package com.gianca1994.userservice.controller;

import com.gianca1994.userservice.entity.Subject;
import com.gianca1994.userservice.model.User;
import com.gianca1994.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userService.getAll();
        if (users.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") int id) {
        User user = userService.getUserById(id);
        if (user == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }

    @PostMapping()
    public ResponseEntity<User> save(@RequestBody User user) {
        User newUser = userService.save(user);
        return ResponseEntity.ok(newUser);
    }

    @GetMapping("/subjects/{userId}")
    public ResponseEntity<List<Subject>> getSubjects(@PathVariable("userId") int userId){
        User user = userService.getUserById(userId);
        if (user == null)
            return ResponseEntity.notFound().build();

        List<Subject> subjects = userService.getSubjects(userId);
        return ResponseEntity.ok(subjects);
    }

    @PostMapping("/savesubject/{userId}")
    public ResponseEntity<Subject> saveSubject(@PathVariable("userId") int userId, @RequestBody Subject subject){
        if(userService.getUserById(userId) == null)
            return ResponseEntity.notFound().build();

        Subject newSubject = userService.saveSubject(userId, subject);
        return ResponseEntity.ok(subject);
    }

    @GetMapping("/getAll/{userId}")
    public ResponseEntity<Map<String, Object>> getAllSubjects(@PathVariable("userId") int userId){
        Map<String, Object> result = userService.getUserSubjects(userId);
        return ResponseEntity.ok(result);
    }

}
