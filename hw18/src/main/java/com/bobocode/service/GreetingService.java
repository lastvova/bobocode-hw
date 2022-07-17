package com.bobocode.service;

import com.bobocode.annotation.Trimmed;
import org.springframework.stereotype.Service;

@Service
@Trimmed
public class GreetingService {

    public String greetings(String name){
        return "   Hello " + name + "!   ";
    }
}
