package com.phonemarket.mapper;

import com.phonemarket.entity.Users;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    Users findUserById(Integer userId);

    Integer addUser(Users user);

    Integer deleteUser(Integer userId);

    Integer changeUserState(Integer state, Integer userId);

    Integer updateUser(Users user);

    List<Users> findAllUser();

    List<Users> findUserByName(String name);

    List<Users> findAllUserLikeName(String name);
}
