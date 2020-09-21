package com.example.demo.service;

import com.example.demo.dao.model.AdminEntity;
import com.example.demo.dao.model.StudentEntity;
import com.example.demo.dto.AdminDTO;
import com.example.demo.dto.UserDTO;

public interface AdminService {

    public void save(AdminEntity adminEntity);

    public AdminDTO findByLogin(String email);
}
