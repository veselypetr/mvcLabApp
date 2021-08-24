/*
 * Vytvořeno jako studentský zápočtový projekt v rámci studia FIM UHK.
 * 2021 - Petr Veselý
 */

package cz.fim.uhk.lab.controller;

import cz.fim.uhk.lab.exeption.RecordNotFoundException;
import cz.fim.uhk.lab.exeption.ResourceNotFoundException;
import cz.fim.uhk.lab.model.Item;
import cz.fim.uhk.lab.model.ItemRequest;
import cz.fim.uhk.lab.model.Person;
import cz.fim.uhk.lab.service.CheckoutService;
import cz.fim.uhk.lab.security.UserManagementService;
import cz.fim.uhk.lab.service.UploadHandlerService;
import cz.fim.uhk.lab.service.UsernameResolverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/*
    Checkout controller is the MVC controller for the module 'Checkout'.
    It passes data to the thymeleaf model for the creation of the table
    with available items, handles checking out of items, their creation,
    deletion, and editing.
 */

@Controller
@RequestMapping("/checkout")
public class CheckoutController {
    @Autowired
    CheckoutService service;
    @Autowired
    UserManagementService userManagementService;
    @Autowired
    UsernameResolverService usernameResolverService;
    @Autowired
    UploadHandlerService uploadHandlerService;

    @GetMapping("/")
    public String getAllAvailableItems(Model model) {
        Long id = usernameResolverService.findIdByUsername(((UserDetails) (SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getUsername());
        ItemRequest itemRequest = new ItemRequest();
        model.addAttribute("availableItems", service.getAvailableItems(id));
        model.addAttribute("checkoutRequest", itemRequest);
        return "checkout/checkout";
    }

    @PostMapping("/")
    public String checkout(ItemRequest itemRequest) {
        Long id = usernameResolverService.findIdByUsername(((UserDetails) (SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getUsername());
        Person person = null;
        try {
            person = userManagementService.getPersonById(id);
            service.checkout(itemRequest, person);
        } catch (RecordNotFoundException | ResourceNotFoundException e) {
            e.printStackTrace();
        }
        return "redirect:/checkout/";
    }

    @GetMapping("/remove-item/{id}")
    public String removeItem(@PathVariable("id") Long itemId) {
        service.remove(itemId);
        return "redirect:/checkout/";
    }

    @GetMapping("/edit-item/{id}")
    public String editItem(Model model, @PathVariable("id") Long id) {
        Item item = service.getItemById(id);
        model.addAttribute("item", item);
        model.addAttribute("categories", service.getAllGeneralCategories());
        return "checkout/edit-item";
    }

    @GetMapping("/edit-specimen/{id}")
    public String editSpecimen(Model model, @PathVariable("id") Long id) {
        Item item = service.getItemById(id);
        model.addAttribute("spec", item);
        model.addAttribute("categories", service.getAllSpecimenCategories());
        model.addAttribute("privileges", service.getAllPrivs());
        return "/checkout/edit-specimen";
    }

    @GetMapping("/create-item")
    public String createItem(Model model) {
        Item item = new Item();
        service.saveNewItem(item);
        return "redirect:/checkout/edit-item/" + item.getItemid();
    }

    @GetMapping("/create-specimen")
    public String createSpecimen(Model model) {
        Item item = new Item();
        service.saveNewItem(item);
        return "redirect:/checkout/edit-specimen/" + item.getItemid();
    }

    @PostMapping({"/edit-specimen/{id}", "/edit-item/{id}"})
    public String saveCreatedItemAndSpecimen(Item updateItem, @RequestParam("file") MultipartFile file, @PathVariable Long id) {
        service.updateItem(updateItem, id);
        uploadHandlerService.handleItemPhoto(file, id);
        return "redirect:/checkout/";
    }

}
