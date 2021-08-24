/*
 * Vytvořeno jako studentský zápočtový projekt v rámci studia FIM UHK.
 * 2021 - Petr Veselý
 */

package cz.fim.uhk.lab.controller;

import cz.fim.uhk.lab.exeption.ResourceNotFoundException;
import cz.fim.uhk.lab.model.Borrowed;
import cz.fim.uhk.lab.model.ItemRequest;
import cz.fim.uhk.lab.service.MyInventoryService;
import cz.fim.uhk.lab.service.UsernameResolverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/*
    MyInventoryController is the MVC controller responsible for
    serving the the app module 'My Inventory'.
 */

@Controller
@RequestMapping("/inventory")
public class MyInventoryController {

    @Autowired
    UsernameResolverService usernameResolverService;
    @Autowired
    private MyInventoryService service;

    @GetMapping("/")
    public String getBorrowedFromId(Model model) {
        Long id = usernameResolverService.findIdByUsername(((UserDetails) (SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getUsername());
        List<Borrowed> current = service.getAllCurrentBorrowedForPerson(id);
        List<Borrowed> history = service.getAllPastBorrowedForPerson(id);
        model.addAttribute("borrowedList", current);
        model.addAttribute("pastBorrowedList", history);
        model.addAttribute("returnRequest", new ItemRequest());
        return "inventory/myinventory";

    }

    @PostMapping("/")
    public String returnBorrowed(ItemRequest itemRequest) throws ResourceNotFoundException {
        service.returnItem(itemRequest);
        return "redirect:/inventory/";
    }
}
