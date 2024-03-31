package com.mju.mentoring.mission.exception;

import com.mju.mentoring.mission.exception.exceptions.NotFoundMissionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MissionExceptionHandler {

    @ExceptionHandler(NotFoundMissionException.class)
    public ResponseEntity<String> handleNotFoundMissionException(
        final NotFoundMissionException exception) {
        return getResponseWithStatus(HttpStatus.NOT_FOUND, exception);
    }

    private ResponseEntity<String> getResponseWithStatus(final HttpStatus httpStatus,
        final NotFoundMissionException exception) {
        return ResponseEntity.status(httpStatus)
            .body(exception.getMessage());
    }
}
