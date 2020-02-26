package sfgpetclininc.services;

import sfgpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long>{

   Owner  findByLastName(String lastName);
}
