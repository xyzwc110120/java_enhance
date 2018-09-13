package com.cyx.java_enhance.javaBean;

public class User {

    private String firstName;   // 名
    private String lastName;    // 姓

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return firstName + "·" + lastName;
    }

    // 在这个 com.cyx.java_enhance.javaBean 中有三个属性，分别为：firstName，lastName，fullName

}
