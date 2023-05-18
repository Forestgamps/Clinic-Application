package ru.curs.Clinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.curs.Clinic.entity.Talon;
import ru.curs.Clinic.service.TalonService;

import java.util.List;

@Controller
@RequestMapping("/talon")
public class TalonController {
    @Autowired
    TalonService dao;

    @RequestMapping
    public String getAll(Model model) {
        List<Talon> taskList = dao.getTalons();
        model.addAttribute("taskList", taskList);
        model.addAttribute("taskSize", taskList.size());
        System.out.println("РАЗМЕР СПИСКА --- " + taskList);
        return "talon";
    }
    @RequestMapping("/talonHirurg")
    public String getAllHirurg(Model model) {
        List<Talon> taskList = dao.getTalons();
        model.addAttribute("taskList", taskList);
        return "talonHirurg";
    }

    @RequestMapping("/talonDentist")
    public String getAllDentist(Model model) {
        List<Talon> taskList = dao.getTalons();
        model.addAttribute("taskList", taskList);
        return "talonDentist";
    }

//    @GetMapping
//    public List<Talon> getAll() {
//        return dao.getTalons();
//    }

    @RequestMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        dao.removeTalon(id);
        return "redirect:/";
    }

    @PostMapping("/Hirurg/add")
    public String addTalonHirurg(@ModelAttribute Talon talon) {
        talon.setDocSpec("Врач-Хирург");
        dao.addTalon(talon);
        return "redirect:/";
    }

    @PostMapping("/Dentist/add")
    public String addTalonDentist(@ModelAttribute Talon talon) {
        talon.setDocSpec("Зубной Врач");
        dao.addTalon(talon);
        return "redirect:/";
    }
    @PostMapping
    public void save(@RequestBody Talon talon) {
        dao.addTalon(talon);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") Long id) {
        dao.removeTalon(id);
    }

}
