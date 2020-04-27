package com.example.demo.controllers;


import com.example.demo.dao.PatientRepository;
import com.example.demo.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping(path = "/index")
    public String index() {
        return  "index";
    }



    @GetMapping(path = "/patients")
    public String listpatient(Model model,
                              @RequestParam(name = "page" , defaultValue = "0") int  page,
                              @RequestParam(name = "size" , defaultValue = "4" ) int size,
                              @RequestParam(name = "keyword" , defaultValue = "") String mc

    ) {
        Page <Patient> pagepatients = patientRepository.findByNameContains(mc ,PageRequest.of(page , size));
        model.addAttribute("patients", pagepatients.getContent());
        model.addAttribute("pages" , new int[ pagepatients.getTotalPages()]);
        model.addAttribute("curentpage",page);
        model.addAttribute("size", size);
        model.addAttribute("keyword" , mc);
        return  "patients";
    }


    @GetMapping(path ="/deletePatient")
    public String delete(Long id ) {
        patientRepository.deleteById(id);
        return "redirect:/patients";
    }


    @GetMapping(path ="/formPatient")
    public String AjouterPatient(Model model) {
        model.addAttribute("patient" , new Patient());
        return  "formPatient";
    }


 @PostMapping(path = "/savePatient")
    public String savePatient(@Valid Patient patient ,BindingResult bindingResult) {

     if ( bindingResult.hasErrors() ) {
         System.out.println("before rrrrrrrrrrrrrrrrrrrr");
         return "formatPatient";
     }

     patientRepository.save(patient);
     return "formPatient";
 }


    @GetMapping(path ="/editPatient")
    public String editPatient( Model model , Long id) {
        Patient p = patientRepository.findById(id).get();
         model.addAttribute("patient", p);
        return  "formPatient";
    }


}
