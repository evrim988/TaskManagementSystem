package org.example.taskmanagementsystemproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.taskmanagementsystemproject.entity.enums.ObjectStatus;
import org.example.taskmanagementsystemproject.entity.enums.Status;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.swing.plaf.nimbus.State;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass //tablo oluşturmadan diğer tablolar buranın özelliklerini kullanabiliyor.
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @CreationTimestamp
    @Column(updatable = false)
    LocalDateTime createdOn;

    @UpdateTimestamp
    LocalDateTime updatedOn;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    Status status = Status.ACTIVE;
    //aktif mi pasif mi olduğu durum - default değer olarak active olarak ayarladım.


}
