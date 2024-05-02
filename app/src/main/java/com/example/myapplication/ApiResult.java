package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * ApiResult class to encapsulate the response from API POST operations,
 * such as posting comments or ratings. This class provides getters and setters
 * for the status and message of the API response.
 */
public class ApiResult {
    private String status;
    private String message;

    /**
     * Default constructor for ApiResult.
     */
    public ApiResult() {
    }

    /**
     * Constructor for ApiResult with status and message.
     * @param status The status of the API response.
     * @param message The message from the API response.
     */
    public ApiResult(@NonNull String status, @Nullable String message) {
        this.status = status;
        this.message = message;
    }

    /**
     * Gets the status of the API response.
     * @return The status of the API response.
     */
    @NonNull
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the API response.
     * @param status The status to set.
     */
    public void setStatus(@NonNull String status) {
        this.status = status;
    }

    /**
     * Gets the message from the API response, if any.
     * @return The message from the API response, or null if none.
     */
    @Nullable
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message in the API response.
     * @param message The message to set, can be null.
     */
    public void setMessage(@Nullable String message) {
        this.message = message;
    }

    /**
     * Returns a string representation of the API response.
     * @return A string representing the status and message of the API response.
     */
    @Override
    public String toString() {
        return "ApiResult{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
