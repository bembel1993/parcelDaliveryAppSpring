package com.example.parceldeliveryapp.enums;

public enum UserType {
    USER(1),
    ADMIN(2);

    private int value;

    UserType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
