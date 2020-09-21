package com.example.demo.service;

import com.example.demo.dao.model.UserEntity;
import com.example.demo.dto.UserDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    public List<UserDTO> findAllUser();

    public UserDTO findByLogin(String email);

    public void save(UserEntity allUser);

    public void update(String email);

    public List<UserDTO> findAllUserPagebal(Pageable pageable);

    public long count();
}
