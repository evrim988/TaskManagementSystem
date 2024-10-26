package org.example.taskmanagementsystemproject.entity.enums;

public enum ObjectStatus {

    DELETED(1),
    NONDELETED(0);

    private final Integer value;

    ObjectStatus(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
