package tech.build.techBuild.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.build.techBuild.CommonFunc.CommonFunc;
import tech.build.techBuild.DTO.DeliveredListDTO;
import tech.build.techBuild.DTO.EarningDTO;
import tech.build.techBuild.DTO.MaterialsDTO;
import tech.build.techBuild.DTO.OrderedDTO;
import tech.build.techBuild.Exceptions.CustomException;
import tech.build.techBuild.Models.SupplyMaterial;
import tech.build.techBuild.Repositories.MaterialRepo;
import tech.build.techBuild.Repositories.SupplyMaterialRepo;
import tech.build.techBuild.Repositories.UsrRepo;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class SupplierService {

    @Autowired
    UsrRepo usrRepo;

    @Autowired
    MaterialRepo materialRepo;

    @Autowired
    SupplyMaterialRepo supplyMaterialRepo;

    public List<MaterialsDTO> getAllMaterials(){
        long orgId = usrRepo.getOrgIdByUsername(CommonFunc.getCurrentUsersUserName());
        return materialRepo.getMaterialList(orgId);
    }

    public Date getCurrentDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = new java.util.Date();
        System.out.println(dateFormat.format(date));
        return Date.valueOf(dateFormat.format(date));
    }

    @Transactional
    public void deliver(long materialId, int quantity) throws CustomException{
        if(supplyMaterialRepo.checkIfMaterialAlreadyOrdered(materialId) == 1){
            SupplyMaterial order = supplyMaterialRepo.getByMaterialId(materialId);

            if(order.getQuantity() >= quantity){
                if(order.getQuantity() == quantity){
                    supplyMaterialRepo.deleteByMaterialId(materialId);
                }else{
                    order.setQuantity(order.getQuantity() - quantity);
                    supplyMaterialRepo.save(order);
                }

                SupplyMaterial deliveredOrder = new SupplyMaterial();
                deliveredOrder.setBuilderId(order.getBuilderId());
                deliveredOrder.setOrderedDate(order.getOrderedDate());
                deliveredOrder.setMaterialId(order.getMaterialId());
                deliveredOrder.setQuantity(quantity);
                deliveredOrder.setSupplierId(usrRepo.findById(usrRepo.getUserIdIdByUsername(CommonFunc.getCurrentUsersUserName())).get());
                deliveredOrder.setDeliveredDate(getCurrentDate());

                supplyMaterialRepo.save(deliveredOrder);
            }else{
                throw new CustomException("So much was not ordered");
            }

        }else{
            throw new CustomException("There is no such order");
        }
    }

    public List<OrderedDTO> orderedList(){
        return supplyMaterialRepo.orderedMaterialsForSupplier(usrRepo.getOrgIdByUsername(CommonFunc.getCurrentUsersUserName()));
    }

    public List<DeliveredListDTO> deliveredList(){
        return supplyMaterialRepo.getDeliveredList(usrRepo.getOrgIdByUsername(CommonFunc.getCurrentUsersUserName()));
    }

    public List<EarningDTO> getEarnings(){
        return supplyMaterialRepo.getSupplierEarning(usrRepo.getUserIdIdByUsername(CommonFunc.getCurrentUsersUserName()));
    }

}
