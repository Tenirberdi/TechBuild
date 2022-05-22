package tech.build.techBuild.Repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tech.build.techBuild.DTO.RegisteredUsers;
import tech.build.techBuild.Models.Admin;

import java.util.List;

@Repository
public interface AdminRepo  extends CrudRepository<Admin, Long> {
    @Query(value="SELECT a.id, a.end_date, a.start_date, a.admin_id, a.employee_id FROM `admin` as a JOIN `usr` as u on u.id = a.admin_id where u.org_id = ?", nativeQuery = true)
    List<RegisteredUsers> getRegisteredUsers(long orgId);

    @Modifying
    @Query(value="UPDATE `admin` SET `end_date` = CURDATE() WHERE `employee_id` = ?", nativeQuery = true)
    void recordDeleting(long empId);
}
