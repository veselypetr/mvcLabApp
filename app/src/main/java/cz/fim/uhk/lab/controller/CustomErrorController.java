/*
 * Vytvořeno jako studentský zápočtový projekt v rámci studia FIM UHK.
 * 2021 - Petr Veselý
 */

package cz.fim.uhk.lab.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/*
    CustomErrorController is the MVC controller responsible of
    serving, and in cooperation with thymeleaf, displaying of
    custom, themed error pages.
 */

@Controller
public class CustomErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {

        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");

        if (throwable != null && model != null) {
            model.addAttribute("message", throwable.toString());
            model.addAttribute("status", statusCode.toString());
            return "error";
        } else if (model != null) {
            model.addAttribute("message", "You have encountered an error");
            model.addAttribute("status", statusCode.toString());
            return "error";
        }
        model.addAttribute("message", "You have encountered a generic error");
        model.addAttribute("status", "Error");
        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
