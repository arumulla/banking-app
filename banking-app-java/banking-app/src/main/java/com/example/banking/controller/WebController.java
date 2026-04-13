package com.example.banking.controller;

import com.example.banking.service.BankingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    private final BankingService bankingService;

    public WebController(BankingService bankingService) {
        this.bankingService = bankingService;
    }

    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("customers", bankingService.getAllCustomers());
        model.addAttribute("accounts", bankingService.getAllAccounts());
        return "index";
    }
}
