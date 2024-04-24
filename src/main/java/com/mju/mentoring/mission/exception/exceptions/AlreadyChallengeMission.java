package com.mju.mentoring.mission.exception.exceptions;

public class AlreadyChallengeMission extends RuntimeException {

    public AlreadyChallengeMission() {
        super("이미 참여 중인 미션입니다.");
    }
}
