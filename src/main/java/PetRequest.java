import io.restassured.RestAssured;
import io.restassured.internal.assertion.Assertion;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

public class PetRequest {
    RequestHelper requestHelper = new RequestHelper();

    public List<Integer> getPetById(int petId) {
        List<Integer> PetIdList = new ArrayList<>();
        int statusCode;
        String errorMsg;
        do {
            Response response = RestAssured.given().spec(requestHelper.requestSpecification)
                    .when().get("v2/pet/" + petId)
                    .then().extract().response();
            statusCode = response.getStatusCode();
            errorMsg = response.jsonPath().getString("message");
            petId++;
            PetIdList.add(petId);

        } while (statusCode != 404 && errorMsg != "Pet not found");
        return PetIdList;
    }

    public void updatePet(int petId) {
        RestAssured.given().spec(requestHelper.requestSpecification)
                .when()
                .body("{\n" +
                        "  \"id\": " + petId + ",\n" +
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
                .put("v2/pet")
                .then().spec(requestHelper.responseSpecification).log().all();
    }

    public void deletePet(int petId) {
        RestAssured.given().spec(requestHelper.requestSpecification)
                .when().delete("v2/pet/" + petId)
                .then().spec(requestHelper.responseSpecification).log().all();
    }

    public void createPet(int petId) {
        RestAssured.given().spec(requestHelper.requestSpecification)
                .when()
                .body("{\n" +
                        "  \"id\": " + petId + ",\n" +
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
                .post("v2/pet")
                .then().spec(requestHelper.responseSpecification).log().all();
    }

    public List<String> getPetByStatus(String status) {
        List<String> AvailablePetList = new ArrayList<>();
        Response response = RestAssured.given().spec(requestHelper.requestSpecification)
                .when().params("status", status).get("v2/pet/findByStatus")
                .then().spec(requestHelper.responseSpecification).extract().response();

        String firstPetId = response.jsonPath().getString("[0].id");
        AvailablePetList.add(firstPetId);
        return AvailablePetList;
    }

    public void getAvailablePetById(String petId) {
        RestAssured.given().spec(requestHelper.requestSpecification)
                .when().get("v2/pet/" + petId)
                .then().spec(requestHelper.responseSpecification).log().all();
    }

    public void getPetNotExist(String petId) {
        RestAssured.given().spec(requestHelper.requestSpecification)
                .when().get("v2/pet/" + petId)
                .then().spec(requestHelper.negativeSpecification).log().all();
    }
}
