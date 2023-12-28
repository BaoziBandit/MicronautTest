package com.safestreets.scheduled;

import com.safestreets.model.User;
import com.safestreets.services.UserService;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.scheduling.annotation.Async;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

/**
 * OnStart
 * Created By: dylanthompson
 * Created On: 12/28/23
 * Note: This class is executed on application start.
 * For the sake of the test any models that need to be pre-populated should be populated here.
 **/
@Slf4j
@Singleton
@Requires(notEnv = Environment.TEST) // Don't load data in tests.
public class OnStart implements ApplicationEventListener<StartupEvent> {

  private final UserService userService;

  public OnStart(UserService userService) {
    this.userService = userService;
  }

  @Async
  @Override
  public void onApplicationEvent(StartupEvent event) {
    log.info("Executing startup job - creating a user.");
    User user = userService.createNewUser("Test User", "test@safestreets.com", "ADMIN");
    log.info("Created user (Id:" + user.getId() + ")");
  }
}
