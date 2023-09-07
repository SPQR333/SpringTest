package ru.ahmed.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ahmed.spring.dao.PersonDAO;


@Controller
public class FirstController {

    final
    PersonDAO personDAO;

    @Autowired
    public FirstController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.show(id));
        return "people/show";
    }

    @GetMapping("calculator")
    public String calc(@RequestParam(value = "one", required = false) int one,
                       @RequestParam(value = "two", required = false) int two,
                       @RequestParam(value = "action", required = false) String action,
                       Model model) {
        int result;

        switch (action) {
            case "minus":
                result = one - two;
                break;
            case "plus":
                result = one + two;
                break;
            case "um":
                result = one * two;
                break;

            default:
                result = 0;
                break;

        }
        model.addAttribute("result", "result  " + result);


        return "people/hello";


    }

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "surname", required = false) String surname,
                           Model model) {
        model.addAttribute("message", "-" + name + "" + surname);
        return "people/hello";
    }

    @GetMapping("/goodbye")
    public String sayGoodbye() {

        return "people/goodbye";
    }
}
