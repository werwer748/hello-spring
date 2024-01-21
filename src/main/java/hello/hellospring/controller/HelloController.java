package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String Hello(Model model) {
        model.addAttribute("data", "spring!!");
        // resources > templates > hello 라는 파일이름을 찾는다.
        // 뷰리졸버가 화면을 찾아서 처리하는 것.
        return "hello";
    }

    @GetMapping("hello-mvc")
    /*
    * Params 사용 시 기본적으로 required는 true
    * 설정을 바꾸고 싶을 때
    * @RequestParam(value = "name", required = false)*/
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    /*
    * `@ResponseBody` 를 사용
    * HTTP의 BODY에 문자 내용을 직접 반환
    * `viewResolver` 대신에 `HttpMessageConverter` 가 동작
    * 기본 문자처리: `StringHttpMessageConverter`
    * 기본 객체처리: `MappingJackson2HttpMessageConverter`
    * byte 처리 등등 기타 여러 HttpMessageConverter가 기본으로 등록되어 있음
    */
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        //? 자바 빈 규약
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
