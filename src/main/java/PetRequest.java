import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

public class PetRequest {

    public static List<Integer> getPetById(int petId){
        List<Integer> PetIdList = new ArrayList<>();
        int statusCode;
        String errorMsg;
        do {
            Response response = RestAssured.given()
                    .contentType("application/json")
                    .accept(ContentType.JSON).log().all()
                    .get("v2/pet/"+petId+"")
                    .prettyPeek().then().extract().response();
            statusCode = response.getStatusCode();
            errorMsg = response.jsonPath().getString("message");
            petId++;
            PetIdList.add(petId);

        }while (statusCode!=404 && errorMsg!="Pet not found");
        return PetIdList;
}

    public static void updatePet(int petId){

        Response response = RestAssured.given()
                .contentType("application/json")
                .body("{\n" +
                        "  \"id\": "+petId+",\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 0,\n" +
                        "    \"name\": \"string\"\n" +
                        "  },\n" +
                        "  \"name\": \"kangal\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 0,\n" +
                        "      \"name\": \"string\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"available\"\n" +
                        "}")
                .accept(ContentType.JSON).log().all()
                .put("v2/pet")
                .prettyPeek().then().statusCode(200).extract().response();

        if (response.getStatusCode()==200){
            System.out.println("Succeed!!");
        } else {
            System.out.println("Failed!!");
        }
    }

    public static void deletePet(int petId){

        Response response = RestAssured.given()
                .contentType("application/json")
                .accept(ContentType.JSON).log().all()
                .delete("v2/pet/"+petId+"")
                .prettyPeek().then().statusCode(200).extract().response();

        if (response.getStatusCode()==200){
            System.out.println("Succeed!!");
        } else {
            System.out.println("Failed!!");
        }
    }

    public static void createPet(int petId){

        Response response = RestAssured.given()
                .contentType("application/json")
                .header("api-key","special-key")
                .body("{\n" +
                        "  \"id\": "+petId+",\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 0,\n" +
                        "    \"name\": \"string\"\n" +
                        "  },\n" +
                        "  \"name\": \"doggie\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 0,\n" +
                        "      \"name\": \"string\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"available\"\n" +
                        "}")
                .accept(ContentType.JSON).log().all()
                .post("v2/pet")
                .prettyPeek().then().statusCode(200).extract().response();

        if (response.getStatusCode()==200){
            System.out.println("Succeed!!");
        } else {
            System.out.println("Failed!!");
        }
    }
    public static List<String> getPetByStatus(String status){

        List<String> AvailablePetList = new ArrayList<>();
        Response response = RestAssured.given()
                .contentType("application/json")
                .params("status", status)
                .accept(ContentType.JSON).log().all()
                .get("v2/pet/findByStatus")
                .prettyPeek().then().statusCode(200).extract().response();

        String firstPetId = response.jsonPath().getString("[0].id");
        AvailablePetList.add(firstPetId);

        if (response.getStatusCode()==200){
            System.out.println("Succeed!!");
        } else {
            System.out.println("Failed!!");
        }
        return AvailablePetList;
    }

    public static void getAvailablePetById(String petId){

        Response response = RestAssured.given()
                .contentType("application/json")
                .accept(ContentType.JSON).log().all()
                .get("v2/pet/"+petId+"")
                .prettyPeek().then().statusCode(200).extract().response();

        int statusCode = response.getStatusCode();

        if (statusCode==200){
            System.out.println("Test is Succeed!!");
        } else {
            System.out.println("Test is Failed!!");
        }
    }
}
