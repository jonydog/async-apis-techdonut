package com.techdonut.synchapi.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
class HelloController{
    val client = RestTemplate()
    @GetMapping("/hello")
    fun hello(@RequestParam("name")  name: String):String{
        val response = client.getForEntity("http://localhost:9090/hello",String::class.java)
        return response.body + " "+ name
    }
}