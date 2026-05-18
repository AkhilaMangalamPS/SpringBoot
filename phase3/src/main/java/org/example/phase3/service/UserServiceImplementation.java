package org.example.phase3.service;

import org.example.phase3.dto.UserDto;
import org.example.phase3.entity.User;
import org.example.phase3.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService{

    private final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto dto) {
        User user = new User();
        user.setName(dto.name());
        User savedUser = userRepository.save(user);
        return new UserDto(savedUser.getId(),savedUser.getName());
    }

    @Override
    public Page<UserDto> getAllUsers(int page, int size) {
        Page<User> users = userRepository.findAll(PageRequest.of(page, size));
        return users.map(user ->
                new UserDto(user.getId(), user.getName()));
    }
}
