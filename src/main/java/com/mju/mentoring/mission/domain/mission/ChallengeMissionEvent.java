package com.mju.mentoring.mission.domain.mission;


import com.mju.mentoring.global.event.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChallengeMissionEvent extends Event {

    private final Long challengerId;
    private final Long missionId;
    private final Long goal;
}
