package com.example.customer.manager;

import com.example.customer.dao.IRoleDAO;
import com.example.customer.model.Customer;
import com.example.customer.dao.ICutomerDAO;
import com.example.customer.model.CustomerCredential;
import com.example.customer.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.List;

public class CustomerManager {

    @Value("${osman.my.prop}")
    private ECacheType cahceType;

    @Autowired
    // @Qualifier("cutomerDAO")
    private ICutomerDAO cutomerDAO;

    @Autowired
    private IRoleDAO roleDAO;

    @Autowired
    private CustomerCache customerCache;


    public CustomerManager(){

    }

    @PostConstruct
    public void init(){
        List<Customer> allCustomers = cutomerDAO.getAllCustomers();
        customerCache.refreshMap(allCustomers);
    }

    public List<Customer> getAllCustomers() {
        return cutomerDAO.getAllCustomers();
    }

    public boolean createCustomer(Customer customer) {
        return cutomerDAO.createCustomer(customer);
    }

    public List<Customer> getCustomerByName(String name) {
        return cutomerDAO.getCustomerByName(name);
    }

    public boolean createRole(Role role) {
        return roleDAO.createRole(role);
    }

    public List<Role> getAllRoles(){
        return roleDAO.getAllRoles();
    }

    public CustomerCredential loadUserByUsernameWithUsername(String s) {
        CustomerCredential customerCredential = cutomerDAO.getCustomerCredential(s);
        return customerCredential;
    }
}
