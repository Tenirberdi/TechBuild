package tech.build.techBuild.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tech.build.techBuild.Exceptions.CustomException;
import tech.build.techBuild.Services.SupplierService;

@Controller
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @GetMapping
    public String getStart(){
        return "supplier";
    }

    @GetMapping("/materials")
    public String getListMaterials(Model model){
        model.addAttribute("materials", supplierService.getAllMaterials());
        model.addAttribute("path", "/supplier");
        return "list-materials";
    }

    @GetMapping("/deliver")
    public String getDeliverPage(Model model){

        model.addAttribute("orders", supplierService.orderedList());

        return "deliver-materials";
    }

    @PostMapping("/deliver")
    public String deliver(@RequestParam long materialId, @RequestParam int quantity) throws CustomException {

        supplierService.deliver(materialId, quantity);

        return "redirect:/supplier/deliver";
    }

    @GetMapping("/deliveredMaterials")
    public String getDeliveredMaterials(Model model){

        model.addAttribute("delivs", supplierService.deliveredList());

        return "delivered-materials";
    }

    @GetMapping("/percentage")
    public String getPercentage(Model model){
        model.addAttribute("earnings", supplierService.getEarnings());
        return "percentage";
    }
}
