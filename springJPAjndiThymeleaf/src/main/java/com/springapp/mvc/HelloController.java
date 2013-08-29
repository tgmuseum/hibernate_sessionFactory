package com.springapp.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.xml.sax.AttributeList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * The type Hello controller.
 */
@Controller
@RequestMapping("/")
public class HelloController {

    @Autowired
    private ComnRepository comnRepository;

    @PersistenceContext (unitName = "oracleUnit")
    private EntityManager oracleEntityManager;

    @PersistenceContext (unitName = "mysqlUnit")
    private EntityManager mysqlEntityManager;

    @RequestMapping("thymeleaf")
    public String thymeleafTest(ModelMap model){

        model.addAttribute("products", comnRepository.findAll());
        /*
        List<AccountEntity> list = comnRepository.findAll();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getId());
        }
        */
        return "thymeleaf";
    }

    /**
     * Print welcome.
     * JNDI lookup해서 DB호출
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            Context ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
            conn = ds.getConnection();

            pstmt = conn.prepareStatement("select sysdate from dual");
            rs = pstmt.executeQuery();

            while(rs.next()){
                System.out.println(">>>>>"+rs.getString(1));
            }
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            try{
                if( rs != null ) rs.close();
                if( pstmt != null ) pstmt.close();
                if( conn != null ) conn.close();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }

		model.addAttribute("message", "Hello world!");
		return "hello";
	}

    /**
     * Test oracle.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping("oracle")
    @Transactional(value = "transactionManager2")
    public String testOracle(ModelMap model) {

        try {
            //Query query = oracleEntityManager.createNativeQuery("call TESTPROC7(?)");
            Query query = oracleEntityManager.createNamedQuery("CALL_ORACLE_TESTPROC");
            query.setParameter(1, 2);
            query.executeUpdate();

            System.out.print(">>>>>> oracle success >>>>>>");
            model.addAttribute("message", "oracle success!");
        } catch (Exception e) {
            e.printStackTrace(System.out);
            model.addAttribute("message", "oracle fail!");
        }

        return "hello";
    }

    /**
     * Test mysql.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping("mysql")
    @Transactional(value = "transactionManager")
    public String testMysql(ModelMap model) {

        try {
            Query query = mysqlEntityManager.createNativeQuery("update account set email=? where id=1");
            query.setParameter(1, "test@naver.com");
            query.executeUpdate();

            System.out.print(">>>>>> mysql success >>>>>>");
            model.addAttribute("message", "mysql success!");
        } catch (Exception e) {
            e.printStackTrace(System.out);
            model.addAttribute("message", "mysql fail!");
        }

        return "hello";
    }
}