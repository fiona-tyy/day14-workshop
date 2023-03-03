package tfip.ssf.day14workshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tfip.ssf.day14workshop.model.Contact;
import tfip.ssf.day14workshop.repository.AddressbookRepository;

@Service
public class AddressbookService {
    @Autowired
    private AddressbookRepository adrbkRepo;

    public void save(final Contact ctc){
        adrbkRepo.save(ctc);
    }

    public Contact findById(final String contactId){
        return adrbkRepo.findById(contactId);
    }

    public List<Contact> findAll(int startIndex){
        return adrbkRepo.findAll(startIndex);
    }
    
}
