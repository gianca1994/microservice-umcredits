package com.gianca1994.subjectservice.service;

import com.gianca1994.subjectservice.model.SubjectModel;
import com.gianca1994.subjectservice.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class SubjectService {

    @Autowired
    SubjectRepository subjectRepository;

    public List<SubjectModel> getAll() {
        return subjectRepository.findAll();
    }

    public SubjectModel getSubjectById(int id) {
        return subjectRepository.findById(id).orElse(null);
    }

    public SubjectModel save(SubjectModel subject) {
        return subjectRepository.save(subject);
    }

    public List<SubjectModel> byUserId(int userId) {
        return subjectRepository.findByUserId(userId);
    }

}