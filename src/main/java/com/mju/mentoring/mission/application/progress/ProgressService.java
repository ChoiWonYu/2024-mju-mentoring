package com.mju.mentoring.mission.application.progress;

import com.mju.mentoring.mission.domain.progress.MissionProgress;
import com.mju.mentoring.mission.domain.progress.ProgressRepository;
import com.mju.mentoring.mission.exception.exceptions.AlreadyChallengeMission;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProgressService {

    private final ProgressRepository progressRepository;

    public void challengeMission(final Long challengerId, final Long missionId, final Long goal) {
        Optional<MissionProgress> missionProgress = progressRepository.findByMissionIdAndChallengerId(
            challengerId, missionId);

        if (!missionProgress.isEmpty()) {
            throw new AlreadyChallengeMission();
        }

        progressRepository.save(MissionProgress.of(goal, missionId, challengerId));
    }

    public void increaseTargetProgress(final Long challengerId, final Long missionId) {
        progressRepository.findByMissionIdAndChallengerId(
            challengerId, missionId).ifPresent(MissionProgress::increaseCount);
    }
}
