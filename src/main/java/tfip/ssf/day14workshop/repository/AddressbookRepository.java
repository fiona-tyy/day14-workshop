package tfip.ssf.day14workshop.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import tfip.ssf.day14workshop.model.Contact;

@Repository
public class AddressbookRepository {
    
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    private static final String CONTACT_LIST = "contactlist";

    public void save(final Contact ctc){
        redisTemplate.opsForList().leftPush(CONTACT_LIST, ctc.getId());
        redisTemplate.opsForHash().put(CONTACT_LIST + "_Map", ctc.getId(), ctc);
    }

    public Contact findById(final String contactId){
        Contact result = (Contact) redisTemplate.opsForHash().get(CONTACT_LIST + "_Map", contactId);
        return result;
    }

    public List<Contact> findAll(int startIndex){
        List<Object> fromContactList = redisTemplate.opsForList()
                                            .range("contactlist", startIndex, 10);
        List<Contact> ctcs = redisTemplate.opsForHash()
                                .multiGet(CONTACT_LIST + "_Map", fromContactList)
                                .stream()
                                .filter(Contact.class::isInstance)
                                .map(Contact.class::cast)
                                .toList();
        return ctcs;
    }
}
