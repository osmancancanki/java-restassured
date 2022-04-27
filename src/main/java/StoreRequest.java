import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

public class StoreRequest {

    public static void getInventory(){

        Response response = RestAssured.given()
                .when().log().all().get("store/inventory")
                .then().statusCode(200).extract().response().prettyPeek();

        if (response.getStatusCode()==200){
            System.out.println("Succeed!!");
        } else {
            System.out.println("Failed!!");
        }
    }

    public static void getOrderById(int orderId){
        Response response = RestAssured.given()
                .when().log().all().get("store/order/"+orderId)
                .then().extract().response().prettyPeek();

        String statusMessage = response.jsonPath().getString("message");
        int statusCode = response.getStatusCode();

        if (orderId < 1 && 9 < orderId && statusCode==400&&statusMessage=="Invalid ID supplied"){
            System.out.println("Succeed!!");
        }
        else if(1<=orderId&&orderId<=9&&statusCode==404&&statusMessage=="Order not found"){
            System.out.println("Succeed!!");
        }
        else {
            System.out.println("Failed!!");
        }
    }

    public static void deleteOrder(String orderId){

        Response response = RestAssured
                .given().contentType("application/json")
                .when().delete("store/order/"+orderId)
                .then().statusCode(200).extract().response();

        if (response.getStatusCode()==200){
            System.out.println("Succeed!!");
        } else {
            System.out.println("Failed!!");
        }
    }

    public static List<String> createOrder(){
        List<String> OrderIdList = new ArrayList<>();
        String orderId;
        Response response = RestAssured.given()
                .contentType("application/json").header("api-key","special-key")
                .when().log().all()
                .body("{\n" +
                        "  \"id\": 0,\n" +
                        "  \"petId\": 0,\n" +
                        "  \"quantity\": 0,\n" +
                        "  \"shipDate\": \"2022-04-27T13:23:25.260Z\",\n" +
                        "  \"status\": \"placed\",\n" +
                        "  \"complete\": true\n" +
                        "}")
                .post("store/order")
                .prettyPeek().then().statusCode(200).extract().response();

        orderId = response.jsonPath().getString("id");
        OrderIdList.add(orderId);

        if (response.getStatusCode()==200){
            System.out.println("Succeed!!");
        } else {
            System.out.println("Failed!!");
        }
        return OrderIdList;
    }
}
