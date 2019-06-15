package com.phonemarket.mapper;

import com.phonemarket.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {
    Role findById(Integer id);

    List<Role> findAllRole();

    Role findRolesFunsById(Integer roleId);
}
