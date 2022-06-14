package org.chobit.cm.spring;


import org.chobit.cm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class ScheduleConfig {


    private final UserService userService;

    @Autowired
    public ScheduleConfig(UserService userService) {
        this.userService = userService;
    }


    @Scheduled(cron = "0 0/5 * * * ? ")
    public void reInform() {
        userService.reInform();
    }
}
