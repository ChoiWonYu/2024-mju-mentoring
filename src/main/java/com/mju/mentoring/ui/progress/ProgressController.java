package com.mju.mentoring.ui.progress;

import com.mju.mentoring.mission.application.progress.ProgressService;
import com.mju.mentoring.mission.application.progress.dto.ProgressResponse;
import com.mju.mentoring.ui.progress.dto.ProgressResponses;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/progress")
public class ProgressController {

    private final ProgressService progressService;

    @GetMapping
    public ResponseEntity<ProgressResponses> findAll() {
        List<ProgressResponse> response = progressService.findAll();
        return ResponseEntity.ok(ProgressResponses.of(response));
    }
}
