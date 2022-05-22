package tech.build.techBuild.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tech.build.techBuild.Services.BuilderService;

@Controller
@RequestMapping("/builder")
public class BuilderController {


    @Autowired
    BuilderService builderService;


    @GetMapping("")
    public String getStart(){
        return "builder";
    }

    @GetMapping("/list-materials")
    public String getListMaterials(Model  model){
        model.addAttribute("materials", builderService.getAllMaterials());
        model.addAttribute("path", "/builder");
        return "list-materials";
    }

    @GetMapping("/order")
    public String getConverattion(){

        return "order-materials";
    }

    @PostMapping("/order")
    public String order(@RequestParam int quantity, @RequestParam long materialId){
        builderService.orderMaterial(quantity, materialId);
        return "redirect:/builder/order-report";
    }

    @GetMapping("/order-report")
    public String getOrderReport(Model model){
        model.addAttribute("orders", builderService.getOrderedMaterials());
        return "order-report";
    }

    @GetMapping("/objects")
    public String getInternationalTranslations(Model model){
        model.addAttribute("objects", builderService.getNotFinishedOnes());
        return "list-objects";
    }

    @GetMapping("/finished-objects")
    public String getTransferReport(Model model){
        model.addAttribute("objects", builderService.getFinishedOnes());
        return "finished-objects";
    }

    @GetMapping("/about")
    public String getTest(){
        return "about";
    }

    @PostMapping("/finish-object")
    public String finishObject(@RequestParam long objectId){
        System.out.println(objectId);
        builderService.finishObject(objectId);
        return "redirect:/builder/transfer-report";
    }

    @GetMapping("/topMaterials")
    public String getTopMaterials(Model model){
        model.addAttribute("materials", builderService.getTopMaterials());
        return "topMaterials";
    }

    @GetMapping("/topBuilders")
    public String getTopBuilder(Model model){
        model.addAttribute("builders", builderService.getTopBuilders());
        return "topBuilders";
    }

    @GetMapping("/earning")
    public String getEarningsFromObjects(Model model){
        model.addAttribute("earnings", builderService.getEarningsFromObjects());
        return "earningsFromObjects";
    }
}
