package tech.build.techBuild.Repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tech.build.techBuild.DTO.EarningDTO;
import tech.build.techBuild.DTO.NotFinishedObjectsDTO;
import tech.build.techBuild.DTO.TopBuildersDTO;
import tech.build.techBuild.DTO.TopMaterialsDTO;
import tech.build.techBuild.Models.ObjectConstruction;

import java.util.List;

@Repository
public interface ObjectConstructionRepo extends CrudRepository<ObjectConstruction, Long> {
    @Modifying
    @Query(value="UPDATE `object_construction` SET `end_date`= CURDATE() WHERE `object_id` = ?", nativeQuery = true)
    public void finishObject(long objectId);

    @Query(value="SELECT ob.photo, oc.id, oc.start_date, oc.builder_id, b.full_name as builderFullName,  oc.client_id,c.full_name as clientFullName, oc.object_id, ob.address, ob.name FROM `object_construction` as oc JOIN `object` as o on o.id = oc.object_id JOIN usr as b on b.id = oc.builder_id JOIN usr as c on c.id = oc.client_id JOIN object as ob on ob.id = oc.object_id WHERE oc.end_date is not null and b.org_id = 2", nativeQuery = true)
    public List<NotFinishedObjectsDTO> getFinishedObjects(long orgId);

    @Query(value="SELECT sum(o.cost) as earning, DATE_FORMAT(oc.end_date, '%m-%Y') as 'date' FROM `object_construction` as oc JOIN `object` as o on o.id = oc.object_id JOIN `organization` as org on org.id = o.org_id where org.id = ? GROUP BY YEAR(oc.end_date), month(oc.end_date)", nativeQuery = true)
    List<EarningDTO> getEarningsFromObjects(long orgId);

    @Query(value="SELECT m.id , m.name, sum(sm.quantity) as 'ordered_quantity', DATE_FORMAT(sm.delivered_date, '%m-%Y') as 'date' FROM `supply_material` as sm JOIN `material` as m on m.id = sm.material_id JOIN `organization` as org on org.id = m.org_id WHERE sm.delivered_date is not null and org.id = ? GROUP BY year(sm.delivered_date) , month(sm.delivered_date) ORDER by 'ordered qunatity' desc",nativeQuery = true)
    List<TopMaterialsDTO> getTopMaterials(long orgId);

    @Query(value="SELECT oc.builder_id, b.full_name , count(oc.id) as finishedObjectQuantity, DATE_FORMAT(oc.end_date, '%m-%Y') as 'date' FROM `object_construction` as oc JOIN `usr` as b on b.id = oc.builder_id WHERE b.org_id = ? GROUP BY oc.builder_id , YEAR(oc.end_date), month(oc.end_date) desc", nativeQuery = true)
    List<TopBuildersDTO> getTopBuilders(long orgId);
}
