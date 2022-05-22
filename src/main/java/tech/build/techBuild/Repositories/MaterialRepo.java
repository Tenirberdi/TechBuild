package tech.build.techBuild.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tech.build.techBuild.DTO.MaterialsDTO;
import tech.build.techBuild.Models.Material;

import java.util.List;

@Repository
public interface MaterialRepo extends CrudRepository<Material, Long> {
    @Query(value = "SELECT m.id, m.photo, m.manufacturer, m.name, m.price,m.quantity, km.kind, km.img FROM `material` as m JOIN `kind_material` as km on km.id = m.kind_material_id where m.org_id = ?", nativeQuery = true)
    List<MaterialsDTO> getMaterialList(long orgId);
}
