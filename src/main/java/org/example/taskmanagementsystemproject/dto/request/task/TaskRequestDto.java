package org.example.taskmanagementsystemproject.dto.request.task;

import java.time.LocalDate;

public record TaskRequestDto(
        String title,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        String token,
        Long assignedToUserId
) {
}
