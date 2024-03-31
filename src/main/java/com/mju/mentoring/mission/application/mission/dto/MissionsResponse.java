package com.mju.mentoring.mission.application.mission.dto;

import java.util.List;

public record MissionsResponse(List<MissionResponse> missions) {

    public static MissionsResponse of(List<MissionResponse> missions) {
        return new MissionsResponse(missions);
    }
}
