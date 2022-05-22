package tech.build.techBuild.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tech.build.techBuild.Models.Role;

@Repository
public interface RoleRepo extends CrudRepository<Role, Long> {
    @Query(value="SELECT * FROM `role` as r where r.role = ?", nativeQuery = true)
    Role findByRole(String role);
}
