package com.semicolon.springpart.dto;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Response<T> {
    private String timeStamp;
    private int status;
    private String message;
    private T result;
    private boolean success;

    // 성공 시 사용하는 생성자
    public Response(T result) {
        this.timeStamp = ZonedDateTime.now().withZoneSameInstant(java.time.ZoneId.of("Asia/Seoul"))
                .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        this.status = HttpStatus.OK.value();
        this.message = "요청이 성공했습니다.";
        this.result = result;
        this.success = true;
    }

    // 실패 시 사용하는 생성자
    public Response(HttpStatus status, String message) {
        this.timeStamp = ZonedDateTime.now().withZoneSameInstant(java.time.ZoneId.of("Asia/Seoul"))
                .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        this.status = status.value();
        this.message = message;
        this.result = null;
        this.success = false;
    }

    // 실패 상황 처리를 위한 static 메서드
    public static <T> Response<T> fail(HttpStatus status, String message) {
        return new Response<>(status, message);
    }

    // Getters and Setters
    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
