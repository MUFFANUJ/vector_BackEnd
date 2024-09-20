package com.AnujSingh.vector.service;

import com.AnujSingh.vector.model.Plantype;
import com.AnujSingh.vector.model.Subscription;
import com.AnujSingh.vector.model.User;
import com.AnujSingh.vector.repository.SubscriptionRepository;
import com.AnujSingh.vector.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SubsScriptionServiceImpl implements SubscrptionService{

    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public Subscription createSubscription(User user) {
        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setSubscriptionStartDate(LocalDate.now());
        subscription.setSubscriptionStartDate(LocalDate.now().plusMonths(12));
        subscription.setValid(true);
        subscription.setPlantype(Plantype.FREE);
        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription getUserSubscription(Long UserId){
        Subscription subscription = subscriptionRepository.findByUserId(UserId);
        if(!isValid(subscription)){
            subscription.setPlantype(Plantype.FREE);
            subscription.setGetSubscriptionEndDate(LocalDate.now().plusMonths(12));
            subscription.setSubscriptionStartDate(LocalDate.now());
        }
        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription updateSubscription(Long userId, Plantype plantype) {
        Subscription subscription = subscriptionRepository.findByUserId(userId);
        subscription.setPlantype(plantype);
        subscription.setSubscriptionStartDate(LocalDate.now());
        if(plantype.equals(Plantype.ANNUALLY)){
            subscription.setGetSubscriptionEndDate(LocalDate.now().plusMonths(12));
        }else{
            subscription.setGetSubscriptionEndDate(LocalDate.now().plusMonths(1));
        }
        return subscriptionRepository.save(subscription);
    }

    @Override
    public boolean isValid(Subscription subscription) {
        if(subscription.getPlantype().equals(Plantype.FREE)){
            return true;
        }

        LocalDate endDate = subscription.getGetSubscriptionEndDate();
        LocalDate currentDate = LocalDate.now();
        return endDate.isAfter(currentDate) || endDate.isEqual(currentDate);
    }
}
