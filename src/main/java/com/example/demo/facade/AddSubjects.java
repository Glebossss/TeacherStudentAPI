package com.example.demo.facade;

import com.example.demo.dao.model.SubjectEntity;

import java.util.ArrayList;
import java.util.List;

public class AddSubjects {

    public static List<SubjectEntity> addSubject() {

        List<SubjectEntity> subjectEntities = new ArrayList<>();
        subjectEntities.add( new SubjectEntity("Maths"));
        subjectEntities.add(new SubjectEntity("Programming"));
        subjectEntities.add( new SubjectEntity("Physics"));
        subjectEntities.add(new SubjectEntity("Economy"));
        subjectEntities.add(new SubjectEntity("English"));
        subjectEntities.add(new SubjectEntity("Chemistry"));
        subjectEntities.add(new SubjectEntity("Default"));

        return subjectEntities;
    }
}
