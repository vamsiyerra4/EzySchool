package com.eazybytes.eazyschool.service;

import com.eazybytes.eazyschool.config.EazySchoolProps;
import com.eazybytes.eazyschool.constants.EazySchoolConstants;
import com.eazybytes.eazyschool.model.Contact;
import com.eazybytes.eazyschool.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/*
@Slf4j annotaion generates a logger field inside the class
 */
@Slf4j
@Service
//@RequestScope
//@SessionScope
//@ApplicationScope
public class ContactService {

//    private static Logger log = LoggerFactory.getLogger(ContactService.class);
//    private int counter = 0;
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    EazySchoolProps eazySchoolProps;


    public ContactService(){

        System.out.println("Contact service Bean initialized");
    }
    public boolean saveMessageDetails(Contact contact){

        boolean isSaved = false;
        contact.setStatus(EazySchoolConstants.OPEN);
//        contact.setCreatedBy(EazySchoolConstants.ANONYMOUS);
//        contact.setCreatedAt(LocalDateTime.now());

        Contact savedContact = contactRepository.save(contact);
        if(null != savedContact && savedContact.getContactId()>0){
            isSaved = true;
        }

//        log.info(contact.toString());

        return isSaved;
    }

//    public int getCounter() {
//        return counter;
//    }

//    public void setCounter(int counter) {
//        this.counter = counter;
//    }

    public Page<Contact> findMsgsWithOpenStatus(int pageNum,String sortField,String sortDir){
        int pageSize = eazySchoolProps.getPageSize();
        if(null!=eazySchoolProps.getContact() && null!=eazySchoolProps.getContact().get("pageSize")){
            pageSize = Integer.parseInt(eazySchoolProps.getContact().get("pageSize").trim());
        }
        Pageable pageable = PageRequest.of(pageNum -1,pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending());
        Page<Contact> msgPage = contactRepository.findByStatusWithQuery(EazySchoolConstants.OPEN,pageable);
        return msgPage;
    }

    public boolean updateMessageStatus(int contactId){

        boolean isUpdated = false;
//        Optional<Contact> contact =contactRepository.findById(contactId);
//        contact.ifPresent(contact1 -> {
//            contact1.setStatus(EazySchoolConstants.Close);
////            contact1.setUpdatedBy(updateBy);
////            contact1.setUpdatedAt(LocalDateTime.now());
//        });

        int rows = contactRepository.updateMsgStatusNative(EazySchoolConstants.Close,contactId);
        if(rows > 0){
            isUpdated = true;
        }
        return isUpdated;
    }
}
