package com.mju.mentoring.mission.application.progress.dto;

public record ProgressResponse(String missionTitle, Long currentCount, Long goal, Double progress) {

}
