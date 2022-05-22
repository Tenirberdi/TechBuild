package tech.build.techBuild.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tech.build.techBuild.DTO.NotFinishedObjectsDTO;
import tech.build.techBuild.Models.Object;

import java.util.List;

@Repository
public interface ObjectRepo extends CrudRepository<Object, Long> {
    @Query(value="SELECT ob.photo, oc.id, oc.start_date, oc.builder_id, b.full_name as builderFullName,  oc.client_id,c.full_name as clientFullName, oc.object_id, ob.address, ob.name FROM `object_construction` as oc JOIN `object` as o on o.id = oc.object_id JOIN usr as b on b.id = oc.builder_id JOIN usr as c on c.id = oc.client_id JOIN object as ob on ob.id = oc.object_id WHERE oc.end_date is null and b.org_id = 2", nativeQuery = true)
    public List<NotFinishedObjectsDTO> getNotFinishedObjects(long orgId);
}
