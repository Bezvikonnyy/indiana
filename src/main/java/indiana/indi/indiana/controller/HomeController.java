package indiana.indi.indiana.controller;

import indiana.indi.indiana.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {

    private final HomeService homeService;

    @GetMapping
    public String getCategoriesList(Model model){
        model.addAttribute("categories", this.homeService.getAllCategories());
        model.addAttribute("categoryGameMap", this.homeService.getGamesByCategory());
        return "home";
    }

}
