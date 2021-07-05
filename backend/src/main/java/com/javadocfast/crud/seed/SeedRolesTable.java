package com.javadocfast.crud.seed;

import com.javadocfast.crud.config.ERole;
import com.javadocfast.crud.entity.Role;
import com.javadocfast.crud.repository.IRoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
public class SeedRolesTable {

    private static IRoleRepository roleRepository;

    @Autowired
    public SeedRolesTable(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public static void insertData() {
        long count = roleRepository.count();
        if (count == 0) {
            // Insert Roles
            Role role01 = new Role(ERole.ROLE_ADMIN);
            Role role02 = new Role(ERole.ROLE_USER);

            // Insert Data
            roleRepository.saveAll(Arrays.asList(role01, role02));
            log.info("Roles Table Seeded.");
        } else {
            log.trace("Roles Seeding Not Required.");
        }
    }

}
