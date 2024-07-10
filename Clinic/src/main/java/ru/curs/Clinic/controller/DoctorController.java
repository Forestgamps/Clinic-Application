package ru.curs.Clinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.curs.Clinic.entity.Doctor;
import ru.curs.Clinic.entity.Talon;
import ru.curs.Clinic.service.DoctorService;
import ru.curs.Clinic.service.TalonService;

import java.util.List;

@Controller
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    DoctorService dao;

    @RequestMapping
    public String getAll(Model model) {
        List<Doctor> taskList = dao.getDoctors();
        model.addAttribute("taskList", taskList);
        model.addAttribute("taskSize", taskList.size());
        System.out.println("РАЗМЕР СПИСКА --- " + taskList);
        return "doctor";
    }


    @RequestMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        dao.removeDoctor(id);
        return "redirect:/";
    }

    @PostMapping("/add")
    public String addDoctor(@ModelAttribute Doctor doctor) {
        dao.addDoctor(doctor);
        return "redirect:/";
    }

    @PostMapping
        public void save(@RequestBody Doctor doctor) {
        dao.addDoctor(doctor);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") Long id) {
        dao.removeDoctor(id);
    }

}
