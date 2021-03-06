package restaurant.web.vote;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.ResultActions;
import restaurant.model.Vote;
import restaurant.repository.datajpa.VoteRepository;
import restaurant.web.AbstractControllerTest;
import restaurant.web.json.JsonUtil;

import java.time.LocalDate;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static restaurant.RestaurantTestData.RESTAURANT_1;
import static restaurant.RestaurantTestData.RESTAURANT_2;
import static restaurant.TestUtil.*;
import static restaurant.UserTestData.USER;
import static restaurant.VoteTestData.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class VoteRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = VoteRestController.REST_URL + "/";

    @Autowired
    private VoteRepository repository;

    @Test
    public void testGetAllByDay() throws Exception {
        mockMvc.perform(get(REST_URL)
                .param("date", "2018-06-07")
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJsonArray(VOTE_1, VOTE_2));
    }

    @Test
    public void testCreateWithLocation() throws Exception {
        Vote created = getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(RESTAURANT_1))
                .with(userHttpBasic(USER)));

        Vote returned = readFromJson(action, Vote.class);
        created.setId(returned.getId());

        assertMatch(returned, created);
        assertMatch(repository.findAllByDate(LocalDate.now()), Collections.singletonList(created));
    }

    @Test
    public void update() throws Exception {
        Vote updated = getUpdated();
        mockMvc.perform(put(REST_URL + VOTE_1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(RESTAURANT_2))
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk());

        assertMatch(repository.findById(VOTE_1_ID).orElse(null), updated);
    }
}
