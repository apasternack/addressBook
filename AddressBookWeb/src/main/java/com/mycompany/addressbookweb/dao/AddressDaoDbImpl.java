/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.addressbookweb.dao;

import com.mycompany.addressbookweb.dto.Address;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class AddressDaoDbImpl implements AddressDao {

    private static final String SQL_INSERT_ADDRESS = "INSERT INTO addressBook.address (firstName, lastName, streetName, streetNumber, city, state, zip) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_ADDRESS = "UPDATE address SET firstName = ?, lastName=?, streetName=?, streetNumber=?, city=?, state=?, zip=? WHERE id=?";
    private static final String SQL_DELETE_ADDRESS = "DELETE FROM address where id = ?";
    private static final String SQL_GET_ADDRESS = "SELECT * FROM address where id = ?";
    private static final String SQL_GET_ADDRESS_LIST = "SELECT * FROM address";
    private static final String SQL_SEARCH_LAST_NAME = "SELECT * FROM address WHERE lastName = ?";
    private static final String SQL_SEARCH_CITY = "SELECT * FROM address WHERE city = ?";
    private static final String SQL_SEARCH_STATE = "SELECT * FROM address WHERE state = ?";
    private static final String SQL_SEARCH_ZIP = "SELECT * FROM address WHERE zip = ?";

    private JdbcTemplate jdbcTemplate;

    public AddressDaoDbImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Address create(Address address) {

        jdbcTemplate.update(SQL_INSERT_ADDRESS,
                address.getFirstName(),
                address.getLastName(),
                address.getStreetName(),
                address.getStreetNumber(),
                address.getCity(),
                address.getState(),
                address.getZip());

        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);  //gets next unique id

        address.setId(id);

        return address;

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(Address address) {

        jdbcTemplate.update(SQL_UPDATE_ADDRESS,
                address.getFirstName(),
                address.getLastName(),
                address.getStreetName(),
                address.getStreetNumber(),
                address.getCity(),
                address.getState(),
                address.getZip(),
                address.getId());

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Address get(Integer id) {

        return jdbcTemplate.queryForObject(SQL_GET_ADDRESS, new AddressMapper(), id);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Integer id) {

        jdbcTemplate.update(SQL_DELETE_ADDRESS, id);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Address> list() {

        return jdbcTemplate.query(SQL_GET_ADDRESS_LIST, new AddressMapper());

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Address> searchByLastName(String lastName) {

        return jdbcTemplate.query(SQL_SEARCH_LAST_NAME, new AddressMapper(), lastName);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Address> searchByCity(String city) {

        return jdbcTemplate.query(SQL_SEARCH_CITY, new AddressMapper(), city);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Address> searchByState(String state) {

        return jdbcTemplate.query(SQL_SEARCH_STATE, new AddressMapper(), state);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Address> searchByZip(String zip) {

        return jdbcTemplate.query(SQL_SEARCH_ZIP, new AddressMapper(), zip);

    }

    private static final class AddressMapper implements RowMapper<Address> {

        @Override
        public Address mapRow(ResultSet rs, int i) throws SQLException {

            Address address = new Address();

            address.setId(rs.getInt("id"));  //getting the data from "id" column for this iteration through a row
            address.setFirstName(rs.getString("firstName"));
            address.setLastName(rs.getString("lastName"));
            address.setStreetName(rs.getString("streetName"));
            address.setStreetNumber(rs.getString("streetNumber"));
            address.setCity(rs.getString("city"));
            address.setState(rs.getString("state"));
            address.setZip(rs.getString("zip"));

            return address;

        }

    }

}
