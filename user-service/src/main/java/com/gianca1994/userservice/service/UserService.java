package com.gianca1994.userservice.service;

import com.gianca1994.userservice.entity.Subject;
import com.gianca1994.userservice.feignclients.SubjectFeignClient;
import com.gianca1994.userservice.model.User;
import com.gianca1994.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    SubjectFeignClient subjectFeignClient;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User save(User user) {
        User newUser = userRepository.save(user);
        return newUser;
    }

    public List<Subject> getSubjects(int userId) {
        List<Subject> subjects = restTemplate.getForObject("http://subject-service/subject/byuser/" + userId, List.class);
        return subjects;

    }

    public Subject saveSubject(int userId, Subject subject) {
        subject.setUserId(userId);
        Subject newSubject = subjectFeignClient.save(subject);
        return newSubject;
    }

    public Map<String, Object> getUserSubjects(int userId) {
        Map<String, Object> result = new HashMap<>();
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            result.put("Message", "El usuario no existe");
            return result;
        }
        result.put("User", user);
        List<Subject> subjects = subjectFeignClient.getSubjects(userId);
        if (subjects.isEmpty())
            result.put("Subject", "El usuario no tiene materias");
        else
            result.put("Subjects", subjects);

        return result;
    }

}
