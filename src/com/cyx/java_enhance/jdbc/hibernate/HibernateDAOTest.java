package com.cyx.java_enhance.jdbc.hibernate;

import com.cyx.java_enhance.jdbc.daoDemo.domain.Student;
import com.cyx.java_enhance.jdbc.hibernate.dao.IHibernateDAO;
import com.cyx.java_enhance.jdbc.hibernate.dao.impl.HibernateDAOImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HibernateDAOTest {

    private IHibernateDAO hibernateDAO = new HibernateDAOImpl();

    @Test
    @DisplayName("保存学生信息")
    void testSave() {
        Student student = new Student("哈哈哈", 12);
        hibernateDAO.save(student);
    }


}
