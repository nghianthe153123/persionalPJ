package com.example.demo.service;

import com.example.demo.exception.UserExistException;
import com.example.demo.model.UserMaster;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class MasterUserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;



    public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
        Iterable<Long> ids = List.of(id);
        return (UserDetails) userRepository.findAllById(ids);
    }

    public UserMaster save(UserMaster user) {
        //find exist user...
//        if (userRepository.existsByUsername(user.getUsername())) {
//            throw new UserExistException(user.getUsername());
//        }
        //save password with Bcrypt encoder
        String password = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(password);
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
