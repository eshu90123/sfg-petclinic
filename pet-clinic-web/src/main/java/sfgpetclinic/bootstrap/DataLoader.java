package sfgpetclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sfgpetclinic.model.*;
import sfgpetclinic.services.*;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialitiesService specialitiesService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialitiesService specialitiesService, VisitService visitService){
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialitiesService = specialitiesService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();
        if(count == 0){
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatType = petTypeService.save(cat);

        Owner owner1 = new Owner();
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

        Visit catVisit = new Visit();
        catVisit.setDate(LocalDate.now());
        catVisit.setPet(ektaPet);
        catVisit.setDescription("Sneezy Kitty");
        visitService.save(catVisit);

        System.out.println("Loaded Owners.....");

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialitiesService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialitiesService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialitiesService.save(dentistry);

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialities().add(savedDentistry);
        vet1.getSpecialities().add(savedSurgery);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Neha");
        vet2.setLastName("Goyal");
        vet2.getSpecialities().add(savedRadiology);
        vetService.save(vet2);

        System.out.println("Loaded Vets.....");
    }
}
