import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

public class StoreRequest {
    RequestHelper requestHelper = new RequestHelper();

    public void getInventory() {
        RestAssured.given().spec(requestHelper.requestSpecification)
                .when().get("v2/store/inventory")
                .then().spec(requestHelper.responseSpecification).log().all();
    }

    public void getOrderById(int orderId) {
        RestAssured.given().spec(requestHelper.requestSpecification)
                .when().get("v2/store/order/" + orderId)
                .then().spec(requestHelper.negativeSpecification).log().all();
    }

    public void deleteOrder(String orderId) {

        RestAssured.given().spec(requestHelper.requestSpecification)
                .when().delete("v2/store/order/" + orderId)
                .then().spec(requestHelper.responseSpecification).log().all();
    }

    public List<String> createOrder() {
        List<String> OrderIdList = new ArrayList<>();
        String orderId;
        Response response = RestAssured.given().spec(requestHelper.requestSpecification)
                .when()
                .body("{\n" +
                        "  \"id\": 0,\n" +
                        "  \"petId\": 0,\n" +
                        "  \"quantity\": 0,\n" +
                        "  \"shipDate\": \"2022-04-27T13:23:25.260Z\",\n" +
                        "  \"status\": \"placed\",\n" +
                        "  \"complete\": true\n" +
                        "}")
                .post("v2/store/order")
                .then().spec(requestHelper.responseSpecification).extract().response();

        orderId = response.jsonPath().getString("id");
        OrderIdList.add(orderId);
        return OrderIdList;
    }
}
