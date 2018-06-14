package restaurant.web.restaurant;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import restaurant.TestUtil;
import restaurant.model.Role;
import restaurant.model.User;
import restaurant.repository.datajpa.UserRepository;
import restaurant.web.AbstractControllerTest;
import restaurant.web.json.JsonUtil;
import restaurant.web.user.ProfileRestController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static restaurant.TestUtil.userHttpBasic;
import static restaurant.UserTestData.*;


public class ProfileRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = ProfileRestController.REST_URL + '/';

    @Autowired
    private UserRepository repository;

    @Test
    public void testGet() throws Exception {
        TestUtil.print(
                mockMvc.perform(get(REST_URL)
                        .with(userHttpBasic(USER)))
                        .andExpect(status().isOk())
                        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                        .andExpect(contentJson(USER))
        );
    }


    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isNoContent());
        assertMatch(repository.findAll(), ADMIN);
    }

    @Test
    public void testUpdate() throws Exception {
        User updated = new User(USER_ID, "UpdatedName", "newemail@ya.ru", "password", Role.ROLE_USER);
        mockMvc.perform(put(REST_URL).contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER))
                .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isOk());

        assertMatch(repository.findById(USER_ID).orElse(null), updated);
    }

}