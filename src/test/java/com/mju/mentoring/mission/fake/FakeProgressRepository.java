package com.mju.mentoring.mission.fake;

import com.mju.mentoring.mission.domain.progress.MissionProgress;
import com.mju.mentoring.mission.domain.progress.ProgressRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeProgressRepository implements ProgressRepository {

    private Map<Long, MissionProgress> db = new HashMap<>();
    private Long id = 1L;

    @Override
    public Optional<MissionProgress> findById(final Long id) {
        return db.keySet().stream()
            .map(key -> db.get(key))
            .filter(progress -> progress.getId().equals(id))
            .findAny();
    }

    @Override
    public MissionProgress save(final MissionProgress missionProgress) {
        MissionProgress progress = MissionProgress.builder()
            .id(id)
            .currentInfo(missionProgress.getCurrentInfo())
            .challengerId(missionProgress.getChallengerId())
            .missionId(missionProgress.getMissionId())
            .build();
        db.put(id++, progress);
        return progress;
    }

    @Override
    public Optional<MissionProgress> findByMissionIdAndChallengerId(final Long missionId,
        final Long challengerId) {
        return db.keySet().stream()
            .map(key -> db.get(key))
            .filter(progress -> progress.getMissionId().equals(missionId) &&
                progress.getChallengerId().equals(challengerId))
            .findAny();
    }
}
