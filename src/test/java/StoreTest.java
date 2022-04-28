import org.junit.Test;

import java.util.List;

public class StoreTest {
    StoreRequest storeRequest = new StoreRequest();

    @Test
    public void storeTest(){

        System.out.println("----------Create Order----------");
        List<String> orderList = storeRequest.createOrder();
        String orderId = orderList.get(0);

        System.out.println("----------Delete Order----------");
        storeRequest.deleteOrder(orderId);

        System.out.println("----------Get Inventory----------");
        storeRequest.getInventory();
    }

    @Test
    public void negativeStoreTest(){

        System.out.println("----------Get Order By Id----------");
        storeRequest.getOrderById(1);
    }
}
