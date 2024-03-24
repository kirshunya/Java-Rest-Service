package com.rateservice.controller;


import com.rateservice.dao.Credit;
import com.rateservice.service.CreditService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/credits")
public class CreditController {
    private final CreditService creditService;

    @GetMapping("/all")
    public List<Credit> getAllCredits() { return creditService.getAllCredits(); }

    @PostMapping("/save_credit")
    public Credit saveCredit(@RequestBody Credit credit) { return creditService.saveCredit(credit);}

    @PutMapping("/{id}")
    Credit updateCredit(@PathVariable Long id, @RequestBody Credit newCredit) { return creditService.updateCredit(id, newCredit);}

    @DeleteMapping("/delete/{id}")
    void deleteCredit(@PathVariable Long id) { creditService.deleteCredit(id); }

    @GetMapping("/find/{id}")
    Credit getCreditById(@PathVariable Long id) { return creditService.findCreditById(id); }

    @PostMapping("/add_user_by_id/{crId}")
    Credit addUserById(@PathVariable Long crId, @RequestParam Long usId) { return creditService.addCreditToUserById(crId, usId); }

    @PostMapping("/add_user/{usId}")
    Credit addUser(@PathVariable Long usId, @RequestBody Credit credit) { return creditService.addCreditToUser(credit, usId);}

    @DeleteMapping("/pay_credit/{crId}")
    String payCredit(@PathVariable Long crId, @RequestParam Long cardId) { return creditService.creditPay(crId, cardId);}
}
