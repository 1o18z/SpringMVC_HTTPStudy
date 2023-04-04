package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
// 하나하나 @ResponseBody 달아주기 번거롭다! 하면 @Controller와 @ResponseBody 모두 포함하고 있는 @RestController 사용하자!
public class ResponseBodyController {

    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException{
        response.getWriter().write("ok");
    } // HttpServletResponse 객체 통해 HTTP 메시지 바디에 직접 ok 응답 메시지 전달

    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2() throws IOException{
        return new ResponseEntity<>("ok", HttpStatus.OK);
    } // HttpEntity는 HTTP 메시지의 헤더, 바디 정보를 가지고 있다. ResponseEntity는 더해서 HTTP 응답 코드 설정 가능

    @ResponseBody
    @GetMapping("/response-body-string-v2")
    public String responseBodyV3(){
        return "ok";
    } // ResponseBody를 사용해 view를 사용하지 않고 HTTP 메시지 컨버터를 통해 HTTP 메시지 직접 입력 가능

    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1(){
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);
        return new ResponseEntity<>(helloData, HttpStatus.OK);
    } // HTTP 메시지 컨버터를 통해 JSON 형식으로 변환되어서 반환

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2(){
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);
        return helloData;
    } // ResponseEntity는 HTTP 응답코드 설정할 수 있지만, @ResponseBody를 사용하면 이런 것 설정 까다로움
    // 따라서 @ResponseStatus를 사용해 응답 코드를 설정
    // 하지만 어노테이션을 사용하면 동적으로 변경 불가 -> 동적 변경 필요하면 ResponseEntity 쓰자~




}
