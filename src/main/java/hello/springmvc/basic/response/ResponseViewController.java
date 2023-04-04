package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello!!!");

        return mav;
    }

    @ResponseBody // (1) 이걸 넣어주면
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello!!!");
        return "response/hello"; //  @ResponseBody 없으면 이게 뷰의 논리적 이름이 됨 -> 뷰 리졸버가 실행돼서 뷰를 찾고 렌더링! -> templates/response/hello.html 실행
        // (2) 위 문자가 그대로 Http 응답 코드(바디)로 나가 버림!
    }

    @RequestMapping("/response/hello")
    public void responseViewV3(Model model) {
        model.addAttribute("data", "hello!!!");
    }
    /*
    void로 반환하는 경우는 @Controller를 사용하고,
    HttpServletResponse, OutputStream(Writer)같은 HTTP 메시지 바디를 처리하는 파라미터가 없으면
    요청 URL(/response/hello) 을 참고해서 논리 뷰 이름으로 사용
    -> templates/response/hello.html 실행
    * 하지만 이 방식은 명시성 너무 떨어지고 이렇게 딱 맞는 경우도 많이 없다! 권장 X
     */


}
