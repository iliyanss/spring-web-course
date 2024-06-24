package com.plannerapp.controller;

import com.plannerapp.model.dto.UsersWithTasksDTO;
import com.plannerapp.model.entity.Task;
import com.plannerapp.model.entity.User;
import com.plannerapp.service.TaskServiceImpl;
import com.plannerapp.service.UserServiceImpl;
import com.plannerapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.Set;

@Controller
public class HomeControllerImpl implements HomeController {

    private final LoggedUser loggedUser;
    private final UserServiceImpl userService;
    private final TaskServiceImpl taskService;

    public HomeControllerImpl(LoggedUser loggedUser,
                              TaskServiceImpl taskService,
                              UserServiceImpl userService) {
        this.loggedUser = loggedUser;
        this.taskService = taskService;
        this.userService = userService;
    }

    @Override
    public String index() {
        if (loggedUser.isLogged()) {
            return "redirect:/home";
        }

        return "index";
    }

    @Override
    public String home(Model model) {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }

        User user = userService.findUserById(loggedUser.getId()).orElse(null);
        model.addAttribute("currentUserInfo", user);
//        model.addAttribute("user", user);


//        Set<Task> offersFromCurrentUser = this.offerService.getOffersFromCurrentUser(this.loggedUser.getId());
        Set<Task> usersAssignedTasks = this.userService.getAssignedTasksFromCurrentUser(this.loggedUser.getId());
        model.addAttribute("usersAssignedTasks", usersAssignedTasks);

//        Set<OffersWithUsernamesDTO> offersFromOtherUsers = this.offerService.getOffersFromOtherUsers(this.loggedUser.getId());
        Set<UsersWithTasksDTO> tasksFromOtherUsers = this.userService.getTasksFromOtherUsers(this.loggedUser.getId());
        model.addAttribute("otherUserTasks", tasksFromOtherUsers);

        Set<Task> allUnassignedTasks = this.taskService.getAllUnassignedTasks();
        model.addAttribute("allUnassignedTasks", allUnassignedTasks);
        model.addAttribute("totalUnassignedTasks", allUnassignedTasks.size());

        return "home";
    }


}
