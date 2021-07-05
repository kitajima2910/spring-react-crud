package com.javadocfast.crud.seed;

import com.javadocfast.crud.config.ERole;
import com.javadocfast.crud.entity.Role;
import com.javadocfast.crud.entity.User;
import com.javadocfast.crud.repository.IRoleRepository;
import com.javadocfast.crud.repository.IUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
public class SeedUsersTable {

    private static IUserRepository userRepository;
    private static IRoleRepository roleRepository;

    public SeedUsersTable(IUserRepository userRepository, IRoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public static void insertData() throws ParseException {
        long count = userRepository.count();
        if(count == 0) {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            // Insert User 01
            Set<Role> roles01 = new HashSet<>();
            Role roleAdmin01 = roleRepository.findByName(ERole.ROLE_ADMIN);
            Role roleUser01 = roleRepository.findByName(ERole.ROLE_USER);
            roles01.add(roleAdmin01);
            roles01.add(roleUser01);
            User user01 = new User("hoaipx", "Phạm Xuân Hoài", format.parse("1995-10-29"),
                    1, "TP. Hồ Chí Minh", "71418", "0901858004", null,
                    "kitajima2910@gmail.com",
                    new BCryptPasswordEncoder().encode("123456"), roles01);

            // Insert User 02
            Set<Role> roles02 = new HashSet<>();
            Role roleUser02 = roleRepository.findByName(ERole.ROLE_USER);
            roles02.add(roleUser02);
            User user02 = new User("huypq", "Phạm Quang Huy", null, 1,
                    "TP. Hồ Chí Minh","72300", null, null, "huypq@gmail.com",
                    new BCryptPasswordEncoder().encode("123456"), roles02);

            // Insert Data
            userRepository.saveAll(Arrays.asList(user01, user02));
            log.info("Users Table Seeded.");
        } else {
            log.trace("Users Seeding Not Required.");
        }
    }

}
