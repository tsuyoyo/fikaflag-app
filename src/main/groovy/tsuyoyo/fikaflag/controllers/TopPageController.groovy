package tsuyoyo.fikaflag.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

import tsuyoyo.fikaflag.services.IFlagsService

@Controller
class TopPageController {

    @Autowired
    IFlagsService flagsService;

    @RequestMapping("/")
    public String topPage(Model model) {
        model.addAttribute("flags", flagsService.get());
        return "index";
    }

    @RequestMapping("/tmp")
    public String topTmpPage(Model model) {
        model.addAttribute("flags", flagsService.get());
        return "index_tmp";
    }

}
