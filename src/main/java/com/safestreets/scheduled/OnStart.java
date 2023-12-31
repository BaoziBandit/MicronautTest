package com.safestreets.scheduled;

import java.util.ArrayList;
import java.util.List;

import com.safestreets.model.Product;
import com.safestreets.model.User;
import com.safestreets.services.OrderService;
import com.safestreets.services.ProductService;
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
  private final ProductService prodService;
  private final OrderService orderService;

  public OnStart(UserService userService, ProductService prodService, OrderService orderService) {
    this.userService = userService;
    this.prodService = prodService;
    this.orderService = orderService;
  }

  @Async
  @Override
  public void onApplicationEvent(StartupEvent event) {
    log.info("Executing startup job - creating a user.");
    User user = userService.createNewUser("Test User", "test@safestreets.com", "ADMIN");
    
    // Test products to have something to retrieve via API
    List<Product> products = new ArrayList<>();
    for(int i = 1; i <= 3; i++){
      Product product = prodService.createNewProduct("Product" + i, i);
      products.add(product);
    }

    // Test Order to have something to retrieve via API
    orderService.createNewOrder(user, products);
  }
}
