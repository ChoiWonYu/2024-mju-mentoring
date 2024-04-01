package com.mju.mentoring.mission.domain.progress;

import java.util.Optional;

public interface ProgressRepository {

    Optional<MissionProgress> findById(final Long id);

    MissionProgress save(final MissionProgress missionProgress);

    Optional<MissionProgress> findByMissionIdAndChallengerId(
        final Long missionId, final Long challengerId);
}
