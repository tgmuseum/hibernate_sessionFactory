package com.springapp.mvc;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13. 7. 25
 * Time: 오후 5:59
 * To change this template use File | Settings | File Templates.
 */
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.xml.bind.annotation.XmlTransient;

/**
 * Base class for APIWrapper implementations to inject the EntityConfiguration reference.
 */
public abstract class UserBaseWrapper implements ApplicationContextAware {

    @XmlTransient
    protected ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
