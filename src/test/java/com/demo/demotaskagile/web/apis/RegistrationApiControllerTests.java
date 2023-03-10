package com.demo.demotaskagile.web.apis;

import com.demo.demotaskagile.config.SecurityConfiguration;
import com.demo.demotaskagile.domain.application.UserService;
import com.demo.demotaskagile.domain.model.user.EmailAddressExistsException;
import com.demo.demotaskagile.domain.model.user.UsernameExistsException;
import com.demo.demotaskagile.utils.JsonUtils;
import com.demo.demotaskagile.web.payload.RegistrationPayload;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ContextConfiguration(classes = {SecurityConfiguration.class, RegistrationApiController.class})
@WebMvcTest
public class RegistrationApiControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService serviceMock;

    @Test
    public void register_blankPayload_shouldFailAndReturn400() throws Exception {
        mvc.perform(post("/api/registrations"))
                .andExpect(status().is(400));
    }

    @Test
    public void register_existedUsername_shouldFailAndReturn400() throws Exception {
        RegistrationPayload payload = new RegistrationPayload();
        payload.setUsername("exist");
        payload.setEmailAddress("test@taskagile.com");
        payload.setPassword("MyPassword!");
        payload.setFirstName("User");
        payload.setLastName("Test");

        doThrow(UsernameExistsException.class)
                .when(serviceMock)
                .register(payload.toCommand());

        mvc.perform(
                        post("/api/registrations")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JsonUtils.toJson(payload)))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.message").value("Username already exists"));
    }

    @Test
    public void register_existedEmailAddress_shouldFailAndReturn400() throws Exception {
        RegistrationPayload payload = new RegistrationPayload();
        payload.setUsername("test");
        payload.setEmailAddress("exist@taskagile.com");
        payload.setPassword("MyPassword!");
        payload.setFirstName("User");
        payload.setLastName("Test");

        doThrow(EmailAddressExistsException.class)
                .when(serviceMock)
                .register(payload.toCommand());

        mvc.perform(
                        post("/api/registrations")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JsonUtils.toJson(payload)))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.message").value("Email address already exists"));
    }

    @Test
    public void register_validPayload_shouldSucceedAndReturn201() throws Exception {
        RegistrationPayload payload = new RegistrationPayload();
        payload.setUsername("sunny");
        payload.setEmailAddress("sunny@taskagile.com");
        payload.setPassword("MyPassword!");
        payload.setFirstName("User");
        payload.setLastName("Test");

        doNothing().when(serviceMock)
                .register(payload.toCommand());

        mvc.perform(
                        post("/api/registrations")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JsonUtils.toJson(payload)))
                .andExpect(status().is(201));
    }
}
