import api.client.CourierService;
import api.model.RegisterCourierDto;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;
import util.DataGenerator;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;

public class CreateCourierTest {
    List<RegisterCourierDto> createdCouriers = new ArrayList<>();

    /**
     * register new courier and save registration information
     */
    private Response register(RegisterCourierDto dto) {
        var response = CourierService.register(dto);
        if (response.statusCode() == 201) {
            createdCouriers.add(dto);
        }
        return response;
    }

    /**
     * remove all created couriers after each test
     */
    @After
    public void after() {
        for (var dto : createdCouriers) {
            int id = CourierService.login(dto.getLogin(), dto.getPassword())
                    .then().assertThat()
                    .statusCode(200)
                    .extract().path("id");
            CourierService.delete(id)
                    .then().assertThat()
                    .statusCode(200)
                    .body("ok", equalTo(true));
        }
    }

    @Test
    public void shouldCreateCourier() {
        register(DataGenerator.generateRegisterCourierDto())
                .then().assertThat()
                .statusCode(201)
                .body("ok", equalTo(true));
    }

    @Test
    public void shouldCreateCourierWithoutFirstName() {
        var dto = DataGenerator.generateRegisterCourierDto();
        dto.setFirstName(null);

        register(dto)
                .then().assertThat()
                .statusCode(201)
                .body("ok", equalTo(true));
    }

    @Test
    public void shouldNotCreateCourierWithoutLogin() {
        var dto = DataGenerator.generateRegisterCourierDto();
        dto.setLogin(null);

        register(dto)
                .then().assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    public void shouldNotCreateCourierWithoutPassword() {
        var dto = DataGenerator.generateRegisterCourierDto();
        dto.setPassword(null);

        register(dto)
                .then().assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    public void shouldNotCreateTwoEqualsCouriers() {
        var dto = DataGenerator.generateRegisterCourierDto();
        register(dto)
                .then().assertThat()
                .statusCode(201)
                .body("ok", equalTo(true));
        register(dto)
                .then().assertThat()
                .statusCode(409)
                .body("message", equalTo("Этот логин уже используется"));
    }

    @Test
    public void shouldNotCreateCourierWithAlreadyExistingLogin() {
        var dto1 = DataGenerator.generateRegisterCourierDto();
        register(dto1)
                .then().assertThat()
                .statusCode(201)
                .body("ok", equalTo(true));


        var dto2 = DataGenerator.generateRegisterCourierDto();
        dto2.setLogin(dto1.getLogin());
        register(dto2)
                .then().assertThat()
                .statusCode(409)
                .body("message", equalTo("Этот логин уже используется"));
    }
}
