package com.mju.mentoring.mission.infrastructure.progress;

import com.mju.mentoring.mission.domain.progress.MissionProgress;
import com.mju.mentoring.mission.domain.progress.ProgressRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProgressRepositoryImpl implements ProgressRepository {

    private final ProgressJpaRepository progressJpaRepository;

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
}
