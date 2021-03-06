/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.addressbookweb.dao;

import com.mycompany.addressbookweb.dto.Address;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface AddressDao {
    
    
    public Address create(Address address);
    public void update(Address address);
    public Address get(Integer id);
    public void delete(Integer id);

    public List<Address> list();
    public List<Address> searchByLastName(String lastName);
    public List<Address> searchByCity(String city);
    public List<Address> searchByState(String state);
    public List<Address> searchByZip(String zip);


    
}
