package com.eazybytes.eazyschool.controller;

import com.eazybytes.eazyschool.model.Contact;
import com.eazybytes.eazyschool.service.ContactService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class ContactController {

    private static Logger log = LoggerFactory.getLogger(ContactController.class);

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping("/contact")
    public String displayContactPage(Model model){
        model.addAttribute("contact",new Contact());
        return "contact.html";
    }

//    @RequestMapping(value = "/saveMsg" , method = RequestMethod.POST)
////  @PostMapping(value = "/saveMsg")
//    public ModelAndView saveMessage(@RequestParam String name,@RequestParam(name="mobileNum") String mobileNumber,
//                                    @RequestParam String email,@RequestParam String subject,
//                                    @RequestParam String message){
//        log.info("Name : " + name);
//        log.info("Mobile Number : " + mobileNumber);
//        log.info("Email Address : " + email);
//        log.info("Subject : " + subject);
//        log.info("Message : " + message);
//        return new ModelAndView("redirect:/contact");
//
//
//    }

    @RequestMapping(value = "/saveMsg", method = RequestMethod.POST)
    public String saveMessage(@Valid @ModelAttribute("contact") Contact contact, Errors errors){
        if(errors.hasErrors()){
            log.error("Contact form validation failed due to : " +errors.toString());
            return "contact.html";
        }
        contactService.saveMessageDetails(contact);
//        contactService.setCounter(contactService.getCounter()+1);
//        log.info("Number of times the Contact form is submitted : "+contactService.getCounter());


        return "redirect:/contact";
    }

    @RequestMapping("/displayMessages/page/{pageNum}")
    public  ModelAndView displayMessages(Model model,
                                         @PathVariable(name = "pageNum") int pageNum,@RequestParam("sortField") String sortField,
                                         @RequestParam("sortDir") String sortDir){
        Page<Contact> msgPage = contactService.findMsgsWithOpenStatus(pageNum,sortField,sortDir);
        List<Contact> contactMsgs = msgPage.getContent();
        ModelAndView modelAndView = new ModelAndView("messages.html");
        model.addAttribute("currentPage",pageNum);
        model.addAttribute("totalPages",msgPage.getTotalPages());
        model.addAttribute("totalMsgs",msgPage.getTotalElements());
        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir",sortDir.equals("asc") ? "desc" : "asc");
        modelAndView.addObject("contactMsgs",contactMsgs);
        return modelAndView;
    }

    @RequestMapping(value = "/closeMsg",method = RequestMethod.GET)
    public String closeMsg(@RequestParam int id /* , Authentication authentication */){

        contactService.updateMessageStatus(id);
        return "redirect:/displayMessages/page/1?sortField=name&sortDir=desc";
    }

}
