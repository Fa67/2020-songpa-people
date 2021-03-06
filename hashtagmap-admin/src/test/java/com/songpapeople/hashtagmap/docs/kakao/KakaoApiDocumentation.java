package com.songpapeople.hashtagmap.docs.kakao;

import com.songpapeople.hashtagmap.docs.ApiDocument;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.songpapeople.hashtagmap.docs.ApiDocumentUtils.getDocumentRequest;
import static com.songpapeople.hashtagmap.docs.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;

public class KakaoApiDocumentation extends ApiDocument {
    protected RestDocumentationResultHandler getDocumentByStartAndStop(String command) {
        return document("kakao/scheduler/" + command,
                getDocumentRequest(),
                getDocumentResponse()
        );
    }

    protected RestDocumentationResultHandler getDocumentByGetStatus() {
        return document("kakao/scheduler/status",
                getDocumentRequest(),
                getDocumentResponse(),
                requestParameters(
                        parameterWithName("name").description("스케줄러의 이름")
                ),
                responseFields(
                        fieldWithPath("data").type(JsonFieldType.BOOLEAN)
                                .description("스케줄러의 상태 (지정된 주기가 되면 데이터를 수집하는 상태)"),
                        fieldWithPath("code").type(JsonFieldType.NULL).description("에러 코드"),
                        fieldWithPath("message").type(JsonFieldType.NULL).description("에러 메세지")
                ));
    }

    protected RestDocumentationResultHandler getDocumentByChangePeriod() {
        return document("kakao/scheduler/period/put",
                getDocumentRequest(),
                getDocumentResponse()
        );
    }

    protected RestDocumentationResultHandler getDocumentByShowPeriodHistory() {
        return document("kakao/scheduler/period/get",
                getDocumentRequest(),
                getDocumentResponse(),
                responseFields(
                        fieldWithPath("data[0].id").type(JsonFieldType.NUMBER).description("데이터 베이스 Id"),
                        fieldWithPath("data[0].expression").type(JsonFieldType.STRING).description("변경된 주기 (cron)"),
                        fieldWithPath("data[0].member").type(JsonFieldType.STRING).description("수정한 사람"),
                        fieldWithPath("data[0].changedDate").type(JsonFieldType.STRING).description("수정한 날짜"),
                        fieldWithPath("code").type(JsonFieldType.NULL).description("에러 코드"),
                        fieldWithPath("message").type(JsonFieldType.NULL).description("에러 메세지")
                )
        );
    }

    protected RestDocumentationResultHandler getDocumentByChangeAutoRunnable() {
        return document("kakao/scheduler/auto/toggle",
                getDocumentRequest(),
                getDocumentResponse(),
                requestFields(
                        fieldWithPath("name").type(JsonFieldType.STRING).description("카카오 스케쥴 이름")
                ),
                responseFields(
                        fieldWithPath("data").type(JsonFieldType.NULL).description("반환 값"),
                        fieldWithPath("code").type(JsonFieldType.NULL).description("에러 코드"),
                        fieldWithPath("message").type(JsonFieldType.NULL).description("에러 메세지")
                )
        );
    }

    protected RestDocumentationResultHandler getDocumentByGetAutoRunnable() {
        return document("kakao/scheduler/auto/status",
                getDocumentRequest(),
                getDocumentResponse(),
                requestParameters(
                        parameterWithName("name").description("카카오 스케쥴 이름")
                ),
                responseFields(
                        fieldWithPath("data").type(JsonFieldType.BOOLEAN).description("자동실행 상태 값"),
                        fieldWithPath("code").type(JsonFieldType.NULL).description("에러 코드"),
                        fieldWithPath("message").type(JsonFieldType.NULL).description("에러 메세지")
                )
        );
    }
}
