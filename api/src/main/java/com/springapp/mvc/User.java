package com.springapp.mvc;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13. 7. 22
 * Time: 오후 1:18
 * To change this template use File | Settings | File Templates.
 */
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * The type User.
 */
/*
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "testPROC",
                query = "{?=CALL  TESTPROC7(?)}",
                hints={
                        @QueryHint(name = "org.hibernate.callable",value = "true")
                },
                resultClass = User.class
        )
})
*/

@Entity(name = "account")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    private String firstName;

    @Basic
    private String lastName;

    @Basic
    private String email;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {

        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param name the name
     */
    public void setFirstName(String name) {
        this.firstName = name;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
