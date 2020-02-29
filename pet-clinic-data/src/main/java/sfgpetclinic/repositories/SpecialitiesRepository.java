package sfgpetclinic.repositories;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.repository.CrudRepository;
import sfgpetclinic.model.Speciality;

public interface SpecialitiesRepository extends CrudRepository<Speciality, Long> {
}
