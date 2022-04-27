import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class StoreTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @Test
    public void storeTest(){

        System.out.println("----------Create Order----------");
        List<String> orderList = StoreRequest.createOrder();
        String orderId = orderList.get(0);

        System.out.println("----------Delete Order----------");
        StoreRequest.deleteOrder(orderId);

        System.out.println("----------Get Inventory----------");
        StoreRequest.getInventory();
    }

    @Test
    public void negativeStoreTest(){

        System.out.println("----------Get Order By Id----------");
        StoreRequest.getOrderById(1);
    }
}
