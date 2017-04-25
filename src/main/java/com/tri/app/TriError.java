package com.tri.app;

/**
 * Enum for errors
 * TriFactory uses it
 *
 * Created by ichebyki on 22.04.2017.
 */
public enum TriError {
    NO_ERROR(0, "No error"),
    INVALID_ARGS_COUNT(1, "Invalid arguments count. Must be either zero or three"),
    INVALID_ARGS_FORMAT(2, "Invalid argument format. Must be valid numeric"),
    INVALID_INPUT_FORMAT(3, "Invalid input format. Must be valid numeric"),
    INVALID_INPUT(4, "General input error. Expected three valid numerics from console."),
    INVALID_SIDES_VALUES(5, "Invalid triangle sides values. The values does not correspond to any Euclidean geometry triangle");

    private final int id;
    private final String msg;

    TriError(int id, String msg) {
        this.id = id;
        this.msg = msg;
    }

    public int getId() {
        return this.id;
    }

    public String getMsg() {
        return this.msg;
    }
}