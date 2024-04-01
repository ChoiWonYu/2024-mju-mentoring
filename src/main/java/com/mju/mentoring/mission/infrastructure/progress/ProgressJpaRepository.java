package com.mju.mentoring.mission.infrastructure.progress;

import com.mju.mentoring.mission.domain.progress.MissionProgress;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgressJpaRepository extends JpaRepository<MissionProgress, Long> {

    Optional<MissionProgress> findById(final Long id);

    MissionProgress save(final MissionProgress missionProgress);

    Optional<MissionProgress> findByMissionIdAndChallengerId(
        final Long missionId, final Long challengerId);
}
