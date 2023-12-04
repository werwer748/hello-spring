package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "spring!!");
        /*
        * 컨트롤러에서 리턴 값으로 문자를 반환하면 뷰 리졸버( `viewResolver` )가 화면을 찾아서 처리한다.
        * 스프링 부트 템플릿엔진 기본 viewName 매핑
        * `resources:templates/` +{ViewName}+ `.html`
        * MVC: Model, View, Controller 패턴임.
        */
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // "hello spring"
    }

    @GetMapping("hello-api")
    @ResponseBody
    // HttpMessageConverter가 판단해서 데이터를 변환해줌.
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello(); // shift + cmd + enter 이쯤 되면 뒤까지 자동완성 될꺼같을 때 입력시 자동완성 됨 ㅋㅋ
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;
        // cmd + n getter, setter 로 자동 생성
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
