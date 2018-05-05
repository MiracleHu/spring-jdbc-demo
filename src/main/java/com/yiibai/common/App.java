package com.yiibai.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.yiibai.customer.dao.CustomerDAO;
import com.yiibai.customer.model.Customer;

public class App {
    public static void main( String[] args ){
    	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
    	
    	//接口由Spring-Customer.xml bean配置好了就能当做实例化好的对象来使用了 
        //context相当于map，里面存了好多预先初始化好的Object
        CustomerDAO customerDAOImpl = (CustomerDAO) context.getBean("customerDAOImpl");
        Customer customer = new Customer(5, "ysdfbaizfsd",29);
        customerDAOImpl.insert(customer);
    	
        Customer customer1 = customerDAOImpl.findByCustomerId(2);
        System.out.println(customer1);
        
    }
}