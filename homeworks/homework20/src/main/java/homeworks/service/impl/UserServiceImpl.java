package homeworks.service.impl;

import homeworks.aspect.Logs;
import homeworks.dto.UserRequest;
import homeworks.dto.UserResponse;
import homeworks.mapper.UserRequestMapper;
import homeworks.mapper.UserResponseMapper;
import homeworks.repository.UserRepository;
import homeworks.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserResponseMapper userResponseMapper;
    private final UserRequestMapper userRequestMapper;

    @Override
    @Logs
    public List<UserResponse> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(userResponseMapper::toUserResponse)
                .toList();
    }

    @Override
    @Logs
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    @Override
    @Logs
    public UserResponse addUser(UserRequest userRequest) {
        return userResponseMapper.toUserResponse(
                userRepository.save(
                        userRequestMapper.toEntity(userRequest)));
    }

    @Override
    @Logs
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Logs
    public UserResponse findById(Long id) {
        return userResponseMapper.toUserResponse(
                userRepository.findById(id).orElseThrow());
    }
}
