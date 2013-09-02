package com.springapp.mvc;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: IHN
 * Date: 13. 8. 29
 * Time: 오전 10:22
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "account", uniqueConstraints = @UniqueConstraint(columnNames = {"id"}))
public class Member implements Serializable {

    @Id
    @Column(name = "id", length = 15)
    private String memberId;

    @Column(name = "first_name", length = 20)
    private String name;

    @Column(name = "email", length = 30)
    private String email;

    @Column(name = "price", length = 15)
    private String phone;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
