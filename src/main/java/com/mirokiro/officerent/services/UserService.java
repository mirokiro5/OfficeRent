package com.mirokiro.officerent.services;

import com.mirokiro.officerent.models.Role;
import com.mirokiro.officerent.models.User;
import com.mirokiro.officerent.repos.RoleRepository;
import com.mirokiro.officerent.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    public boolean saveUser(User user) {
        User userFromDb = userRepository.findByUsername(user.getUsername());
        if (userFromDb != null) {
            return false;
        }
        user.setRoles(Collections.singleton(new Role(3L, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        System.out.println("saved user= "+user+" roles: "+user.roleToString());
        userRepository.save(user);
        return true;
    }
    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepository.findByUsername(username);
        System.out.println("user= "+user);
        System.out.println(" roles: "+user.roleToString());

        if (user == null) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.roleToString())
                .build();
        System.out.println("userDetails= "+userDetails);
        return userDetails;
    }
}
