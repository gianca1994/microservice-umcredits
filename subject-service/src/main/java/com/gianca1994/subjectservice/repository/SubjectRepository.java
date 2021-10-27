package com.gianca1994.subjectservice.repository;


import com.gianca1994.subjectservice.model.SubjectModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectModel, Integer> {
    List<SubjectModel> findByUserId(int userId);
}
