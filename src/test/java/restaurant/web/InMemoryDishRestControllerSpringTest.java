package restaurant.web;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import restaurant.model.Dish;
import restaurant.web.dish.DishRestController;

import java.util.Collection;

@ContextConfiguration("classpath:spring/spring-app.xml")
@RunWith(SpringRunner.class)
public class InMemoryDishRestControllerSpringTest {

    @Autowired
    private DishRestController controller;

//    @Autowired
//    private InMemoryUserRepositoryImpl repository;

//    @Before
//    public void setUp() throws Exception {
//        repository.init();
//    }

    @Test
    public void testDelete() throws Exception {
        Collection<Dish> menu = controller.getAll();
        Assert.assertEquals(menu.size(), 9);
    }

//    @Test(expected = NotFoundException.class)
//    public void testDeleteNotFound() throws Exception {
//        controller.delete(10);
//    }
}
