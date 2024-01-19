package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String Hello(Model model) {
        model.addAttribute("data", "spring!!");
        // resources > templates > hello 라는 파일이름을 찾는다.
        // 뷰리졸버가 화면을 찾아서 처리하는 것.
        return "hello";
    }
}
