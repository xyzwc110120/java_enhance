package com.cyx.java_enhance.jdbc.daoDemo.dao;

import com.cyx.java_enhance.jdbc.daoDemo.domain.Student;

import java.util.List;

// 封装 Student 对象的 CRUD 操作
public interface IStudentDAO {

    /**
     * 保存操作（增）
     *
     * @param student 学生对象，封装了需要保存的信息
     */
    void save(Student student);

    /**
     * 删除操作（删）
     *
     * @param id 被删除的学生的主键值
     */
    void delete(Long id);

    /**
     * 修改操作（改）
     *
     * @param student 学生对象，新的学生信息
     */
    void update(Student student);

    /**
     * 查询并返回指定 id 的学生对象
     *
     * @param id 被查询学生的主键值
     * @return 如果 id 存在，返回该学生对象，否则，返回 null
     */
    Student get(Long id);

    /**
     * 查询并返回所有学生对象
     *
     * @return 如果集合为空，返回一个空的 List 对象
     */
    List<Student> getAll();

}
