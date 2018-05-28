package restaurant.web;

import org.junit.*;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import restaurant.model.Dish;
import restaurant.web.dish.DishRestController;

import java.util.Arrays;
import java.util.Collection;

public class InMemoryDishRestControllerTest {
    private static ConfigurableApplicationContext appCtx;
    private static DishRestController controller;

    @BeforeClass
    public static void beforeClass() {
        appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        System.out.println("\n" + Arrays.toString(appCtx.getBeanDefinitionNames()) + "\n");
        controller = appCtx.getBean(DishRestController.class);
    }

    @AfterClass
    public static void afterClass() {
        appCtx.close();
    }

//    @Before
//    public void setUp() throws Exception {
//        // re-initialize
//        InMemoryUserRepositoryImpl repository = appCtx.getBean(InMemoryUserRepositoryImpl.class);
//        repository.init();
//    }

    @Test
    public void testGetAll() throws Exception {
        //controller.delete(UserTestData.USER_ID);
        Collection<Dish> menu = controller.getAll();
        Assert.assertEquals(menu.size(), 9);
        //Assert.assertEquals(users.iterator().next(), ADMIN)
        ;
    }

//    @Test(expected = NotFoundException.class)
//    public void testDeleteNotFound() throws Exception {
//        controller.delete(10);
//    }
}