package com.home.realtor.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.realtor.models.User;
import com.home.realtor.repositories.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
    final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userRepository.create(user);
    }

    @PutMapping
    public User update(@RequestBody User user) {
        return userRepository.update(user);
    }

    @GetMapping("/company/id/{companyId}")
    public List<User> findAllByCompanyId(@PathVariable String companyId) {
        return userRepository.findAllByCompanyId(companyId);
    }

    @GetMapping("/company/id/{companyId}/user/id/{userId}")
    public User findByCompanyIdAndUserId(@PathVariable String companyId, @PathVariable String userId) {
        return userRepository.getByCompanyIdAndUserId(companyId, userId);
    }

}
