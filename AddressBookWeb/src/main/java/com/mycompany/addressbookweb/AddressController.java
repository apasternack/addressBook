/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.addressbookweb;

import com.mycompany.addressbookweb.dao.AddressDao;
import com.mycompany.addressbookweb.dto.Address;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author apprentice
 */
@Controller
@RequestMapping(value = "/address")
public class AddressController {

    private AddressDao addressDao;

    @Inject
    public AddressController(AddressDao dao) {
        this.addressDao = dao;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public Address create(@Valid @RequestBody Address address) {

        return addressDao.create(address);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") Integer contactId) {

        addressDao.delete(contactId);

    }


//    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
//    public String edit(@PathVariable("id") Integer addressId, Map model) {
//
//        Address address = addressDao.get(addressId);
//
//        model.put("address", address);
//
//        return "edit";
//    }
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseBody
    public Address editSubmit(@Valid @RequestBody Address address) {

        addressDao.update(address);

        return address;

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Address show(@PathVariable("id") Integer contactId) {

        Address address = addressDao.get(contactId);

        return address;

    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search() {

        return "search";

    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String edit(@Valid @RequestParam("search") String search, @RequestParam("searchBy") String searchBy, Map model) {

        List<Address> searchResults = null;

        switch (searchBy) {
            case "lastName":
                searchResults = addressDao.searchByLastName(search);
                break;
            case "state":
                searchResults = addressDao.searchByState(search);
                break;
            case "city":
                searchResults = addressDao.searchByCity(search);
                break;
            case "zipCode":
                searchResults = addressDao.searchByZip(search);
                break;

        }

        model.put("searchResults", searchResults);

        return "search";
    }
}
