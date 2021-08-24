/*
 * Vytvořeno jako studentský zápočtový projekt v rámci studia FIM UHK.
 * 2021 - Petr Veselý
 */

package cz.fim.uhk.lab.controller;

import cz.fim.uhk.lab.exeption.RecordNotFoundException;
import cz.fim.uhk.lab.model.Person;
import cz.fim.uhk.lab.model.PrivilegeLevelCodebook;
import cz.fim.uhk.lab.security.UserManagementService;
import cz.fim.uhk.lab.service.UploadHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/*
    UsersManagementController is the MVC controller for the module 'User Management'.
    It passes data to the thymeleaf model for the creation of the list
    existing users, handles creation, deletion, and editing of users.
 */

@Controller
@RequestMapping("/users")
public class UsersManagementController {
    @Autowired
    UserManagementService service;
    @Autowired
    UploadHandlerService uploadHandlerService;

    @GetMapping("/")
    public String getAllEmployees(Model model) {
        List<Person> list = service.getAllPersons();
        model.addAttribute("personList", list);
        return "users/list-persons";
    }

    @GetMapping("/edit-person/{id}")
    public String getPersonById(Model model, @PathVariable("id") Long id) {
        Person existingPerson = null;
        List<PrivilegeLevelCodebook> codebookList = null;
        try {
            existingPerson = service.getPersonById(id);
            codebookList = service.getAllAvailablePrivilegeCodebookLevels(id);
        } catch (RecordNotFoundException e) {
            e.printStackTrace();
        }
        model.addAttribute("person", existingPerson);
        model.addAttribute("codebookList", codebookList);
        return "users/edit-person";
    }

    @PostMapping(path = "/edit-person/{id}")
    public String editPerson(Person person, @PathVariable("id") Long id) {
        service.updatePerson(id, person);
        return "redirect:/users/edit-person/" + id;
    }

    @PostMapping(path = "/edit-person/{id}/update-photo")
    public String uploadPhoto(@RequestParam("file") MultipartFile file, @PathVariable("id") Long id) {
        uploadHandlerService.handlePersonPhoto(file, id);
        return "redirect:/users/edit-person/" + id;
    }


    @GetMapping(path = "/create-person")
    public String createPerson(Model model) {
        Long id = service.createPerson();
        return "forward:/users/edit-person/" + id;
    }

    @GetMapping(path = "/{personid}/delete-priv/{privid}")
    public String deletePriv(@PathVariable("personid") Long personid, @PathVariable("privid") Long privid) {
        service.deletePrivilege(personid, privid);
        return "forward:/users/edit-person/" + personid;
    }

    @GetMapping(path = "/{personid}/add-priv/{codebookid}")
    public String addPriv(@PathVariable("personid") Long personid, @PathVariable("codebookid") Long codebookid) {
        service.addPrivilege(personid, codebookid);
        return "forward:/users/edit-person/" + personid;
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.GET)
    public String deletePersonById(Model model, @PathVariable("id") Long id)
            throws RecordNotFoundException {
        service.deletePersonById(id);
        return "redirect:/users/";
    }


}
