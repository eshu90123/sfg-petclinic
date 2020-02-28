package sfgpetclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.convert.JodaTimeConverters;
import org.springframework.stereotype.Component;
import sfgpetclinic.model.Owner;
import sfgpetclinic.model.Pet;
import sfgpetclinic.model.PetType;
import sfgpetclinic.model.Vet;
import sfgpetclinic.services.OwnerService;
import sfgpetclinic.services.PetTypeService;
import sfgpetclinic.services.VetService;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService){
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatType = petTypeService.save(cat);

        Owner  owner1 = new Owner();
        owner1.setFirstName("Esha");
        owner1.setLastName("Garg");
        owner1.setAddress("123 street");
        owner1.setCity("abc");
        owner1.setTelephone("12323132213123");

        Pet eshaPet = new Pet();
        eshaPet.setPetType(savedDogType);
        eshaPet.setOwner(owner1);
        eshaPet.setBirthDate(LocalDate.now());
        eshaPet.setName("Rosco");
        owner1.getPets().add(eshaPet);

        ownerService.save(owner1);

        Owner  owner2 = new Owner();
        owner2.setFirstName("Ekta");
        owner2.setLastName("Raman");
        owner2.setAddress("123 street");
        owner2.setCity("abc");
        owner2.setTelephone("12323132213123");

        Pet ektaPet = new Pet();
        ektaPet.setPetType(savedCatType);
        ektaPet.setOwner(owner2);
        ektaPet.setBirthDate(LocalDate.now());
        ektaPet.setName("Kitty");
        owner2.getPets().add(ektaPet);

        ownerService.save(owner2);

        System.out.println("Loaded Owners.....");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Neha");
        vet2.setLastName("Goyal");
        vetService.save(vet2);

        System.out.println("Loaded Vets.....");

    }
}
