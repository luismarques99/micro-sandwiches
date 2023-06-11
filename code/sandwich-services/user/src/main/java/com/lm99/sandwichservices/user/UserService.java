package com.lm99.sandwichservices.user;

import java.util.List;

public interface UserService {

    User createUser(User user);

    List<User> getAllUsersByRoleId(Long roleId);

    User getUserById(Long id);

    User getUserByEmail(String email);

    User updateUser(Long id, User updatedUser);

}
