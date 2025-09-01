import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PetTest {
    PetRequest petRequest = new PetRequest();

    @Test
    @DisplayName("Find a pet")
    @Description("Find pet by spesific Id")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Osman Can Canki")
    public void getPetByIdTest() {

        System.out.println("----------Get Pet By Id----------");
        List<Integer> petList = petRequest.getPetById(1);
        int id = petList.get(0);
        System.out.println(id);

        System.out.println("----------Create Pet Id----------");
        petRequest.createPet(id);

        System.out.println("----------Update Pet Id----------");
        petRequest.updatePet(id);

        System.out.println("----------Delete Pet Id----------");
        petRequest.deletePet(id);
    }

    /*
    Behavior based hierarchy
     */
    @Test
    @Epic("Pet Scenarios")
    @Feature("User gets pet")
    @Story("User gets pet by status")
    public void getPetByStatusTest() {

        System.out.println("----------Get Pet By Status----------");
        List<String> availablePetList = petRequest.getPetByStatus("available");
        String petId = availablePetList.get(0);

        System.out.println("----------Get Pet By Id----------");
        petRequest.getAvailablePetById(petId);
    }

    /*
    Suite based hierarchy
     */
    @Test
    public void negativePetTest() {

        Allure.suite("Negative Tests");

        System.out.println("----------Get Pet By Id----------");
        petRequest.getPetNotExist("1");
    }
}
