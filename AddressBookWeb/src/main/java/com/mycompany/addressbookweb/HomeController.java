package com.mycompany.addressbookweb;

import com.mycompany.addressbookweb.dao.AddressDao;
import com.mycompany.addressbookweb.dto.Address;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    private AddressDao addressDao;

    @Inject
    public HomeController(AddressDao dao) {
        this.addressDao = dao;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Map model) {

        List<Address> addresses = addressDao.list();

        model.put("addresses", addresses);

        return "home";
    }


}
