package com.plannerapp.init;

import com.plannerapp.service.PriorityService;
import com.plannerapp.service.TaskServiceImpl;
import com.plannerapp.service.UserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FirstInit implements CommandLineRunner {

    private final PriorityService priorityService;
    private final UserServiceImpl userService;
    private final TaskServiceImpl offerService;

    public FirstInit(PriorityService priorityService,
                     UserServiceImpl userService,
                     TaskServiceImpl offerService) {
        this.priorityService = priorityService;
        this.userService = userService;
        this.offerService = offerService;
    }

    @Override
    public void run(String... args) {
        this.userService.initAdmin();
        this.userService.initTest();
        this.priorityService.initPriorities();
        offerService.addTestTasks();

    }
}
