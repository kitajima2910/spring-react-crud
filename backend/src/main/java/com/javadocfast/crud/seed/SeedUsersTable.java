package com.javadocfast.crud.seed;

import com.javadocfast.crud.config.ERole;
import com.javadocfast.crud.entity.Role;
import com.javadocfast.crud.entity.User;
import com.javadocfast.crud.repository.IRoleRepository;
import com.javadocfast.crud.repository.IUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class SeedUsersTable {

    private static final Logger LOGGER = LoggerFactory.getLogger(SeedUsersTable.class);

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
            Role roleAdmin01 = roleRepository.findByName(ERole.ROLE_ADMIN).get();
            Role roleUser01 = roleRepository.findByName(ERole.ROLE_USER).get();
            roles01.add(roleAdmin01);
            roles01.add(roleUser01);
            User user01 = new User("hoaipx", "Phạm Xuân Hoài", format.parse("1995-10-29"),
                    0, "TP. Hồ Chí Minh", "71418", "0901858004", null, null,
                    "kitajima2910@gmail.com",
                    new BCryptPasswordEncoder().encode("123456"), roles01);

            // Insert User 02
            Set<Role> roles02 = new HashSet<>();
            Role roleUser02 = roleRepository.findByName(ERole.ROLE_USER).get();
            roles02.add(roleUser02);
            User user02 = new User("huypq1", "Phạm Quang Huy 01", null, 0,
                    "TP. Hồ Chí Minh","72300", null, null, null, "huypq1@gmail.com",
                    new BCryptPasswordEncoder().encode("123456"), roles02);

            // Insert User 03
            Set<Role> roles03 = new HashSet<>();
            Role roleUser03 = roleRepository.findByName(ERole.ROLE_USER).get();
            roles03.add(roleUser03);
            User user03 = new User("huypq2", "Phạm Quang Huy 02", null, 0,
                    "TP. Hồ Chí Minh","72300", null, null, null, "huypq2@gmail.com",
                    new BCryptPasswordEncoder().encode("123456"), roles02);

            // Insert User 04
            Set<Role> roles04 = new HashSet<>();
            Role roleUser04 = roleRepository.findByName(ERole.ROLE_USER).get();
            roles04.add(roleUser04);
            User user04 = new User("huypq3", "Phạm Quang Huy 03", null, 0,
                    "TP. Hồ Chí Minh","72300", null, null, null, "huypq3@gmail.com",
                    new BCryptPasswordEncoder().encode("123456"), roles02);

            // Insert User 05
            Set<Role> roles05 = new HashSet<>();
            Role roleUser05 = roleRepository.findByName(ERole.ROLE_USER).get();
            roles05.add(roleUser05);
            User user05 = new User("huypq4", "Phạm Quang Huy 04", null, 0,
                    "TP. Hồ Chí Minh","72300", null, null, null, "huypq4@gmail.com",
                    new BCryptPasswordEncoder().encode("123456"), roles02);

            // Insert User 06
            Set<Role> roles06 = new HashSet<>();
            Role roleUser06 = roleRepository.findByName(ERole.ROLE_USER).get();
            roles06.add(roleUser06);
            User user06 = new User("huypq5", "Phạm Quang Huy 05", null, 0,
                    "TP. Hồ Chí Minh","72300", null, null, null, "huypq5@gmail.com",
                    new BCryptPasswordEncoder().encode("123456"), roles02);

            // Insert User 07
            Set<Role> roles07 = new HashSet<>();
            Role roleUser07 = roleRepository.findByName(ERole.ROLE_USER).get();
            roles07.add(roleUser07);
            User user07 = new User("huypq6", "Phạm Quang Huy 06", null, 0,
                    "TP. Hồ Chí Minh","72300", null, null, null, "huypq6@gmail.com",
                    new BCryptPasswordEncoder().encode("123456"), roles02);

            // Insert User 08
            Set<Role> roles08 = new HashSet<>();
            Role roleUser08 = roleRepository.findByName(ERole.ROLE_USER).get();
            roles08.add(roleUser08);
            User user08 = new User("huypq7", "Phạm Quang Huy 07", null, 0,
                    "TP. Hồ Chí Minh","72300", null, null, null, "huypq7@gmail.com",
                    new BCryptPasswordEncoder().encode("123456"), roles02);

            // Insert User 09
            Set<Role> roles09 = new HashSet<>();
            Role roleUser09 = roleRepository.findByName(ERole.ROLE_USER).get();
            roles09.add(roleUser09);
            User user09 = new User("huypq8", "Phạm Quang Huy 08", null, 0,
                    "TP. Hồ Chí Minh","72300", null, null, null, "huypq8@gmail.com",
                    new BCryptPasswordEncoder().encode("123456"), roles02);

            // Insert User 10
            Set<Role> roles10 = new HashSet<>();
            Role roleUser10 = roleRepository.findByName(ERole.ROLE_USER).get();
            roles10.add(roleUser10);
            User user10 = new User("huypq9", "Phạm Quang Huy 09", null, 0,
                    "TP. Hồ Chí Minh","72300", null, null, null, "huypq9@gmail.com",
                    new BCryptPasswordEncoder().encode("123456"), roles02);

            // Insert User 11
            Set<Role> roles11 = new HashSet<>();
            Role roleUser11 = roleRepository.findByName(ERole.ROLE_USER).get();
            roles11.add(roleUser11);
            User user11 = new User("huypq10", "Phạm Quang Huy 10", null, 0,
                    "TP. Hồ Chí Minh","72300", null, null, null, "huypq10@gmail.com",
                    new BCryptPasswordEncoder().encode("123456"), roles02);

            // Insert Data
            userRepository.saveAll(Arrays.asList(
                    user01, user02, user03, user04, user05, user06,
                    user07, user08, user09, user10, user11
            ));
            LOGGER.info("Users Table Seeded.");
        } else {
            LOGGER.trace("Users Seeding Not Required.");
        }
    }

}
