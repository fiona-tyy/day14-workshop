package tfip.ssf.day14workshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import tfip.ssf.day14workshop.model.Contact;
import tfip.ssf.day14workshop.service.AddressbookService;


@Controller
@RequestMapping(path="/contact")
public class AddressBookController {
  
    @Autowired
    private AddressbookService adrbkSvc;

    @GetMapping
    public String showAddressBookForm(Model model){
        model.addAttribute("contact", new Contact());
        return "addressbook";
    }

    
    @PostMapping
    public String saveContact(@Valid Contact contact, BindingResult binding, Model model){
        if(binding.hasErrors()){
            return "addressbook";
        }
        adrbkSvc.save(contact);
        return "showContact";
    }

    @GetMapping(path="{contactId}")
    public String getContactId(Model model, @PathVariable String contactId){
        Contact ctc = adrbkSvc.findById(contactId);
        model.addAttribute("contact", ctc);
        return "showContact";
    }

    @GetMapping(path="/list")
    public String getAllContacts(Model model, @RequestParam Integer startIndex){
        List<Contact> ctcs = adrbkSvc.findAll(startIndex);
        model.addAttribute("contacts", ctcs);
        return "contacts";
    }
    
}
