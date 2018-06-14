package restaurant.web.dish;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.ResultActions;
import restaurant.model.Dish;
import restaurant.repository.datajpa.DishRepository;
import restaurant.web.AbstractControllerTest;
import restaurant.web.json.JsonUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static restaurant.TestUtil.*;
import static restaurant.UserTestData.ADMIN;
import static restaurant.UserTestData.USER;
import static restaurant.web.DishTestData.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class DishRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL_ADMIN = DishAdminRestController.REST_URL + "/";
    private static final String REST_URL = DishRestController.REST_URL + "/";

    @Autowired
    private DishRepository repository;


    @Test
    public void testCreateWithLocation() throws Exception {
        Dish created = getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL_ADMIN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created))
                .with(userHttpBasic(ADMIN)));

        Dish returned = readFromJson(action, Dish.class);
        created.setId(returned.getId());

        assertMatch(returned, created);
        assertMatch(repository.findAllByDate(DATEOFVOTE), DISH_1, DISH_2, DISH_3, DISH_4, DISH_5, DISH_6, created);
    }

    @Test
    public void testUpdate() throws Exception {
        Dish updated = getUpdated();

        mockMvc.perform(put(REST_URL_ADMIN + DISH_3_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk());
        assertMatch(repository.findById(DISH_3_ID).orElse(null), updated);
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL_ADMIN + DISH_2_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent());
        assertMatch(repository.findAllByDate(DATEOFVOTE), DISH_1, DISH_3, DISH_4, DISH_5, DISH_6);
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL_ADMIN + DISH_1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(DISH_1));
    }

    @Test
    public void testGetAllByDay() throws Exception {
        mockMvc.perform(get(REST_URL)
                .param("date", "2018-06-07")
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJsonArray(DISH_1, DISH_2, DISH_3, DISH_4, DISH_5, DISH_6));
    }
}
