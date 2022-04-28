import org.junit.Test;

import java.util.List;

public class PetTest {
    PetRequest petRequest = new PetRequest();

    @Test
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

    @Test
    public void getPetByStatusTest() {

        System.out.println("----------Get Pet By Status----------");
        List<String> availablePetList = petRequest.getPetByStatus("available");
        String petId = availablePetList.get(0);

        System.out.println("----------Get Pet By Id----------");
        petRequest.getAvailablePetById(petId);
    }
}
