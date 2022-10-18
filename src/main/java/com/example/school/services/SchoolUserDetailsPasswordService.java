//package com.example.school.services;
//
//import com.example.school.entities.UserEntity;
//import com.example.school.repositories.UsersRepository;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsPasswordService;
//import org.springframework.stereotype.Service;
//
//@Service
//public class SchoolUserDetailsPasswordService implements UserDetailsPasswordService {
//    private final UsersRepository repository;
//
//    public SchoolUserDetailsPasswordService(UsersRepository repository) {
//        this.repository = repository;
//    }
//
//    @Override
//    public UserDetails updatePassword(UserDetails user, String newPassword) {
//        UserEntity userEntity = repository.findById(user.getUsername()).orElse(null);
//        if (userEntity != null) {
//            userEntity.setPassword(newPassword);
//            repository.save(userEntity);
//        }
//        return User
//                .withUsername(user.getUsername())
//                .password(newPassword)
//                .authorities(user.getAuthorities())
//                .build();
//    }
//
//}
