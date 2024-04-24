package com.mju.mentoring.mission.ui.mission;

import static com.mju.mentoring.member.fixture.MemberFixture.id_없는_멤버_생성;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import com.mju.mentoring.global.BaseAcceptanceTest;
import com.mju.mentoring.member.domain.MemberRepository;
import com.mju.mentoring.member.domain.TokenManager;
import com.mju.mentoring.mission.application.mission.dto.MissionResponse;
import com.mju.mentoring.mission.application.mission.dto.MissionsResponse;
import com.mju.mentoring.mission.domain.mission.Mission;
import com.mju.mentoring.mission.domain.mission.MissionRepository;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class MissionAcceptanceTestFixture extends BaseAcceptanceTest {

    private static final String HEADER_NAME = "Authorization";
    private static final String AUTHORIZATION_PREFIX = "Bearer ";

    @Autowired
    private MissionRepository missionRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    protected TokenManager<Long> tokenManager;

    protected Mission 미션을_저장한다(final Mission mission) {
        return missionRepository.save(mission);
    }

    protected void 도전자를_생성한다() {
        memberRepository.save(id_없는_멤버_생성());
    }

    protected ExtractableResponse 모든_미션을_조회한다(final String token, final String url) {
        return RestAssured.given().log().all()
            .header(HEADER_NAME, AUTHORIZATION_PREFIX + token)
            .when()
            .get(url)
            .then().log().all()
            .extract();
    }

    protected void 여러_미션_검증(final ExtractableResponse response, final Mission mission) {
        int code = response.statusCode();
        MissionsResponse result = response.as(MissionsResponse.class);
        List<MissionResponse> missions = result.missions();

        미션_검증(missions.get(0), mission);
        assertSoftly(softly -> {
            softly.assertThat(code)
                .isEqualTo(HttpStatus.OK.value());
            softly.assertThat(missions.size())
                .isEqualTo(1);
        });
    }

    protected ExtractableResponse 미션을_신청한다(final String token, final String url) {
        return RestAssured.given().log().all()
            .header(HEADER_NAME, AUTHORIZATION_PREFIX + token)
            .when()
            .post(url)
            .then().log().all()
            .extract();
    }

    protected void 미션_신청_예외_검증(final ExtractableResponse response) {
        int code = response.statusCode();
        assertThat(code).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    private void 미션_검증(final MissionResponse result, final Mission mission) {
        assertSoftly(softly -> {
            softly.assertThat(result.goal()).isEqualTo(mission.getGoal());
            softly.assertThat(result.title()).isEqualTo(mission.getTitle());
            softly.assertThat(result.reward()).isEqualTo(mission.getReward());
            softly.assertThat(result.operateType()).isEqualTo(mission.getOperateType());
            softly.assertThat(result.resourceType()).isEqualTo(mission.getTargetType());
        });
    }
}
