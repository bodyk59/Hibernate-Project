package com.softserve.academy;

import com.softserve.academy.service.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Bohdan Kurchak, Ruslan Pryimak
 */
@SpringBootApplication
public class Application {
    private final MarathonService marathonService;
    private final TaskService taskService;
    private final UserService userService;
    private final SprintService sprintService;
    private final ProgressService progressService;

    public Application(MarathonService marathonService,
                       TaskService taskService,
                       UserService userService,
                       SprintService sprintService,
                       ProgressService progressService) {
        this.marathonService = marathonService;
        this.taskService = taskService;
        this.userService = userService;
        this.sprintService = sprintService;
        this.progressService = progressService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
