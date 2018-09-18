package com.cyx.java_enhance.jdbc.daoDemo.dao;

import com.cyx.java_enhance.jdbc.daoDemo.domain.Teacher;

import java.util.List;

public interface ITeacherDao {

    /**
     * 保存老师信息
     */
    void save(Teacher teacher);

    /**
     * 修改指定主键值的老师信息
     */
    void update(Teacher teacher);

    /**
     * 删除指定主键值的老师
     */
    void delete(Long id);

    /**
     * 获取指定主键值的老师信息
     */
    Teacher get(Long id);

    /**
     * 获取所有老师信息
     */
    List<Teacher> getAll();

}
