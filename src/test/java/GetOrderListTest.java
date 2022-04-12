import api.client.OrderService;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.isA;

public class GetOrderListTest {

    @Test
    public void shouldReturnNotNullOrdersList() {
        OrderService.getList()
                .then().assertThat()
                .statusCode(200)
                .body("orders", isA(List.class));
    }

}
