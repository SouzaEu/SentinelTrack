package br.com.SentinelTrack.controllers.view;


import br.com.SentinelTrack.models.Update;
import br.com.SentinelTrack.services.interfaces.UpdatesService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@Controller
@RequestMapping("/updates")
public class UpdateViewController {

    private final UpdatesService updatesService;

    public UpdateViewController(UpdatesService updatesService) {
        this.updatesService = updatesService;
    }

    @GetMapping
    public String listUpdates(Model model) {
        model.addAttribute("updates", updatesService.findAll());
        return "updates/list";
    }
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("update", new Update());
        return "updates/form";
    }

    @PostMapping("/save")
    public String saveUpdate(@Valid @ModelAttribute("update") Update update,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "updates/form";
        }
        this.updatesService.post(update);
        return "redirect:/updates";
    }

    @GetMapping("delete/{id}")
    public String deleteUpdate(@PathVariable("id") UUID  id){
       this.updatesService.deleteById(id);
        return "redirect:/updates";
    }

    @GetMapping("/view/{id}")
    public String viewUpdateByUserId(@PathVariable("id") UUID id, Model model){
       Update update = this.updatesService.findAllById(id);
        model.addAttribute("update", update);
        return "updates/view";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") UUID id, Model model){
        Update newUpdate = this.updatesService.findAllById(id);
        model.addAttribute("update", newUpdate);
        return "updates/form";
    }
}