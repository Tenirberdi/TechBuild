package tech.build.techBuild.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tech.build.techBuild.Models.KindMaterial;

@Repository
public interface KindMaterialRepo extends CrudRepository<KindMaterial, Long> {
}
