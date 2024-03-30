package com.mju.mentoring.mission.domain.progress;

public enum RewardStatus {

    RECEIVED, NOT_AVAILABLE, WAITING;

    public boolean canReceive() {
        return this.equals(WAITING);
    }
}
