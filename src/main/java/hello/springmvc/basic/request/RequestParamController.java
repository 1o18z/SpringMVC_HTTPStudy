package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller // (1)컨트롤러면서
public class RequestParamController {
    /**
     * 반환 타입이 없으면서 이렇게 응답에 값을 직접 집어넣으면, view 조회X
     */
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse
            response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);
        response.getWriter().write("ok");
    }

    @ResponseBody // (4) @ResponseBody 추가하면 ok라는 문자를 그대로 http 응답 메시지에 바로 넣어서 반환을 해버림 !
    @RequestMapping("/request-param-v2")
    public String requestParamV2( //(2) String이면
                                  @RequestParam("username") String memberName,
                                  @RequestParam("age") int memberAge) {

        log.info("userName={}, age={}", memberName, memberAge);
        return "ok"; // (3) ok라는 뷰를 찾게 됨!
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username, // 변수명과 파라미터명이 같으면 생략 가능!
            @RequestParam int age ) {

        log.info("userName={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age){ // 요청 파라미터 이름과 동일하면 이렇게도 가능 !
        log.info("userName={}, age={}", username, age);
        return "ok";
    } // String, int, Integer 등의 단순 타입이면 이렇게 @RequestParam도 생략 가능!
      // 하지만 명확하게 요청 파라미터에서 데이터를 읽는다는 것을 알 수 있으니 완전 익숙하지 않다면 v3처럼 쓰는 걸 권장~

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestRequired(
            @RequestParam(required = true) String username, // username을 required=true로 지정해줘서 필수값이 됨 ! (username 넘겨주지 않으면 405 Bad Request)
            @RequestParam(required = false) int age ) { // 반대로 username만 있고 age만 있으면 500에러! (age 값이 없으면 null이 들어가야 하는데 int는 null이 불가 -> Integer로 바꿔줘야 함(Integer은 객체형이라 null 가능))

        log.info("userName={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age ) {
            // 디폴트값 설정 가능 ! (사실상 디폴트값을 주면 required가 있는 의미가...)
        log.info("userName={}, age={}", username, age);
        return "ok";
    } // defaultValue는 빈 문자가 들어와도 설정한 기본값 적용됨! (/request-param-default?username=)

}

