package example.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController{

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "동현의 코딩여행");
        return "hello";
        // templates/hello.html에 자동으로 매핑돼서 model을 전달함
    }

    @GetMapping("hello-template")
    public String hello_template(@RequestParam("name") String name,  Model model) {
        // /hello-template?name= 형식으로 받으면 name에 파라미터가 저장됩니다.
        model.addAttribute("data", name);
        return "hello-template";
        // templates/hello-template.html에 자동으로 매핑돼서 model을 전달함
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello hello_api(@RequestParam("age") String age){
        Hello hello = new Hello();
        try {
            hello.setAge(Integer.parseInt(age));
        } catch (Exception e) {
            hello.setAge(22);
        }
        return hello;
        
    }
    

    static class Hello {
        private String name;
        private int age;
        public Hello(){
            this.name = "동현의 코딩여행";
        }

        public void setAge(int age){
            this.age = age;
        }

        public int getAge(){
            return this.age;
        }

        public void setName(String name){
            this.name = name;
        }
        
        public String getName(){
            return this.name;
        }
    }
}
