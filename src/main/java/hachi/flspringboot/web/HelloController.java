package hachi.flspringboot.web;

import hachi.flspringboot.web.dto.HelloRequestDto;
import hachi.flspringboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam(value = "name", required = true) String name, @RequestParam(value = "amount", required = false, defaultValue = "100") int amount) {
        return new HelloResponseDto(name, amount);
    }

    @GetMapping("/hello/dto/simple")
    public HelloResponseDto helloDtoSimple(@RequestParam("name") String name, @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }

    @GetMapping("/hello/dto/model")
    public HelloResponseDto helloDtoModel(@ModelAttribute HelloRequestDto helloRequestDto) {
        return new HelloResponseDto(helloRequestDto.getName(), helloRequestDto.getAmount());
    }

    @GetMapping("/hello/dto/model/wrapper")
    public HelloResponseDto helloDtoModelWrapper(@ModelAttribute HelloRequestDto helloRequestDto) {
        return helloRequestDto.toResponseDto();
    }
}