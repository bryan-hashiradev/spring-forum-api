package br.com.hashiradev.forum

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/hello"])
class HelloSpring {

    @GetMapping
    fun helloSpring(): String {
        return "Hello Spring! devtools test works!"
    }
}