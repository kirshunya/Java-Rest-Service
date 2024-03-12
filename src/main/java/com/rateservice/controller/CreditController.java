package com.rateservice.controller;


import com.rateservice.dao.Credit;
import com.rateservice.service.CreditService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/credits")
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
}
