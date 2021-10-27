package com.gianca1994.subjectservice.controller;

import com.gianca1994.subjectservice.model.SubjectModel;
import com.gianca1994.subjectservice.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @GetMapping
    public ResponseEntity<List<SubjectModel>> getAll() {
        List<SubjectModel> subjects = subjectService.getAll();
        if (subjects.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectModel> getById(@PathVariable("id") int id) {
        SubjectModel subject = subjectService.getSubjectById(id);
        if (subject == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(subject);
    }

    @PostMapping()
    public ResponseEntity<SubjectModel> save(@RequestBody SubjectModel subject) {
        SubjectModel newSubject = subjectService.save(subject);
        return ResponseEntity.ok(newSubject);
    }

    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<SubjectModel>> getByUserId(@PathVariable("userId") int userId) {
        List<SubjectModel> subjects = subjectService.byUserId(userId);
        return ResponseEntity.ok(subjects);
    }

}
