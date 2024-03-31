package com.mju.mentoring.ui.mission;

import com.mju.mentoring.mission.application.mission.MissionService;
import com.mju.mentoring.mission.application.mission.dto.MissionResponse;
import com.mju.mentoring.mission.application.mission.dto.MissionsResponse;
import com.mju.mentoring.mission.domain.mission.Mission;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @GetMapping
    public ResponseEntity<MissionsResponse> findAllMissions() {
        List<Mission> missions = missionService.findAll();
        List<MissionResponse> missionsResponse = missions.stream()
            .map(MissionResponse::from)
            .toList();

        return ResponseEntity.ok(MissionsResponse.of(missionsResponse));
    }
}
