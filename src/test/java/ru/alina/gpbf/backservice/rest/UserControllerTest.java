package ru.alina.gpbf.backservice.rest;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.alina.gpbf.backservice.ErrorData;
import ru.alina.gpbf.backservice.JsonUtil;
import ru.alina.gpbf.backservice.Matcher;
import ru.alina.gpbf.backservice.dto.ErrorResponseTo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



class UserControllerTest extends AbstractControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void createUser() throws Exception {
        this.mockMvc.perform(post("/v2/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userTelegramId\": 136, \"userName\": \"Alina\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isNoContent())
                .andExpect(content().string(Matchers.blankString()));
    }

    @Test
    void createUser_TelegramIdDuplicate() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(post("/v2/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userTelegramId\": 100,"+ " \"userName\": \"Alina\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isConflict()).andReturn();
        mvcResult.getResponse().setCharacterEncoding("utf-8");

        ErrorResponseTo errorResponseTo = JsonUtil.readValue(mvcResult.getResponse().getContentAsString(), ErrorResponseTo.class);

        Matcher.match(errorResponseTo, ErrorData.ERROR_CONFLICT_RESPONSE, "traceId");
    }

    @Test
    void createUser_BadJson_TelegramIdAbsent() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(post("/v2/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userName\": \"Alina\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isBadRequest()).andReturn();
         mvcResult.getResponse().setCharacterEncoding("utf-8");

        ErrorResponseTo errorResponseTo = JsonUtil.readValue(mvcResult.getResponse().getContentAsString(), ErrorResponseTo.class);

        Matcher.match(errorResponseTo, ErrorData.ERROR_VALIDATION_RESPONSE, "traceId");
    }

    @Test
    void createUser_BadJson_TelegramIdNull() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(post("/v2/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userTelegramId\": null, \"userName\": \"Alina\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isBadRequest()).andReturn();
        mvcResult.getResponse().setCharacterEncoding("utf-8");

        ErrorResponseTo errorResponseTo = JsonUtil.readValue(mvcResult.getResponse().getContentAsString(), ErrorResponseTo.class);

        Matcher.match(errorResponseTo, ErrorData.ERROR_VALIDATION_RESPONSE, "traceId");
    }

    @Test
    void createUser_UserNameDuplicate() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(post("/v2/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userTelegramId\": 136,"+ " \"userName\": \"Maks\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isConflict()).andReturn();
        mvcResult.getResponse().setCharacterEncoding("utf-8");

        ErrorResponseTo errorResponseTo = JsonUtil.readValue(mvcResult.getResponse().getContentAsString(), ErrorResponseTo.class);

        Matcher.match(errorResponseTo, ErrorData.ERROR_CONFLICT_RESPONSE, "traceId");
    }

    @Test
    void createUser_BadJson_userNameAbsent() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(post("/v2/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userTelegramId\": 136}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isBadRequest()).andReturn();
        mvcResult.getResponse().setCharacterEncoding("utf-8");

        ErrorResponseTo errorResponseTo = JsonUtil.readValue(mvcResult.getResponse().getContentAsString(), ErrorResponseTo.class);

        Matcher.match(errorResponseTo, ErrorData.ERROR_VALIDATION_RESPONSE, "traceId");
    }

    @Test
    void createUser_BadJson_userNameNull() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(post("/v2/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userTelegramId\": 136, \"userName\": null}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isBadRequest()).andReturn();
        mvcResult.getResponse().setCharacterEncoding("utf-8");

        ErrorResponseTo errorResponseTo = JsonUtil.readValue(mvcResult.getResponse().getContentAsString(), ErrorResponseTo.class);

        Matcher.match(errorResponseTo, ErrorData.ERROR_VALIDATION_RESPONSE, "traceId");
    }

    @Test
    void createUser_BadJson_userNameEmpty() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(post("/v2/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userTelegramId\": 136, \"userName\": \"   \"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isBadRequest()).andReturn();
        mvcResult.getResponse().setCharacterEncoding("utf-8");

        ErrorResponseTo errorResponseTo = JsonUtil.readValue(mvcResult.getResponse().getContentAsString(), ErrorResponseTo.class);

        Matcher.match(errorResponseTo, ErrorData.ERROR_VALIDATION_RESPONSE, "traceId");
    }
}