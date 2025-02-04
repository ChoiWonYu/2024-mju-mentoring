package com.mju.mentoring.board.ui;

import static com.mju.mentoring.member.fixture.MemberFixture.id_없는_멤버_생성;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import com.mju.mentoring.board.application.dto.BoardCreateRequest;
import com.mju.mentoring.board.application.dto.BoardDeleteRequest;
import com.mju.mentoring.board.application.dto.BoardUpdateRequest;
import com.mju.mentoring.board.domain.Board;
import com.mju.mentoring.board.domain.BoardRepository;
import com.mju.mentoring.board.ui.dto.BoardResponse;
import com.mju.mentoring.board.ui.dto.BoardsResponse;
import com.mju.mentoring.board.ui.dto.CursorInfo;
import com.mju.mentoring.global.BaseAcceptanceTest;
import com.mju.mentoring.member.domain.MemberRepository;
import com.mju.mentoring.member.domain.TokenManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class BoardAcceptanceTestFixture extends BaseAcceptanceTest {

    private static final String HEADER_NAME = "Authorization";
    private static final String AUTHORIZATION_PREFIX = "Bearer ";

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    protected TokenManager<Long> tokenManager;

    protected Board 게시글을_저장한다(final Board board) {
        return boardRepository.save(board);
    }

    protected BoardCreateRequest 게시글_생성_요청() {
        return new BoardCreateRequest("title", "content");
    }

    protected void 작성자를_생성한다() {
        memberRepository.save(id_없는_멤버_생성());
    }

    protected ExtractableResponse 게시글을_생성한다(final String token, final BoardCreateRequest request,
        final String url) {
        return RestAssured.given().log().all()
            .body(request)
            .contentType(ContentType.JSON)
            .header(HEADER_NAME, AUTHORIZATION_PREFIX + token)
            .when()
            .post(url)
            .then().log().all()
            .extract();
    }

    protected void 게시글_생성을_검증한다(final ExtractableResponse response) {
        int code = response.statusCode();

        assertThat(code).isEqualTo(HttpStatus.CREATED.value());
    }

    protected ExtractableResponse 게시글을_단건_조회한다(final String token, final String url) {
        return RestAssured.given().log().all()
            .header(HEADER_NAME, AUTHORIZATION_PREFIX + token)
            .when()
            .get(url)
            .then().log().all()
            .extract();
    }

    protected void 단건_게시글_조회_검증(final ExtractableResponse response, final Board board) {
        int code = response.statusCode();
        BoardResponse result = response.as(BoardResponse.class);

        게시물_검증(result, board);
        assertSoftly(softly -> {
            softly.assertThat(result.view()).isEqualTo(1);
            softly.assertThat(code).isEqualTo(HttpStatus.OK.value());
        });
    }

    protected ExtractableResponse 모든_게시물을_조회한다(final String token, final String url) {
        return RestAssured.given().log().all()
            .header(HEADER_NAME, AUTHORIZATION_PREFIX + token)
            .when()
            .get(url)
            .then().log().all()
            .extract();
    }

    protected void 여러_게시물_조회_검증(final ExtractableResponse response, final Board board1,
        final Board board2) {

        int code = response.statusCode();
        BoardsResponse results = response.as(BoardsResponse.class);
        List<BoardResponse> resultData = results.data();
        CursorInfo cursor = results.cursor();

        BoardResponse firstBoard = resultData.get(1);
        BoardResponse secondBoard = resultData.get(0);

        게시물_검증(firstBoard, board1);
        게시물_검증(secondBoard, board2);

        assertSoftly(softly -> {
            softly.assertThat(code).isEqualTo(HttpStatus.OK.value());
            softly.assertThat(resultData.size()).isEqualTo(2);
            softly.assertThat(cursor.lastId()).isEqualTo(1L);
            softly.assertThat(cursor.hasMorePage()).isFalse();
        });
    }

    private void 게시물_검증(BoardResponse result, Board board) {
        assertSoftly(softly -> {
            softly.assertThat(board.getId()).isEqualTo(result.boardId());
            softly.assertThat(board.getTitle()).isEqualTo(result.title());
            softly.assertThat(board.getContent()).isEqualTo(result.content());
            softly.assertThat(board.getWriterName()).isEqualTo(result.writer());
        });
    }

    protected ExtractableResponse 게시글을_수정한다(final String token, final BoardUpdateRequest request,
        final String url) {
        return RestAssured.given()
            .body(request).given().log().all()
            .header(HEADER_NAME, AUTHORIZATION_PREFIX + token)
            .contentType(ContentType.JSON)
            .when()
            .put(url)
            .then().log().all()
            .extract();
    }

    protected void 게시물_수정_실패_검증(final ExtractableResponse response) {
        int code = response.statusCode();

        assertThat(code).isEqualTo(HttpStatus.FORBIDDEN.value());
    }

    protected BoardUpdateRequest 게시글_수정_요청() {
        return new BoardUpdateRequest("update title", "update content");
    }

    protected void 게시물_수정_검증(final ExtractableResponse response) {
        int code = response.statusCode();

        assertThat(code).isEqualTo(HttpStatus.OK.value());
    }

    protected ExtractableResponse 단건_게시물을_삭제한다(final String token, final String url) {
        return RestAssured.given().log().all()
            .header(HEADER_NAME, AUTHORIZATION_PREFIX + token)
            .when()
            .delete(url)
            .then()
            .extract();
    }

    protected void 게시물_삭제_검증(final ExtractableResponse response) {
        int code = response.statusCode();
        assertThat(code).isEqualTo(HttpStatus.OK.value());
    }

    protected void 게시물_삭제_실패_검증(final ExtractableResponse response) {
        int code = response.statusCode();
        assertThat(code).isEqualTo(HttpStatus.FORBIDDEN.value());
    }

    protected BoardDeleteRequest 여러건_게시글_삭제_요청() {
        return new BoardDeleteRequest(List.of(1L, 2L));
    }

    protected ExtractableResponse 여러_게시글을_삭제한다(final String token, final String url,
        final BoardDeleteRequest request) {
        return RestAssured.given().log().all()
            .header(HEADER_NAME, AUTHORIZATION_PREFIX + token)
            .body(request)
            .contentType(ContentType.JSON)
            .when()
            .delete(url)
            .then()
            .extract();
    }

    protected void 게시글_조회수_중복_방지_검증(final ExtractableResponse response1,
        final ExtractableResponse response2) {
        BoardResponse result1 = response1.as(BoardResponse.class);
        BoardResponse result2 = response2.as(BoardResponse.class);

        assertThat(result1.view()).isEqualTo(result2.view());
    }
}
