/*
 * Vytvořeno jako studentský zápočtový projekt v rámci studia FIM UHK.
 * 2021 - Petr Veselý
 */

package cz.fim.uhk.lab.controller;

import cz.fim.uhk.lab.exeption.RecordNotFoundException;
import cz.fim.uhk.lab.model.Person;
import cz.fim.uhk.lab.security.UserManagementService;
import cz.fim.uhk.lab.service.UploadHandlerService;
import cz.fim.uhk.lab.service.UsernameResolverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/*
    MyProfileController is the MVC controller responsible for
    serving the the app module 'My Profile'.
 */

@Controller
@RequestMapping("/myprofile")
public class MyProfileController {
    @Autowired
    UserManagementService service;

    @Autowired
    UsernameResolverService usernameResolverService;

    @Autowired
    UploadHandlerService uploadHandlerService;


    @GetMapping("/")
    public String getMyProfile(Model model) {
        Long id = usernameResolverService.findIdByUsername(((UserDetails) (SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getUsername());
        try {
            model.addAttribute("person", service.getPersonById(id));
        } catch (RecordNotFoundException e) {
            e.printStackTrace();
        }
        return "myprofile/myprofile";
    }

    @GetMapping("/update")
    public String updateMyProfile(Model model) {
        Long id = usernameResolverService.findIdByUsername(((UserDetails) (SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getUsername());
        try {
            model.addAttribute("person", service.getPersonById(id));
        } catch (RecordNotFoundException e) {
            e.printStackTrace();
        }
        return "myprofile/update";
    }

    @PostMapping(path = "/update/update-photo")
    public String uploadPhoto(@RequestParam("file") MultipartFile file) {
        Long id = usernameResolverService.findIdByUsername(((UserDetails) (SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getUsername());
        uploadHandlerService.handlePersonPhoto(file, id);
        return "redirect:/myprofile/";
    }


    @PostMapping("/update")
    public String editMyProfile(Person person) {
        Long id = usernameResolverService.findIdByUsername(((UserDetails) (SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getUsername());
        service.updatePerson(id, person);
        return "redirect:/myprofile/";
    }

}
