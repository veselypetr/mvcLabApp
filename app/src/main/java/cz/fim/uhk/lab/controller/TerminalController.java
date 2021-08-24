/*
 * Vytvořeno jako studentský zápočtový projekt v rámci studia FIM UHK.
 * 2021 - Petr Veselý
 */

package cz.fim.uhk.lab.controller;

import cz.fim.uhk.lab.model.AccessTerminalHistory;
import cz.fim.uhk.lab.model.PersonRequest;
import cz.fim.uhk.lab.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
    TerminalController is the MVC controller responsible for
    serving the the app module 'Terminal'.
 */


@Controller
@RequestMapping("/terminal")
public class TerminalController {

    @Autowired
    TerminalService service;

    @GetMapping("/")
    public String redirectToId(Model model) {
        return "redirect:/terminal/1";
    }

    @GetMapping("/{id}")
    public String useTerminal(Model model, @PathVariable("id") Long terminalID) {
        model.addAttribute("terminal", service.getTerminal(terminalID));
        model.addAttribute("personRequest", new PersonRequest());
        model.addAttribute("ath", new AccessTerminalHistory());
        return "terminal/terminal";
    }

    @PostMapping("/{id}/addAccess")
    public String addAccess(AccessTerminalHistory ath, PersonRequest pr, @PathVariable("id") Long terminalID) {
        service.addHistory(ath, pr, terminalID);
        return "redirect:";
    }
}
