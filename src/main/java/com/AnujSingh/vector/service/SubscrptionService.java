package com.AnujSingh.vector.service;

import com.AnujSingh.vector.model.Plantype;
import com.AnujSingh.vector.model.Subscription;
import com.AnujSingh.vector.model.User;

public interface SubscrptionService {

    Subscription createSubscription(User user);
    Subscription getUserSubscription(Long UserId);
    Subscription updateSubscription(Long userId, Plantype plantype);

    boolean isValid(Subscription subscription);
}
