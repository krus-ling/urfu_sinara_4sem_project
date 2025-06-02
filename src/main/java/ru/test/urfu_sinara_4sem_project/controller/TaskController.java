package ru.test.urfu_sinara_4sem_project.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.test.urfu_sinara_4sem_project.model.Task;
import ru.test.urfu_sinara_4sem_project.service.TaskService;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public String listTasks(Model model) {
        var tasks = taskService.findAll();
        model.addAttribute("tasks", tasks);
        model.addAttribute("totalTasks", tasks.size());
        long completedTasks = tasks.stream().filter(Task::isCompleted).count();
        model.addAttribute("completedTasks", completedTasks);
        return "tasks/list";
    }


    @GetMapping("/new")
    public String newTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "tasks/new";
    }

    @PostMapping
    public String createTask(@Valid @ModelAttribute Task task, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "tasks/new";
        }
        taskService.save(task);
        return "redirect:/tasks";
    }

    @GetMapping("/{id}/edit")
    public String editTaskForm(@PathVariable Long id, Model model) {
        Task task = taskService.findById(id).orElseThrow(() -> new IllegalArgumentException("Неверный ID задачи"));
        model.addAttribute("task", task);
        return "tasks/edit";
    }

    @PostMapping("/{id}")
    public String updateTask(@PathVariable Long id, @Valid @ModelAttribute Task task, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "tasks/edit";
        }
        task.setId(id);
        taskService.save(task);
        return "redirect:/tasks";
    }

    @PostMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteById(id);
        return "redirect:/tasks";
    }

    @PostMapping("/{id}/completed")
    @ResponseBody
    public ResponseEntity<?> updateCompleted(@PathVariable Long id, @RequestBody Map<String, Boolean> payload) {
        Optional<Task> optionalTask = taskService.findById(id);
        if (optionalTask.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Task task = optionalTask.get();
        Boolean completed = payload.get("completed");
        if (completed == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Missing 'completed' field"));
        }
        task.setCompleted(completed);
        taskService.save(task);
        return ResponseEntity.ok(Map.of("success", true));
    }
}
