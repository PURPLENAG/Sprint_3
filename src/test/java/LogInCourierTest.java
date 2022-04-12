import api.client.CourierService;
import api.model.RegisterCourierDto;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import util.DataGenerator;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.equalTo;

public class LogInCourierTest {

    RegisterCourierDto courier;

    /**
     * create courier before each test
     */
    @Before
    public void before() {
        courier = DataGenerator.generateRegisterCourierDto();
        CourierService.register(courier)
                .then().assertThat()
                .statusCode(201);
    }


    /**
     * remove courier after each test
     */
    @After
    public void after() {
        int id = CourierService.login(courier.getLogin(), courier.getPassword())
                .then().assertThat()
                .statusCode(200)
                .extract().path("id");
        CourierService.delete(id)
                .then().assertThat()
                .statusCode(200)
                .body("ok", equalTo(true));
    }

    @Test
    public void shouldCourierLogIn() {
        CourierService.login(courier.getLogin(), courier.getPassword())
                .then().assertThat()
                .statusCode(200)
                .body("id", any(Integer.class));
    }

    @Test
    public void shouldNotCourierLogInWithoutLogin() {
        CourierService.login(null, courier.getPassword())
                .then().assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    public void shouldNotCourierLogInWithoutPassword() {
        CourierService.login(courier.getLogin(), null)
                .then().assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    public void shouldNotCourierLogInWithoutLoginAndPassword() {
        CourierService.login(null, null)
                .then().assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    public void shouldNotCourierLogInWithIncorrectPassword() {
        CourierService.login(courier.getLogin(), RandomStringUtils.randomAlphabetic(10))
                .then().assertThat()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    public void shouldNotCourierLogInWithIncorrectLogin() {
        CourierService.login(RandomStringUtils.randomAlphabetic(10), courier.getPassword())
                .then().assertThat()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    public void shouldNotUnregisteredCourierLogIn() {
        CourierService.login(DataGenerator.generateLoginCourierDto())
                .then().assertThat()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));
    }
}
