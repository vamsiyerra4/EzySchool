package com.eazyschool.ConsumerRest.proxy;

import com.eazyschool.ConsumerRest.config.ProjectConfiguration;
import com.eazyschool.ConsumerRest.model.Contact;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.module.Configuration;
import java.util.List;

@FeignClient(name = "contact",url = "http://localhost:8080/api/contact",
        configuration = ProjectConfiguration.class)
public interface ContactProxy {

    @RequestMapping(method = RequestMethod.GET,value = "/getMessageByStatus")
    @Headers(value = "Contact-Type:application/json")
    public List<Contact> getMessagesByStatus(@RequestParam("status") String status);
}
