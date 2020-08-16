package com.example.demo.facade;

import com.example.demo.dao.model.TeacherEntity;
import com.example.demo.dao.model.UserEntity;
import com.example.demo.dao.model.enums.RoleUser;
import com.example.demo.dto.TeacherDTO;
import com.example.demo.service.TeacherService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddTeachers {

    @Autowired
    TeacherService teacherService;

    @Autowired
    UserService userService;

    public void teacherEntities() {

        List<TeacherDTO> teacherEntities = teachers();
        List<UserEntity> allUserEntities = users();
        teacherEntities.forEach(teacherService::save);
        allUserEntities.forEach(userService::save);
    }

    public static List<UserEntity> users() {

        List<UserEntity> allUserEntities = new ArrayList<>();

        UserEntity allUserEgor = UserEntity.of("blablabla@gg.com", "Egor", "https://lh4.googleusercontent.com/-I78Y4-goIOU/AAAAAAAAAAI/AAAAAAAAAAA/AMZuucmHDdSplh61cwIAOi3CVKkUqmaIOg/s96-c/photo.jpg", RoleUser.TEACHER);
        allUserEntities.add(allUserEgor);

        UserEntity allUserIgor = UserEntity.of("igor@gg.com", "Igor", "https://lh4.googleusercontent.com/-I78Y4-goIOU/AAAAAAAAAAI/AAAAAAAAAAA/AMZuucmHDdSplh61cwIAOi3CVKkUqmaIOg/s96-c/photo.jpg", RoleUser.TEACHER);
        allUserEntities.add(allUserIgor);

        UserEntity allUserValera = UserEntity.of("Valera@gg.com", "Valera", "https://lh4.googleusercontent.com/-I78Y4-goIOU/AAAAAAAAAAI/AAAAAAAAAAA/AMZuucmHDdSplh61cwIAOi3CVKkUqmaIOg/s96-c/photo.jpg", RoleUser.TEACHER);
        allUserEntities.add(allUserValera);

        UserEntity allUserVasil = UserEntity.of("Vasil@gg.com", "Vasil", "https://lh4.googleusercontent.com/-I78Y4-goIOU/AAAAAAAAAAI/AAAAAAAAAAA/AMZuucmHDdSplh61cwIAOi3CVKkUqmaIOg/s96-c/photo.jpg", RoleUser.TEACHER);
        allUserEntities.add(allUserVasil);

        UserEntity allUserPetr = UserEntity.of("Vasil@gg.com", "Vasil", "https://lh4.googleusercontent.com/-I78Y4-goIOU/AAAAAAAAAAI/AAAAAAAAAAA/AMZuucmHDdSplh61cwIAOi3CVKkUqmaIOg/s96-c/photo.jpg", RoleUser.TEACHER);
        allUserEntities.add(allUserPetr);

        return allUserEntities;
    }

    public static List<TeacherDTO> teachers() {

        List<TeacherDTO> teacherEntities = new ArrayList<>();

        TeacherDTO teacherEntity = TeacherEntity.of("blablabla@gg.com", "Egor", "https://lh4.googleusercontent.com/-I78Y4-goIOU/AAAAAAAAAAI/AAAAAAAAAAA/AMZuucmHDdSplh61cwIAOi3CVKkUqmaIOg/s96-c/photo.jpg").toTeacherDTO();
        teacherEntities.add(teacherEntity);

        TeacherDTO teacheUserIgor = TeacherEntity.of("igor@gg.com", "Igor", "https://lh4.googleusercontent.com/-I78Y4-goIOU/AAAAAAAAAAI/AAAAAAAAAAA/AMZuucmHDdSplh61cwIAOi3CVKkUqmaIOg/s96-c/photo.jpg").toTeacherDTO();
        teacherEntities.add(teacheUserIgor);

        TeacherDTO teacheUserValera = TeacherEntity.of("Valera@gg.com", "Valera", "https://lh4.googleusercontent.com/-I78Y4-goIOU/AAAAAAAAAAI/AAAAAAAAAAA/AMZuucmHDdSplh61cwIAOi3CVKkUqmaIOg/s96-c/photo.jpg").toTeacherDTO();
        teacherEntities.add(teacheUserValera);

        TeacherDTO teacheUserVasil = TeacherEntity.of("Vasil@gg.com", "Vasil", "https://lh4.googleusercontent.com/-I78Y4-goIOU/AAAAAAAAAAI/AAAAAAAAAAA/AMZuucmHDdSplh61cwIAOi3CVKkUqmaIOg/s96-c/photo.jpg").toTeacherDTO();
        teacherEntities.add(teacheUserVasil);

        TeacherDTO teacheUserPetr = TeacherEntity.of("Vasil@gg.com", "Vasil", "https://lh4.googleusercontent.com/-I78Y4-goIOU/AAAAAAAAAAI/AAAAAAAAAAA/AMZuucmHDdSplh61cwIAOi3CVKkUqmaIOg/s96-c/photo.jpg").toTeacherDTO();
        teacherEntities.add(teacheUserPetr);

        return teacherEntities;
    }
}
