package hello.springmvc.basic;

import lombok.Data;

@Data // @Getter, @Setter 등을 자동으로 만들어 줌
public class HelloData {

    private String username;
    private int age;
}
