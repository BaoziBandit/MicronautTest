package com.safestreets.services;

import com.safestreets.model.User;
import com.safestreets.model.repository.UserRepository;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

/**
 * UserService
 * Created By: dylanthompson
 * Created On: 12/28/23
 **/
@Singleton
@Slf4j
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User createNewUser(String name, String email, String role) {
    User user = new User();
    user.setName(name);
    user.setEmail(email);
    user.setRole(role);
    user = userRepository.save(user);
    if(user.getId() > 0){
      log.info("User creation success! Id: " + user.getId());
    }
    return user;
  }

}
