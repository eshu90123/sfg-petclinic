package sfgpetclinic.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sfgpetclinic.model.Owner;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {

    OwnerServiceMap ownerServiceMap;
    final Long ownerID = 1L;
    final String garg = "garg";

    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerServiceMap(new PetTypeServiceMap(), new PetServiceMap());
        ownerServiceMap.save(Owner.builder().id(ownerID).lastName(garg).build());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerServiceMap.findAll();
        assertEquals(1, ownerSet.size());
    }

    @Test
    void findById() {
        Owner owner = ownerServiceMap.findById(ownerID);
        assertEquals(ownerID, owner.getId());
    }

    @Test
    void saveExistingID() {
        Long id = 2L;
        Owner owner2 =Owner.builder().id(id).build();
        Owner savedOwner = ownerServiceMap.save(owner2);
        assertEquals(id, savedOwner.getId());
    }

    @Test
    void saveNoID() {
        Owner savedOwner = ownerServiceMap.save(Owner.builder().build());
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }
    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(ownerID));
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(ownerID);
        assertEquals(0 , ownerServiceMap.findAll().size());
    }

    @Test
    void findByLastName() {

        Owner garg = ownerServiceMap.findByLastName(this.garg);
        assertNotNull(garg);
        assertEquals(ownerID, garg.getId());
    }

    @Test
    void findByLastNameNotFound() {

        Owner garg = ownerServiceMap.findByLastName("foo");
        assertNull(garg);
    }
}