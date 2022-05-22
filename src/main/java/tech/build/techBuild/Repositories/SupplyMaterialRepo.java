package tech.build.techBuild.Repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tech.build.techBuild.DTO.DeliveredListDTO;
import tech.build.techBuild.DTO.EarningDTO;
import tech.build.techBuild.DTO.OrderedDTO;
import tech.build.techBuild.Models.SupplyMaterial;

import java.util.List;

@Repository
public interface SupplyMaterialRepo extends CrudRepository<SupplyMaterial, Long> {
    @Modifying
    @Query(value="INSERT INTO `supply_material`( `ordered_date`, `quantity`, `builder_id`, `material_id`) VALUES (CURDATE(), ?, ?,?)", nativeQuery = true)
    void orderMaterial(int quantity, long builderId, long material);

    @Query(value="SELECT s.id, s.ordered_date, s.quantity, s.builder_id, s.material_id, m.name, m.manufacturer FROM supply_material as s \n" +
            "join material as m on m.id = s.material_id where s.delivered_date is null and s.supplier_id is null and s.builder_id = ?", nativeQuery = true)
    List<OrderedDTO> orderedMaterials(long builderId);

    @Query(value="SELECT s.id, s.ordered_date, s.quantity, s.builder_id, s.material_id, m.name, m.manufacturer FROM supply_material as s join material as m on m.id = s.material_id JOIN `organization` as org on org.id = m.org_id where s.delivered_date is null and org.id = ?", nativeQuery = true)
    List<OrderedDTO> orderedMaterialsForSupplier(long orgId);

    @Query(value="select if(count(*)>0, true, false) from `supply_material` as s where s.material_id = ? and s.delivered_date is null", nativeQuery = true)
    int checkIfMaterialAlreadyOrdered(long materialId);

    @Modifying
    @Query(value="UPDATE `supply_material` SET `quantity` = `quantity` + ?1 WHERE `material_id` = ?2 and delivered_date is null", nativeQuery = true)
    void orderMore(int quantity, long materialId);

    @Modifying
    @Query(value="DELETE FROM `supply_material` WHERE material_id = ? and delivered_date is null", nativeQuery = true)
    void deleteByMaterialId(long materialId);

    @Query(value="SELECT if(count(*)>0, true, false) FROM `supply_material` as s WHERE s.material_id = ? and s.delivered_date is null",nativeQuery = true)
    int checkIfSuchOrderExists(long materialId);

    @Query(value="SELECT * FROM `supply_material` as s WHERE s.material_id = ? and s.delivered_date is null", nativeQuery = true)
    SupplyMaterial getByMaterialId(long materialId);

    @Query(value="SELECT s.id, s.delivered_date, s.ordered_date, s.quantity, s.builder_id, b.full_name as builder_full_name, s.material_id, m.name, s.supplier_id, sup.full_name as supplier_full_name FROM `supply_material` as s JOIN `usr` as b on b.id = s.builder_id JOIN `usr` as sup on sup.id = s.supplier_id JOIN `material` as m on m.id = s.material_id JOIN organization as org on org.id = m.org_id where org.id = ?", nativeQuery = true)
    List<DeliveredListDTO> getDeliveredList(long orgId);

    @Query(value="SELECT sum(s.quantity*m.price/20) as earning, DATE_FORMAT(s.delivered_date, '%Y-%m') as 'date' FROM `supply_material` as s JOIN `material` as m on m.id = s.material_id WHERE s.supplier_id = ? and s.delivered_date is not null GROUP BY s.material_id, year(s.delivered_date), month(s.delivered_date)", nativeQuery = true)
    List<EarningDTO> getSupplierEarning(long supplierId);
}
