package com.example.demo.serviceImp;

import com.example.demo.dao.model.AdminEntity;
import com.example.demo.dao.model.StudentEntity;
import com.example.demo.dao.repository.AdminRepository;
import com.example.demo.dto.AdminDTO;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;

@Service
public class AdminServiceImp implements AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Transient
    @Override
    public void save(AdminEntity adminEntity) {
        if (adminRepository.existsByEmail(adminEntity.getEmail())) {
            return;
        }
        adminRepository.save(adminEntity);
    }

    @Transient
    @Override
    public AdminDTO findByLogin(String email) {
        AdminDTO adminDTO = adminRepository.findByEmail(email).toAdminDTO();
        return adminDTO;
    }
}
