package com.mju.mentoring.member.exception;

import com.mju.mentoring.member.exception.exceptions.DuplicateNicknameException;
import com.mju.mentoring.member.exception.exceptions.DuplicateUsernameException;
import com.mju.mentoring.member.exception.exceptions.ExpiredTokenException;
import com.mju.mentoring.member.exception.exceptions.InvalidTokenException;
import com.mju.mentoring.member.exception.exceptions.MemberNotFoundException;
import com.mju.mentoring.member.exception.exceptions.WrongPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MemberExceptionHandler {

    @ExceptionHandler(DuplicateUsernameException.class)
    public ResponseEntity<String> handleDuplicateUsernameException(
        final DuplicateUsernameException exception) {
        return getResponseWithStatus(HttpStatus.CONFLICT, exception);
    }

    @ExceptionHandler(DuplicateNicknameException.class)
    public ResponseEntity<String> handleDuplicateNicknameException(
        final DuplicateNicknameException exception) {
        return getResponseWithStatus(HttpStatus.CONFLICT, exception);
    }

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<String> handleMemberNotFoundException(
        final MemberNotFoundException exception) {
        return getResponseWithStatus(HttpStatus.NOT_FOUND, exception);
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<String> handleWrongPasswordException(
        final DuplicateNicknameException exception) {
        return getResponseWithStatus(HttpStatus.BAD_REQUEST, exception);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<String> handleInvalidTokenException(
        final InvalidTokenException exception) {
        return getResponseWithStatus(HttpStatus.UNAUTHORIZED, exception);
    }

    @ExceptionHandler(ExpiredTokenException.class)
    public ResponseEntity<String> handleExpiredTokenException(
        final ExpiredTokenException exception) {
        return getResponseWithStatus(HttpStatus.UNAUTHORIZED, exception);
    }

    private ResponseEntity<String> getResponseWithStatus(final HttpStatus httpStatus,
        final RuntimeException exception) {
        return ResponseEntity.status(httpStatus)
            .body(exception.getMessage());
    }
}
