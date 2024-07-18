package com.kanchan.Mvc.repository;

import com.kanchan.Mvc.models.Role;
import com.kanchan.Mvc.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);

}
