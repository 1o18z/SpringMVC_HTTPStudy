package hello.springmvc.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 문자를 반환하면 그대로 반환이 됨 (맨 아랫줄에 "ok") 테스트 할 때 간단하게 쓰기 굿
public class LogTestController {

    private final Logger log = LoggerFactory.getLogger(getClass()); // getClass 말고 LogTestController.class 도 가능

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        System.out.println("name = " + name);

        log.trace("trace log = {}", name);
        log.debug("debug log = {}", name);
        log.info("info log = {}", name);
        log.warn("warn log = {}", name);
        log.error("error log = {}", name);

        return "ok";
    }

}
