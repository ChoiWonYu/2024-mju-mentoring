package com.mju.mentoring.mission.domain.progress;

import com.mju.mentoring.global.domain.OperateType;
import com.mju.mentoring.global.domain.ResourceType;
import java.util.Optional;

public interface MissionProgressRepository {

    Optional<MissionProgress> findById(final Long id);

    MissionProgress save(final MissionProgress missionProgress);

    Optional<MissionProgress> findByMissionIdAndChallengerId(
        final Long missionId, final Long challengerId);

    Optional<MissionProgress> findByChallengeIdAndType(final Long challengerId,
        final OperateType getOperateType, final ResourceType resourceType);
}
