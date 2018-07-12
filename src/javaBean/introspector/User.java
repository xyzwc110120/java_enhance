package javaBean.introspector;

import lombok.Getter;
import lombok.Setter;

public class User {

    @Getter
    @Setter
    private String name;

    @Getter
    private int age;

    @Setter
    private boolean man;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", man=" + man +
                '}';
    }
}
