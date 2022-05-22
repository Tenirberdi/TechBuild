package tech.build.techBuild.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tech.build.techBuild.Models.Organization;

@Repository
public interface OrganizationRepo extends CrudRepository<Organization, Long> {
    @Query(value="SELECT * FROM `organization` as org where org.name = ?", nativeQuery = true)
    Organization findByName(String name);
}
