package tech.build.techBuild.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @GetMapping
    public String getStart(){
        return "index";
    }

    @GetMapping("/login")
    public String getLoginPage(){

        return "index";

    }


    @GetMapping("/error")
    public String getErrorPage(Model model){

        model.addAttribute("error", (String) model.asMap().get("error"));

//        model.addAttribute("error1", "Error!");
//        model.addAttribute("status", (String) model.asMap().get("status"));
//        model.addAttribute("path", (String) model.asMap().get("path"));
        return "error";
    }
}
