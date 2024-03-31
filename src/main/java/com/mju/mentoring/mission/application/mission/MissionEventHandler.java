package com.mju.mentoring.mission.application.mission;

import com.mju.mentoring.board.domain.BoardOperateEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MissionEventHandler {

    private final MissionService missionService;

    @EventListener
    public void findTargetMission(final BoardOperateEvent event) {
        missionService.findTargetMission(
            event.getMemberId(), event.getOperateType(), event.getResourceType());
    }
}
