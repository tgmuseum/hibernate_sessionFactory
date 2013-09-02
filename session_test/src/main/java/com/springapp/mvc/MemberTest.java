package com.springapp.mvc;

import org.hibernate.Criteria;
import org.hibernate.criterion .*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: IHN
 * Date: 13. 8. 29
 * Time: 오전 10:27
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/member")
public class MemberTest {

    @Autowired
    private SessionFactory sessionFactory;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String  getTest(ModelMap model) {

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>test>>>>>>>>>>>>>>>>>>>>>>>>");
        model.addAttribute("message", "Hello testMember!!!!");

        return "hello";
    }

    @RequestMapping(value = "/testList", method = RequestMethod.GET)
    public String  getTestList(ModelMap model, @ModelAttribute("member") Member member) {
        Session session = sessionFactory.openSession();
        //Session session = sessionFactory.getCurrentSession();
        try{
            System.out.println("한글테스트>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println("name====="+member.getName());
            System.out.println("email====="+member.getEmail());
            System.out.println("phone====="+member.getPhone());

            Criteria criteria = session.createCriteria(Member.class);
            Disjunction any = Restrictions.disjunction();
            //any.add(Restrictions.like("name", "%"+member.getName()+"%"));
            any.add(Restrictions.like("email", "%"+member.getEmail()+"%"));
            any.add(Restrictions.like("phone", "%"+member.getPhone()+"%"));
            criteria.add(any);
            criteria.addOrder(Order . asc("name") . ignoreCase()) ;
            List countryList = criteria.list();

            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>test List>>>>>>>>>>>>>>>>>>>>>>>>");

            model.addAttribute("member", new Member());
            model.addAttribute("members", countryList);
        }catch (HibernateException e)  {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return "test";
    }
}
