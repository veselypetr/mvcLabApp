/*
 * Vytvořeno jako studentský zápočtový projekt v rámci studia FIM UHK.
 * 2021 - Petr Veselý
 */

package cz.fim.uhk.lab.controller;

import cz.fim.uhk.lab.service.LogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
    LogsController is the MVC controller responsible for
    serving the the app module 'logs'.
 */

@Controller
@RequestMapping("/logs")
public class LogsController {
    @Autowired
    LogsService service;

    @GetMapping("/")
    public String getAllLogs(Model model) {
        model.addAttribute("allBorrowed", service.getAllBorrowed());
        model.addAttribute("allTerminalHistory", service.getAllTerminalHistory());
        return "logs/logs";
    }

}
