package com.songpapeople.hashtagmap.instagram.api;

import com.songpapeople.hashtagmap.docs.instagram.InstagramApiDocumentation;
import com.songpapeople.hashtagmap.service.InstagramService;
import com.songpapeople.hashtagmap.taglevel.service.TagLevelCommandService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = InstagramSchedulerApiController.class)
public class InstagramApiControllerTest extends InstagramApiDocumentation {
    @MockBean
    private InstagramService instagramService;

    @MockBean
    private TagLevelCommandService tagLevelCommandService;

    @DisplayName("Instagram Scheduler를 시작")
    @Test
    void updateTest() throws Exception {
        doNothing().when(instagramService).update();
        doNothing().when(tagLevelCommandService).update();

        mockMvc.perform(put("/instagram-scheduler"))
                .andExpect(status().isOk())
                .andDo(getDocumentByUpdate());
    }
}
