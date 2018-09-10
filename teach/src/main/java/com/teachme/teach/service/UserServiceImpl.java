package com.teachme.teach.service;

import com.teachme.teach.model.Role;
import com.teachme.teach.model.Users;
import com.teachme.teach.repository.RoleRepository;
import com.teachme.teach.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

   /* @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;*/

    @Override
    public Users findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(Users user) {
      // user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        // user.setActive(1);
      Role  userRole = roleRepository.findByRole("USER");
       // user.setRole(new HashSet<Role>(Arrays.asList(userRole)));
       user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
        userRepository.save(user);
    }
}
