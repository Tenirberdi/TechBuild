package tech.build.techBuild.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.build.techBuild.CommonFunc.CommonFunc;
import tech.build.techBuild.DTO.*;
import tech.build.techBuild.Repositories.*;

import java.util.List;

@Service
public class BuilderService {

    @Autowired
    UsrRepo usrRepo;

    @Autowired
    MaterialRepo materialRepo;

    @Autowired
    SupplyMaterialRepo supplyMaterialRepo;

    @Autowired
    ObjectConstructionRepo objectConstructionRepo;

    @Autowired
    ObjectRepo objectRepo;

    public List<MaterialsDTO> getAllMaterials(){
        long orgId = usrRepo.getOrgIdByUsername(CommonFunc.getCurrentUsersUserName());
        return materialRepo.getMaterialList(orgId);
    }

    @Transactional
    public void orderMaterial(int quantity, long materialId){

        if(supplyMaterialRepo.checkIfMaterialAlreadyOrdered(materialId) == 1){
            supplyMaterialRepo.orderMore(quantity, materialId);
        }else {

            long builderId = usrRepo.getUserIdIdByUsername(CommonFunc.getCurrentUsersUserName());
            supplyMaterialRepo.orderMaterial(quantity, builderId, materialId);
        }
    }

    public List<OrderedDTO> getOrderedMaterials(){
        return supplyMaterialRepo.orderedMaterials(usrRepo.getUserIdIdByUsername(CommonFunc.getCurrentUsersUserName()));

    }

    @Transactional
    public void finishObject(long objectId){
        objectConstructionRepo.finishObject(objectId);
    }

    public List<NotFinishedObjectsDTO> getNotFinishedOnes(){
        return objectRepo.getNotFinishedObjects(usrRepo.getOrgIdByUsername(CommonFunc.getCurrentUsersUserName()));
    }

    public List<NotFinishedObjectsDTO> getFinishedOnes(){
        return objectConstructionRepo.getFinishedObjects(usrRepo.getOrgIdByUsername(CommonFunc.getCurrentUsersUserName()));
    }

    public List<TopBuildersDTO> getTopBuilders(){
        return objectConstructionRepo.getTopBuilders(usrRepo.getOrgIdByUsername(CommonFunc.getCurrentUsersUserName()));
    }

    public List<TopMaterialsDTO> getTopMaterials(){
        return objectConstructionRepo.getTopMaterials(usrRepo.getOrgIdByUsername(CommonFunc.getCurrentUsersUserName()));
    }

    public List<EarningDTO> getEarningsFromObjects(){
        return objectConstructionRepo.getEarningsFromObjects(usrRepo.getOrgIdByUsername(CommonFunc.getCurrentUsersUserName()));
    }



}
