package com.mju.mentoring.mission.domain.mission;

import com.mju.mentoring.global.event.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MissionFoundEvent extends Event {

    private final Long missionId;
    private final Long challengerId;
}
