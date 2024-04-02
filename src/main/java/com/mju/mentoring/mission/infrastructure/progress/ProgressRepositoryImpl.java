package com.mju.mentoring.mission.infrastructure.progress;

import com.mju.mentoring.global.domain.OperateType;
import com.mju.mentoring.global.domain.ResourceType;
import com.mju.mentoring.mission.domain.progress.MissionProgress;
import com.mju.mentoring.mission.domain.progress.ProgressRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProgressRepositoryImpl implements ProgressRepository {

    private final ProgressJpaRepository progressJpaRepository;
    private final ProgressQueryDslRepository queryDslRepository;

    @Override
    public Optional<MissionProgress> findById(final Long id) {
        return progressJpaRepository.findById(id);
    }

    @Override
    public MissionProgress save(final MissionProgress missionProgress) {
        return progressJpaRepository.save(missionProgress);
    }

    @Override
    public Optional<MissionProgress> findByMissionIdAndChallengerId(final Long missionId,
        final Long challengerId) {
        return progressJpaRepository.findByMissionIdAndChallengerId(missionId, challengerId);
    }

    @Override
    public Optional<MissionProgress> findByChallengeIdAndType(final Long challengerId,
        final OperateType operateType, final ResourceType resourceType) {
        return queryDslRepository.findByChallengeIdAndType(challengerId, operateType, resourceType);
    }
}
