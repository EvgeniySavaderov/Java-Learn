package homeworks.service;

import homeworks.dto.UserRequest;
import homeworks.dto.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> getUsers();

    void deleteAllUsers();

    UserResponse addUser(UserRequest userRequest);

    void deleteById(Long id);

    UserResponse findById(Long id);
}
