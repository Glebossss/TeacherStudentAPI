package com.example.demo.serviceImp;

import com.example.demo.dao.model.StudentEntity;
import com.example.demo.dao.model.TeacherEntity;
import com.example.demo.dao.model.UserEntity;
import com.example.demo.dao.model.enums.RoleUser;
import com.example.demo.dao.repository.StudentRepository;
import com.example.demo.dao.repository.TeacherRepository;
import com.example.demo.dao.repository.UserRepository;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private static final String ROLE = "STUDENT";

    @Autowired
    UserRepository userRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    StudentRepository studentRepository;

    @Transient
    @Override
    public List<UserDTO> findAllUser() {

        List<UserDTO> userDTOS = new ArrayList<>();
        userRepository.findAll().forEach(x -> userDTOS.add(x.toAllUserDTO()));
        return userDTOS;
    }

    @Transient
    @Override
    public void save(UserEntity allUser) {
        if (userRepository.existsByEmail(allUser.getEmail()))
            return;
        userRepository.save(allUser);
    }

    @Transient
    @Override
    public void update(String email) {

        final RoleUser roleUser = userRepository.findByEmail(email).getRole();
        final String rool = roleUser.toString();
        if (rool.equals(ROLE)) {
            final UserEntity userEntity = userRepository.findByEmail(email);
            userEntity.setRole(RoleUser.TEACHER);
            final StudentEntity studentEntity = studentRepository.findByStudentEmail(email);
            final TeacherEntity teacherEntity = new TeacherEntity(userEntity.getEmail(),
                    userEntity.getName(),
                    userEntity.getPictureURL());
            teacherRepository.save(teacherEntity);
            studentRepository.delete(studentEntity);
        } else {
            final UserEntity userEntity = userRepository.findByEmail(email);
            userEntity.setRole(RoleUser.STUDENT);
            final TeacherEntity teacherEntity = teacherRepository.findByEmail(email);
            final StudentEntity studentEntity = new StudentEntity(userEntity.getEmail(),
                    userEntity.getName(),
                    userEntity.getPictureURL());

            studentRepository.save(studentEntity);
            teacherRepository.delete(teacherEntity);
        }
    }

    @Transient
    @Override
    public UserDTO findByLogin(String email) {

        final UserDTO userDTO = userRepository.findByEmail(email).toAllUserDTO();

        return userDTO;
    }

    @Transient
    @Override
    public List<UserDTO> findAllUserPagebal(Pageable pageable) {

        List<UserDTO> userDTOS = new ArrayList<>();
        userRepository.findAllUserEntitiesBy(pageable).forEach(x -> userDTOS.add(x.toAllUserDTO()));

        return userDTOS;
    }
}
