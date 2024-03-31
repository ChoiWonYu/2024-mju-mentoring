package com.mju.mentoring.mission.exception.exceptions;

public class NotFoundMissionException extends RuntimeException {

    public NotFoundMissionException(final Long missionId) {
        super("id가 " + missionId + "인 미션을 찾을 수 없습니다.");
    }
}
