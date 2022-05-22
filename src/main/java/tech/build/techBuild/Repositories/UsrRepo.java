package tech.build.techBuild.Repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tech.build.techBuild.DTO.UsersDTO;
import tech.build.techBuild.Models.Usr;

import java.util.List;

@Repository
public interface UsrRepo extends CrudRepository<Usr, Long> {

    @Modifying
    @Query(value="UPDATE `usr` SET `enabled` = 0 where `id` = ?", nativeQuery = true)
    void deleteUser(long id);

    @Query(value="SELECT org_id FROM `usr`where login = ?", nativeQuery = true)
    long getOrgIdByUsername(String login);

    @Query(value="SELECT id FROM `usr` where login = ?", nativeQuery = true)
    long getUserIdIdByUsername(String login);

    @Modifying
    @Query(value="INSERT INTO `admin`(`enabled`, `start_date`, `admin_id`, `employee_id`) VALUES (1, CURDATE(), ?, ?)", nativeQuery = true)
    void addUsr(long adminId, long employeeId);

    @Query(value="SELECT u.id, u.full_name, u.login, u.email, u.phone, u.photo, r.role FROM `usr` as u JOIN `role` as r on r.id = u.role_id WHERE u.enabled = 1 and u.org_id = ?", nativeQuery = true)
    List<UsersDTO> getUsers(long orgId);



}
