package hello.core;

import lombok.Getter;
import lombok.Setter;

// Lombok 이 있으면 getter, setter 를 자동으로 만들어줌, 생성자 기능도 있음
@Getter
@Setter
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("asdfas");

        String name = helloLombok.getName();
        System.out.println("name = " + name);
    }
}
