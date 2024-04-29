package com.mju.mentoring.mission.infrastructure.progress.dto;

import com.mju.mentoring.mission.application.progress.dto.ProgressResponse;

public record CurrentProgress(String missionTitle, Long currentCount, Long goal, Double progress) {

    public ProgressResponse toResponse() {
        return new ProgressResponse(missionTitle, currentCount, goal, progress);
    }
}
