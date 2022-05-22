package tech.build.techBuild.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tech.build.techBuild.DTO.RegInfo;
import tech.build.techBuild.Exceptions.CustomException;
import tech.build.techBuild.Services.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping
    public String getStart(Model model){
        model.addAttribute("users", adminService.getUsrs());

        return "admin";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model){

        model.addAttribute("regInfo", new RegInfo());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegInfo regInfo, RedirectAttributes redirectAttributes) throws CustomException {
        try {
            adminService.addUser(regInfo.getFullName(), regInfo.getLogin(), regInfo.getPass(), regInfo.getEmail(), regInfo.getPhone(), regInfo.getPhoto(), regInfo.getOrg(), regInfo.getRole());
            return "redirect:/admin";
        }catch (CustomException ce){
            redirectAttributes.addFlashAttribute("error", ce.getMessage());

//            redirectAttributes.addFlashAttribute("status", 500);
//            redirectAttributes.addFlashAttribute("path", "/admin");
            return "redirect:/error";
        }
    }

    @GetMapping("/report")
    public String getReport(Model model){

        model.addAttribute("users", adminService.getRegisteredUsers());

        return "adminReport";
    }

    @GetMapping("/delete/{empId}")
    public String deleteEmp(@PathVariable long empId) throws  CustomException{
        adminService.deleteUser(empId);
        return "redirect:/admin";
    }


}
