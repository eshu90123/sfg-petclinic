package sfgpetclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sfgpetclinic.model.Owner;
import sfgpetclinic.model.Vet;
import sfgpetclininc.services.OwnerService;
import sfgpetclininc.services.OwnerServiceMap;
import sfgpetclininc.services.VetService;
import sfgpetclininc.services.VetServiceMap;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    public DataLoader(){
        ownerService = new OwnerServiceMap();
        vetService = new VetServiceMap();
    }

    @Override
    public void run(String... args) throws Exception {
        Owner  owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Esha");
        owner1.setLastName("Garg");
        ownerService.save(owner1);

        Owner  owner2 = new Owner();
        owner1.setId(2L);
        owner1.setFirstName("Ekta");
        owner1.setLastName("Raman");
        ownerService.save(owner2);

        System.out.println("Loaded Owners.....");

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Neha");
        vet2.setLastName("Goyal");
        vetService.save(vet2);

        System.out.println("Loaded Vets.....");

    }
}
