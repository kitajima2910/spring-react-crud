package com.javadocfast.crud.repository;

import com.javadocfast.crud.config.ERole;
import com.javadocfast.crud.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {

    Role findByName(ERole name);

}
