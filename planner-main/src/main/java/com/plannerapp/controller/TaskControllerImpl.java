package com.plannerapp.controller;

import com.plannerapp.model.dto.AddTaskDTO;
import com.plannerapp.service.TaskServiceImpl;
import com.plannerapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TaskControllerImpl implements TaskController {

    private final LoggedUser loggedUser;
    private final TaskServiceImpl taskService;

    public TaskControllerImpl(LoggedUser loggedUser, TaskServiceImpl taskService) {
        this.loggedUser = loggedUser;
        this.taskService = taskService;
    }

    @Override
    public String addTask() {
        if (!loggedUser.isLogged()) {
            return "redirect:/users/login";
        }

        return "task-add";
    }

    @Override
    public String addTask(AddTaskDTO addTaskDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addTaskDTO", addTaskDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addTaskDTO", result);

            return "redirect:/tasks/add-task";
        }

        this.taskService.addTask(addTaskDTO,loggedUser.getId());
        return "redirect:/home";
    }

    @Override
    public String assignTask(Long id) {
        taskService.assignTaskWithId(id, loggedUser.getId());
        return "redirect:/home";
    }

    @Override
    public String removeTask(Long id) {
        taskService.removeTaskById(id,loggedUser.getId());

        return "redirect:/home";
    }

    @Override
    public String returnTask(Long id) {
        taskService.returnTask(id, loggedUser.getId());
        return "redirect:/home";
    }

    @ModelAttribute
    public AddTaskDTO addOfferDTO() {
        return new AddTaskDTO();
    }
}
