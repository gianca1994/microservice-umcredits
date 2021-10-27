package com.gianca1994.userservice.feignclients;

import com.gianca1994.userservice.entity.Subject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="subject-service", url="http://localhost:8002")
@RequestMapping("/subject")
public interface SubjectFeignClient {

    @PostMapping()
    Subject save(@RequestBody Subject subject);

    @GetMapping("/byuser/{userId}")
    List<Subject> getSubjects(@PathVariable("userId") int userId);

}
