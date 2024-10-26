package org.example.taskmanagementsystemproject.utility;

import org.example.taskmanagementsystemproject.entity.Task;
import org.example.taskmanagementsystemproject.entity.enums.TaskStatus;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Task task = new Task();
        task.setTitle("Görev Başlığı");
        task.setDescription("Görev Açıklaması");
        task.setStartDate(LocalDate.of(2024,10,19));
        task.setEndDate(LocalDate.of(2024,10,20));
        task.setTaskStatus(TaskStatus.PENDING);

    }
}
