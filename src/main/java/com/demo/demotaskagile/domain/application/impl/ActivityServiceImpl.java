package com.demo.demotaskagile.domain.application.impl;

import com.demo.demotaskagile.domain.application.ActivityService;
import com.demo.demotaskagile.domain.model.activity.Activity;
import com.demo.demotaskagile.domain.model.activity.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;

    @Override
    public void saveActivity(Activity activity) {
        activityRepository.save(activity);
    }
}
