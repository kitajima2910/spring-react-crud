package com.javadocfast.crud.controller;

import com.javadocfast.crud.config.ERole;
import com.javadocfast.crud.entity.Role;
import com.javadocfast.crud.entity.User;
import com.javadocfast.crud.payload.request.UpdateUserRequest;
import com.javadocfast.crud.payload.response.MessageResponse;
import com.javadocfast.crud.repository.IRoleRepository;
import com.javadocfast.crud.repository.IUserRepository;
import com.javadocfast.crud.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Value("${javadocfast.cloudinary.folder.avatar}")
    private String avatar;

    private Map<String, String> options = new HashMap<>();

    @GetMapping("/list")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getUsers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(defaultValue = "") String keyword
    ) {

        Pageable pageable = PageRequest.of(
                page - 1, pageSize,
                "asc".equals(sortDir) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending()
        );

        Page<User> users = "".equals(keyword) ?
                userRepository.findAll(pageable) :
                userRepository.findUsersByFullNameLike("%" + keyword + "%", pageable);

        return ResponseEntity.ok(users);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> updateUser(@Valid @ModelAttribute UpdateUserRequest updateUserRequest) throws IOException {

        // Check Exits Username
        if (!userRepository.existsByUsername(updateUserRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Unable to identify account"));
        }

        // Find User By Username
        User user = userRepository.findByUsername(updateUserRequest.getUsername()).get();

        // Set Propeties To Entity User To Update
        user.setFullName(updateUserRequest.getFullName());
        user.setBirthday(updateUserRequest.getBirthday());
        user.setGender(updateUserRequest.getGender());
        user.setAddress(updateUserRequest.getAddress());
        user.setPostcode(updateUserRequest.getPostcode());
        user.setPhone(updateUserRequest.getPhone());
        user.setEmail(updateUserRequest.getEmail());

        // Set Roles To Entity User To Update
        Set<String> strRoles = updateUserRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null || strRoles.size() == 0) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(adminRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(userRole);
                        break;
                }
            });
        }

        user.setRoles(roles);


        // Check Image Avatar
        MultipartFile multipartFile = updateUserRequest.getAvatar();

        if (multipartFile != null) {
            BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
            if (bufferedImage == null) {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: Invalid image"));
            }

            // Folder To Save Avatar
            options.put("folder", avatar);

            if (user.getNameImage() != null) {
                // Delete Old Avatar
                cloudinaryService.delete(user.getNameImage(), options);
            }

            // Update New Avatar
            Map result = cloudinaryService.upload(multipartFile, options);
            user.setLinkImage(result.get("url").toString());
            user.setNameImage(result.get("public_id").toString());
        }

        // Update row of table User in Database
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Data update successful"));
    }

}
