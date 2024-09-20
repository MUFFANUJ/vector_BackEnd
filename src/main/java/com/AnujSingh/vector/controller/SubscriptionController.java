package com.AnujSingh.vector.controller;

import com.AnujSingh.vector.model.Plantype;
import com.AnujSingh.vector.model.Subscription;
import com.AnujSingh.vector.model.User;
import com.AnujSingh.vector.repository.SubscriptionRepository;
import com.AnujSingh.vector.service.SubscrptionService;
import com.AnujSingh.vector.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {
    @Autowired
    private UserService userService;
    @Autowired
    private SubscrptionService subscrptionService;

    @GetMapping("/user")
    public ResponseEntity<Subscription> getUserSubscriptions
            (@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Subscription subscription = subscrptionService.getUserSubscription(user.getId());
        return  new ResponseEntity<>(subscription, HttpStatus.OK);
    }

    @PatchMapping("/upgrade")
    public ResponseEntity<Subscription> upgradeSubscriptions
            (@RequestHeader("Authorization") String jwt,
             @RequestParam Plantype plantype) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Subscription subscription = subscrptionService.updateSubscription(user.getId(), plantype);
        return  new ResponseEntity<>(subscription, HttpStatus.OK);
    }



}
