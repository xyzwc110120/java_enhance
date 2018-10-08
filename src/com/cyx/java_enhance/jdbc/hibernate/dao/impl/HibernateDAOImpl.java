package com.cyx.java_enhance.jdbc.hibernate.dao.impl;

import com.cyx.java_enhance.jdbc.daoDemo.domain.Student;
import com.cyx.java_enhance.jdbc.hibernate.HibernateMock;
import com.cyx.java_enhance.jdbc.hibernate.dao.IHibernateDAO;

import java.util.List;

public class HibernateDAOImpl implements IHibernateDAO {

    @Override
    public void save(Student student) {
        HibernateMock.save(student);
    }
}
