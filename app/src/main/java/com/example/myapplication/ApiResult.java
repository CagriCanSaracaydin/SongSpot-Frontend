package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Objects;

/**
 * ApiResult class to encapsulate the response from API POST operations,
 * such as posting comments or ratings. This class provides getters for
 * the status and message of the API response.
 */
public final class ApiResult {
    private final String status;
    private final String message;

    /**
     * Constructor for ApiResult with status and message.
     * @param status The status of the API response.
     * @param message The message from the API response.
     */
    public ApiResult(@NonNull String status, @Nullable String message) {
        this.status = Objects.requireNonNull(status, "status must not be null");
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
     * Gets the message from the API response, if any.
     * @return The message from the API response, or null if none.
     */
    @Nullable
    public String getMessage() {
        return message;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiResult apiResult = (ApiResult) o;
        return status.equals(apiResult.status) &&
                Objects.equals(message, apiResult.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, message);
    }
}