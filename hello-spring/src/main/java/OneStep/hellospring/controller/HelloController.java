package OneStep.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello~!");
        return "hello";
        // MVC view를 리턴? => resources/templates/hello.html 을 렌더링 해라. command+클릭하면 해당 파일로 넘어감
        // ViewResolver가 hello.html 템플릿 찾을거임
    }

}
