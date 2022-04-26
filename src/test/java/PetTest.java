import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PetTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://petstore.swagger.io";
    }

    @Test
    public void getPetByIdTest(){

        System.out.println("----------Get Pet By Id----------");
        List<Integer> petList = PetRequest.getPetById(1);
        int id = petList.get(0);
        System.out.println(id);

        System.out.println("----------Create Pet Id----------");
        PetRequest.createPetId(id);

        System.out.println("----------Update Pet Id----------");
        PetRequest.updatePetId(id);

        System.out.println("----------Delete Pet Id----------");
        PetRequest.deletePetId(id);
    }

    @Test
    public void getPetByStatusTest(){

        System.out.println("----------Get Pet By Status----------");
        List<String> availablePetList = PetRequest.getPetByStatus("available");
        String petId = availablePetList.get(0);

        System.out.println("----------Get Pet By Id----------");
        PetRequest.getAvailablePetById(petId);
    }


}
