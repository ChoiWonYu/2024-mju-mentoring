package com.mju.mentoring.mission.domain.mission;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

import com.mju.mentoring.mission.fake.FakeMissionRepository;
import com.mju.mentoring.mission.fixture.MissionFixture;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MissionRepositoryTest {

    private FakeMissionRepository missionRepository;

    @BeforeEach
    void init() {
        missionRepository = new FakeMissionRepository();
    }

    @Test
    void 미션_전체_조회_테스트() {
        // given
        Mission mission = MissionFixture.id_없는_미션_생성();

        // when
        missionRepository.save(mission);
        List<Mission> missions = missionRepository.findAll();

        // then
        assertSoftly(softly -> {
            softly.assertThat(missions)
                .isNotEmpty();
            softly.assertThat(missions.get(0).getId())
                .isEqualTo(1L);
            softly.assertThat(missions.get(0))
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(mission);
        });
    }

    @Test
    void 미션_단건_조회_테스트() {
        // given
        Mission mission = MissionFixture.id_없는_미션_생성();

        // when
        missionRepository.save(mission);
        Optional<Mission> targetMission = missionRepository.findByOperateTypeAndResourceType(
            mission.getOperateType(), mission.getTargetType());

        // then
        assertSoftly(softly -> {
            softly.assertThat(targetMission)
                .isNotEmpty();

            softly.assertThat(targetMission.get())
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(mission);
        });
    }
}
