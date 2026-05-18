package org.example.phase3.service;

import org.example.phase3.dto.UserDto;
import org.springframework.data.domain.Page;


import java.util.List;

public interface UserService {
    UserDto createUser(UserDto dto);
    Page<UserDto> getAllUsers(int page, int size);

}
