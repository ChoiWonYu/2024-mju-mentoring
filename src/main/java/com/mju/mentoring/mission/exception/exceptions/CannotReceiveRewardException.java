package com.mju.mentoring.mission.exception.exceptions;

public class CannotReceiveRewardException extends RuntimeException {

    public CannotReceiveRewardException() {
        super("보상을 받을 수 없습니다.");
    }
}
