package sfgpetclinic.services.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import sfgpetclinic.model.Speciality;
import sfgpetclinic.model.Vet;
import sfgpetclinic.services.SpecialitiesService;
import sfgpetclinic.services.VetService;
import sfgpetclinic.services.map.AbstractMapService;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {
    private SpecialitiesService specialitiesService;
    public VetServiceMap(SpecialitiesService specialitiesService){
        this.specialitiesService = specialitiesService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet object)
    {
        if(object.getSpecialities().size()>0){
            object.getSpecialities().forEach(speciality -> {
                if(speciality.getId() == null){
                    Speciality saveSpeciality = specialitiesService.save(speciality);
                    speciality.setId(saveSpeciality.getId());
                }
            });
        }
        return super.save(object);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
