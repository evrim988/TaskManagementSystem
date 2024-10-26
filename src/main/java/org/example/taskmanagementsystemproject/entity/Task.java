package org.example.taskmanagementsystemproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.taskmanagementsystemproject.entity.enums.TaskStatus;

import java.time.LocalDate;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbltask")
public class Task extends BaseEntity{
    String title;
    String description;
    LocalDate startDate;
    LocalDate endDate;
    Long assignedByUserId; //Görevi atayan kullanıcı
    Long assignedToUserId; //Görevin atandığı kullanıcı

    @Enumerated(value = EnumType.STRING)
    TaskStatus taskStatus;
}
