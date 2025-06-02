package ru.test.urfu_sinara_4sem_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.test.urfu_sinara_4sem_project.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
