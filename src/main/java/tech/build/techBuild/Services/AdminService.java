package tech.build.techBuild.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.build.techBuild.CommonFunc.CommonFunc;
import tech.build.techBuild.DTO.RegisteredUsers;
import tech.build.techBuild.DTO.UsersDTO;
import tech.build.techBuild.Exceptions.CustomException;
import tech.build.techBuild.Models.Organization;
import tech.build.techBuild.Models.Role;
import tech.build.techBuild.Models.Usr;
import tech.build.techBuild.Repositories.AdminRepo;
import tech.build.techBuild.Repositories.OrganizationRepo;
import tech.build.techBuild.Repositories.RoleRepo;
import tech.build.techBuild.Repositories.UsrRepo;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    UsrRepo usrRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    OrganizationRepo organizationRepo;

    @Autowired
    AdminRepo adminRepo;

    @Transactional
    public void addUser(String fullName, String login, String pass, String email, String phone, String photo, String orgName, String roleName) throws CustomException{

        Role role = roleRepo.findByRole(roleName);

        Organization org = organizationRepo.findByName(orgName);

        if(role == null){
            System.out.println("Role not found");
            throw new CustomException("Role not found");
        }
        if(org == null){
            System.out.println(orgName);
            System.out.println("Organization not found");
            throw new CustomException("Organization not found");
        }

        Usr user = new Usr();
        user.setFullName(fullName);
        user.setLogin(login);
        user.setPass(pass);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPhoto(photo);
        user.setOrgId(org);
        user.setRoleId(role);
        user.setEnabled(true);

        Usr newUser = usrRepo.save(user);

        usrRepo.addUsr(usrRepo.getUserIdIdByUsername(CommonFunc.getCurrentUsersUserName()), newUser.getId());

    }

    @Transactional
    public void deleteUser(long userId) throws CustomException{
        Usr user = usrRepo.findById(userId).get();
        if(user == null){
            throw new CustomException("user not found");
        }

        usrRepo.deleteUser(userId);

        adminRepo.recordDeleting(userId);
    }

    public List<RegisteredUsers> getRegisteredUsers(){
        return adminRepo.getRegisteredUsers(usrRepo.getOrgIdByUsername(CommonFunc.getCurrentUsersUserName()));
    }

    public List<UsersDTO> getUsrs(){

        return  usrRepo.getUsers(usrRepo.getOrgIdByUsername(CommonFunc.getCurrentUsersUserName()));
    }
}
