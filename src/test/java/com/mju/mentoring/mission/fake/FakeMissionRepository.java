package com.mju.mentoring.mission.fake;

import com.mju.mentoring.global.domain.OperateType;
import com.mju.mentoring.global.domain.ResourceType;
import com.mju.mentoring.mission.domain.mission.Mission;
import com.mju.mentoring.mission.domain.mission.MissionRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FakeMissionRepository implements MissionRepository {

    Map<Long, Mission> db = new HashMap<>();
    private Long id = 1L;

    public Mission save(final Mission mission) {
        Mission saveMission = mission.builder()
            .id(id)
            .title(mission.getTitle())
            .reward(mission.getReward())
            .goal(mission.getGoal())
            .targetType(mission.getTargetType())
            .operateType(mission.getOperateType())
            .build();
        db.put(id++, saveMission);
        return saveMission;
    }

    @Override
    public List<Mission> findAll() {
        return db.values().stream().toList();
    }

    @Override
    public Optional<Mission> findByOperateTypeAndResourceType(
        final OperateType operateType, final ResourceType resourceType) {
        return db.keySet().stream()
            .map(db::get)
            .filter(mission -> mission.hasSameInfo(resourceType, operateType))
            .findAny();
    }
}
