package com.practice.spring.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BillController {
    @GetMapping("/generate-pdf")
    public void billGeneration() {

    }

}
