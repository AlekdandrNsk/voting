package restaurant.web.restaurant;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.ResultActions;
import restaurant.model.Restaurant;
import restaurant.repository.datajpa.RestaurantRepository;
import restaurant.web.AbstractControllerTest;
import restaurant.web.json.JsonUtil;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static restaurant.TestUtil.*;
import static restaurant.UserTestData.ADMIN;
import static restaurant.RestaurantTestData.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class RestaurantAdminRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = RestaurantAdminRestController.REST_URL + "/";

    @Autowired
    private RestaurantRepository repository;

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJsonArray(RESTAURANT_1, RESTAURANT_2));
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT_1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(RESTAURANT_1));
    }

    @Test
    public void testCreateWithLocation() throws Exception {
        Restaurant created = getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created))
                .with(userHttpBasic(ADMIN)));

        Restaurant returned = readFromJson(action, Restaurant.class);
        created.setId(returned.getId());

        assertMatch(returned, created);
        assertMatch(repository.findAll(), RESTAURANT_1, RESTAURANT_2, created);
    }

    @Test
    public void testUpdate() throws Exception {
        Restaurant updated = getUpdated();

        mockMvc.perform(put(REST_URL + RESTAURANT_2_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk());

        assertMatch(repository.findById(RESTAURANT_2_ID).orElse(null), updated);
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + RESTAURANT_1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent());
        assertMatch(repository.findAll(), Collections.singletonList(RESTAURANT_2));
    }
}
