package com.reminder.service.type;

/**
 * The enumeration of Rel types
 */

public enum RelValue {

    SELF("self"),
    GET("get"),
    SEARCH("search"),
    CREATE("create"),
    UPDATE("update"),
    DELETE("delete");

    private String name;

    private RelValue(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
};
