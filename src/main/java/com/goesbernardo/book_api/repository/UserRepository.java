package com.goesbernardo.book_api.repository;

import com.goesbernardo.book_api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User,String> {

    UserDetails findByLogin(String login);


}
