package OneStep.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello~!");
        return "hello";
        // MVC view를 리턴? => resources/templates/hello.html 을 렌더링 해라. command+클릭하면 해당 파일로 넘어감
        // ViewResolver가 hello.html 템플릿 찾을거임
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        // @RequestParam(value = "name", required = true) true 가 디폴트라서 이 값이 없으면 에러
        // localhost:8080/hello-mvc
        // There was an unexpected error (type=Bad Request, status=400).
        // localhost:8080/hello-mvc?name=spring!!!! 로 파라미터 입력 또는
        // required = false 로 하면 에러X
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("string") String str, Model model){
        // http://localhost:8080/hello-string?string=test
        return "HELLO " + str;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello h = new Hello();
        h.setName(name);
        return h;
        // http://localhost:8080/hello-api?name=apitest
        // json 형태로 객체 내용 보여줌 (과거엔 xml 포맷을 사용했으나 요즘엔 기본으로 json)
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
