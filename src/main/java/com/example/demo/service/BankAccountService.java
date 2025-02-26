package com.example.demo.service;

import com.example.demo.model.BankAccount;
import com.example.demo.model.Product;
import com.example.demo.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

@Service
public class BankAccountService {
//    @Autowired
//    private BankAccountRepository bankAccountRepository;
//
//    public BankAccount save(BankAccount bankAccount) {
//        return bankAccountRepository.save(bankAccount);
//    }
//
//    public void transfer(long from, long to, double amount, boolean status) {
//        BankAccount bankAccountA =  bankAccountRepository.findById(from).get();
//        BankAccount bankAccountB =  bankAccountRepository.findById(to).get();
//
//        int amountA = bankAccountA.getBalance();
//        int amountB = bankAccountB.getBalance();
//        if (status) {
//            amountA += amount;
//            amountB -= amount;
//        }else{
//            amountA -= amount;
//            amountB += amount;
//        }
//        bankAccountA.setBalance(amountA);
//        bankAccountB.setBalance(amountB);
//
//        bankAccountRepository.save(bankAccountA);
//        bankAccountRepository.save(bankAccountB);
//    }
}
