package com.mju.mentoring.mission.infrastructure.progress;

import com.mju.mentoring.global.domain.OperateType;
import com.mju.mentoring.global.domain.ResourceType;
import com.mju.mentoring.mission.domain.progress.MissionProgress;
import com.mju.mentoring.mission.domain.progress.MissionProgressRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MissionProgressRepositoryImpl implements MissionProgressRepository {

    private final MissionProgressJpaRepository missionProgressJpaRepository;
    private final MissionProgressQueryDslRepository queryDslRepository;

    @Override
    public Optional<MissionProgress> findById(final Long id) {
        return missionProgressJpaRepository.findById(id);
    }

    @Override
    public MissionProgress save(final MissionProgress missionProgress) {
        return missionProgressJpaRepository.save(missionProgress);
    }

    @Override
    public Optional<MissionProgress> findByMissionIdAndChallengerId(final Long missionId,
        final Long challengerId) {
        return missionProgressJpaRepository.findByMissionIdAndChallengerId(missionId, challengerId);
    }

    @Override
    public Optional<MissionProgress> findByChallengeIdAndType(final Long challengerId,
        final OperateType operateType, final ResourceType resourceType) {
        return queryDslRepository.findByChallengeIdAndType(challengerId, operateType, resourceType);
    }
}
