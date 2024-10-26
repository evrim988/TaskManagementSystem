package org.example.taskmanagementsystemproject.entity.enums;

public enum Status {
    ACTIVE(1),
    PASIVE(0);

    private final Integer value;

    Status(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
