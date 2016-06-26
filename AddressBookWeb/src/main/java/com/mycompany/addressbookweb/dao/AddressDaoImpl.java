/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.addressbookweb.dao;

import com.mycompany.addressbookweb.dto.Address;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apprentice
 */
public class AddressDaoImpl implements AddressDao {

    private List<Address> addresses = new ArrayList();
    private int nextId;
    private String readWriteFile;

    public AddressDaoImpl() {
        readWriteFile = "address.txt";
        addresses = decode(readWriteFile);
        if (addresses == null) {
            addresses = new ArrayList();
        }
    }

    public AddressDaoImpl(String readWriteFile) {
        this.readWriteFile = readWriteFile;
        addresses = decode(readWriteFile);
        if (addresses == null) {
            addresses = new ArrayList();
        }
    }

    @Override
    public Address create(Address address) {

        int highestId = 0;

        for (Address myAddress : addresses) {
            if (myAddress.getId() > highestId) {
                highestId = myAddress.getId();
            }
        }

        nextId = highestId + 1;

        address.setId(nextId);

        addresses.add(address);

        encode(readWriteFile);

        return address;
    }

    @Override
    public Address get(Integer id) {

        for (Address myAddress : addresses) {
            if (myAddress.getId() == id) {
                return myAddress;
            }
        }
        return null; //if get returns null, the requested  student is not in the database
    }

    @Override
    public List<Address> searchByState(String state) {

        List<Address> sortedAddresses = new ArrayList();

        for (Address address : addresses) {
            if (state.equals(address.getState())) {
                sortedAddresses.add(address);
            }
        }

        return sortedAddresses;

//        Map<String, List<Address>> addressesInAStateByCity = new HashMap<String, List<Address>>();
//
//        List<Address> addressesInState = new ArrayList<>();
//
//        Set<String> uniqueCities = new HashSet<>();
//
//        for (Address address : addresses) {
//            if (state.equalsIgnoreCase(address.getState())) {
//                addressesInState.add(address);
//            }
//        }
//
//        for (Address address : addressesInState) {
//            uniqueCities.add(address.getCity());
//        }
//
//        for (String city : uniqueCities) {
//
//            List<Address> addressesInACity = new ArrayList<>();
//            for (Address address : addressesInState) {
//
//                if (city.equalsIgnoreCase(address.getCity())) {
//
//                    addressesInACity.add(address);
//                }
//            }
//            addressesInAStateByCity.put(city, addressesInACity);
//        }
//        return addressesInAStateByCity;
    }

    @Override
    public List<Address> searchByZip(String zipCode) {

        List<Address> sortedAddresses = new ArrayList();

        for (Address address : addresses) {
            if (zipCode.equals(address.getZip())) {
                sortedAddresses.add(address);
            }
        }

        return sortedAddresses;

    }

    @Override
    public List<Address> searchByLastName(String lastName) {

        List<Address> sortedAddresses = new ArrayList<>();

        for (Address address : addresses) {
            if (lastName.equalsIgnoreCase(address.getLastName())) {
                sortedAddresses.add(address);
            }
        }

        return sortedAddresses;

    }

    @Override
    public List<Address> searchByCity(String city) {

        List<Address> sortedAddresses = new ArrayList();

        for (Address address : addresses) {
            if (city.equalsIgnoreCase(address.getCity())) {
                sortedAddresses.add(address);
            }
        }

        return sortedAddresses;

    }

    @Override
    public List<Address> list() {
        return new ArrayList(addresses);
    }

    @Override
    public void update(Address address) {

        Address found = new Address();

        for (Address myAddress : addresses) {

            if (myAddress.getId() == address.getId()) {

                found = myAddress;

            }
        }
        addresses.remove(found);
        addresses.add(address);
        encode(readWriteFile);
    }

    @Override
    public void delete(Integer id) {

        Address found = null;

        for (Address myAddress : addresses) {

            if (Objects.equals(myAddress.getId(), id)) {
                found = myAddress;
                break;

            }

        }

        addresses.remove(found);

        encode(readWriteFile);

    }

    private void encode(String file) {

        final String TOKEN = "::";

        try {
            PrintWriter out = new PrintWriter(new FileWriter(file));

            for (Address myAddress : addresses) {

                out.print(myAddress.getId());
                out.print(TOKEN);

                out.print(myAddress.getFirstName());
                out.print(TOKEN);

                out.print(myAddress.getLastName());
                out.print(TOKEN);

                out.print(myAddress.getState());
                out.print(TOKEN);

                out.print(myAddress.getCity());
                out.print(TOKEN);

                out.print(myAddress.getStreetName());
                out.print(TOKEN);

                out.print(myAddress.getStreetNumber());
                out.print(TOKEN);

                out.print(myAddress.getZip());

                out.println("");
            }

            out.flush();
            out.close();

        } catch (IOException ex) {
            Logger.getLogger(AddressDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private List<Address> decode(String file) {

        List<Address> addressList = new ArrayList();

        try {

            Scanner sc;

            sc = new Scanner(new BufferedReader(new FileReader(file)));

            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();

                String[] stringParts = currentLine.split("::");

                Address myAddress = new Address();

                int id = Integer.parseInt(stringParts[0]);

                myAddress.setId(id);                    //puts id number back into student object
                myAddress.setFirstName(stringParts[1]);      //puts name back into student object
                myAddress.setLastName(stringParts[2]);
                myAddress.setState(stringParts[3]);
                myAddress.setCity(stringParts[4]);
                myAddress.setStreetName(stringParts[5]);
                myAddress.setStreetNumber(stringParts[6]);
                if (stringParts.length < 8) {
                    myAddress.setZip("");
                } else if (stringParts.length == 8) {
                    myAddress.setZip(stringParts[7]);
                }

                addressList.add(myAddress); //adds student object "myStudent" to AL of Student objects
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AddressDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }

        return addressList;
    }

}
