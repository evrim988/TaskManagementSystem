package org.example.taskmanagementsystemproject.repository;

import org.example.taskmanagementsystemproject.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findAllByAssignedToUserId(Long userId);

}
