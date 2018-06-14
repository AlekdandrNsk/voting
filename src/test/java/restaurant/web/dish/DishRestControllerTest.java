package restaurant.web.dish;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import restaurant.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static restaurant.TestUtil.*;
import static restaurant.UserTestData.USER;
import static restaurant.DishTestData.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class DishRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = DishRestController.REST_URL + "/";

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
