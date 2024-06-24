package com.plannerapp.service;

import com.plannerapp.model.dto.AddTaskDTO;
import com.plannerapp.model.entity.Priority;
import com.plannerapp.model.entity.PriorityEnum;
import com.plannerapp.model.entity.Task;
import com.plannerapp.model.entity.User;
import com.plannerapp.repo.TaskRepo;
import com.plannerapp.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Service
public class TaskServiceImpl {

    private final TaskRepo taskRepo;
    private final PriorityService priorityService;
    private final UserServiceImpl userService;
    private final UserRepo userRepo;

    public TaskServiceImpl(TaskRepo taskRepo, PriorityService priorityService, UserServiceImpl userService, UserRepo userRepo) {
        this.taskRepo = taskRepo;
        this.priorityService = priorityService;
        this.userService = userService;
        this.userRepo = userRepo;
    }

    public void addTask(AddTaskDTO addTaskDTO, Long id) {
        Task task = new Task();
        Priority priority = this.priorityService.findPriority(addTaskDTO.getPriority());
        User userById = userService.findUserById(id).orElse(null);

        task.setPriority(priority);
        task.setDescription(addTaskDTO.getDescription());
        task.setDueDate(addTaskDTO.getDueDate());

//        userById.getAssignedTasks().add(task);
        this.taskRepo.save(task);
        this.userRepo.save(userById);
    }


    public void assignTaskWithId(Long taskId, Long userId) {
        User currentUser = userService.findUserById(userId).orElse(null);
        Task taskById = taskRepo.findById(taskId).orElse(null);
        taskById.setAssignedTo(currentUser);
        taskRepo.save(taskById);
        currentUser.getAssignedTasks().add(taskById);

        userRepo.save(currentUser);
    }

    public void addTestTasks() {
        User admin1 = userService.findUserById(Long.parseLong("1")).orElse(null);
        User test1 = userService.findUserById(Long.parseLong("2")).orElse(null);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        Task task1 = new Task().setDueDate(LocalDate.parse("15-04-2032", dateTimeFormatter))
                .setPriority(priorityService.findPriority(PriorityEnum.IMPORTANT))
                .setDescription("Prepare presentation for TBD meeting");
        assignTaskToUser(admin1, task1);

//        Task task2 = new Task().setDueDate(LocalDate.now())
//                .setPriority(priorityService.findPriority(PriorityEnum.IMPORTANT))
//                .setDescription("Edit \"About us\" page");
//        assignTaskToUser(admin1, task2);

        Task task3 = new Task().setDueDate(LocalDate.parse("20-01-2032", dateTimeFormatter))
                .setPriority(priorityService.findPriority(PriorityEnum.LOW))
                .setDescription("Test new resources");
        taskRepo.save(task3);

        Task task4 = new Task().setDueDate(LocalDate.parse("22-11-2032", dateTimeFormatter))
                .setPriority(priorityService.findPriority(PriorityEnum.LOW))
                .setDescription("Test new image compressing solutions");
        taskRepo.save(task4);

        Task task5 = new Task().setDueDate(LocalDate.parse("14-02-2031", dateTimeFormatter))
                .setPriority(priorityService.findPriority(PriorityEnum.IMPORTANT))
                .setDescription("Fix issue with payment on page 4758");
        taskRepo.save(task5);


        Task task6 = new Task().setDueDate(LocalDate.parse("10-06-2029", dateTimeFormatter))
                .setPriority(priorityService.findPriority(PriorityEnum.IMPORTANT))
                .setDescription("Deactivate promo codes");
        taskRepo.save(task6);


//        Task task7 = new Task().setDueDate(LocalDate.now())
//                .setPriority(priorityService.findPriority(PriorityEnum.LOW))
//                .setDescription("Prepare documents for the tax office");
//        taskRepo.save(task7);

        Task task8 = new Task().setDueDate(LocalDate.parse("15-05-2030", dateTimeFormatter))
                .setPriority(priorityService.findPriority(PriorityEnum.LOW))
                .setDescription("Develop invoice automation for repeating orders");
        taskRepo.save(task8);

        Task task9 = new Task().setDueDate(LocalDate.parse("01-04-2029", dateTimeFormatter))
                .setPriority(priorityService.findPriority(PriorityEnum.URGENT))
                .setDescription("Listen to the full Overplay album");
        assignTaskToUser(admin1,task9);
        taskRepo.save(task9);

        userRepo.save(admin1);
        userRepo.save(test1);
    }

    private void assignTaskToUser(User user, Task task) {
        user.getAssignedTasks().add(task);
        task.setAssignedTo(user);
        taskRepo.save(task);
    }

    public void removeTaskById(Long taskId, Long userId) {
        User user = userRepo.findById(userId).orElse(null);
        Task task1 = user.getAssignedTasks().stream().filter(e -> e.getId().equals(taskId)).findFirst().orElse(null);
        boolean removed = user.getAssignedTasks().remove(task1);

        userRepo.save(user);
        taskRepo.delete(task1);
    }

    public Set<Task> getAllUnassignedTasks() {
        return taskRepo.findAllByAssignedToIsNull();

    }

    public void returnTask(Long taskId, Long userId) {
        Task task = taskRepo.findById(taskId).orElse(null);
        User user = userRepo.findById(userId).orElse(null);
        Task task1 = user.getAssignedTasks().stream().filter(e -> e.getId().equals(taskId)).findFirst().orElse(null);
        boolean remove = user.getAssignedTasks().remove(task1);
        task.setAssignedTo(null);
        taskRepo.save(task);
        userRepo.save(user);
    }
}
