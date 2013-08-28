package com.springapp.mvc;

import org.hibernate.StatelessSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.sql.*;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13. 7. 25
 * Time: 오후 12:53
 * To change this template use File | Settings | File Templates.
 */
@Component
@Scope("singleton")
@Path("/customer")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class UserRestful {

    @Autowired
    UserRepository userRepository;

    @PersistenceContext (unitName = "oracleUnit")
    private EntityManager entityManager;

    @PersistenceContext (unitName = "mysqlUnit")
    private EntityManager mysqlEntityManager;

    @GET
    @Path("/list")
    public UserResponse getUsers() {
        UserResponse userResponse = new UserResponse();
        List<User> users = userRepository.findAll();
        userResponse.setUsers(users);
        for (int i = 0; i < users.size(); i++) {
            printUser(users.get(i));
        }
        return userResponse;
    }

    @GET
    @Path("/{userId}")
    public UserResponse getUser(@PathParam("userId") Long id) {
        UserResponse userResponse = new UserResponse();

        User user = userRepository.findOne(id);
        printUser(user);

        userResponse.setId(user.getId());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());

        return userResponse;
    }

    @GET
    @Path("/oracle/{userId}")
    @Transactional(value = "transactionManager2")
    public UserResponse getOracle(@PathParam("userId") Long id){
        UserResponse userResponse = new UserResponse();
/*
        // 최후의 수단!! JDBC 커넥션을 맺어서 proceduer을 호출한다!!

        Connection conn = null;
        PreparedStatement pstmt = null;

        //디비의 주소와 디비정보들..
        final String IP = "192.168.44.128";
        String jdbc_driver = "oracle.jdbc.driver.OracleDriver";
        String jdbc_url = "jdbc:oracle:thin:@" + IP + ":1521:XE";

        //JDBC 드라이버 로드
        try {
            Class.forName(jdbc_driver);
            //데이터베이스 연결
            conn = DriverManager.getConnection(jdbc_url, "test", "1234");

            CallableStatement cs = conn.prepareCall("{ call TESTPROC7(?) }");
            cs.setInt(1,2);  // first parameter index start with 1
            cs.execute();  // call stored procedure

            cs.close();
            conn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
*/

        try {
            Query query = entityManager.createNativeQuery("call TESTPROC7(?)");
            query.setParameter(1, 2);
            query.executeUpdate();

            //System.out.print(">>>>>>>>>>>>"+ (String) query.getSingleResult());
           //userResponse.setEmail((String) query.getSingleResult());
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        return userResponse;
    }

    @GET
    @Path("/mysql/{userId}")
    @Transactional(value = "transactionManager")
    public UserResponse getMysql(@PathParam("userId") Long id){
        UserResponse userResponse = new UserResponse();

        try {
            Query query = mysqlEntityManager.createNativeQuery("update account set email=? where id=1");
            query.setParameter(1, "test@naver.com");
            query.executeUpdate();

            //System.out.print(">>>>>>>>>>>>"+ (String) query.getSingleResult());
            //userResponse.setEmail((String) query.getSingleResult());
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        return userResponse;
    }

    @GET
    @Path("/old")
    public Response getUser() {
        return Response.ok().entity("<xml>씨팔</xml>").build();
    }

    private void printUser(final User user) {
        System.out.println("-------------------");
        System.out.println(user.getId());
        System.out.println(user.getFirstName());
        System.out.println(user.getLastName());
        System.out.println(user.getEmail());
    }
}
