package com.vn.tpbank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.vn.tpbank.entity.BankAccount;
import com.vn.tpbank.entity.Customer;
import com.vn.tpbank.entity.User;
import com.vn.tpbank.repository.BankAccountRepository;
import com.vn.tpbank.repository.CustomerRepository;
import com.vn.tpbank.repository.TransactionRepository;
import com.vn.tpbank.repository.UserRepository;
import com.vn.tpbank.request.RegisterBankAccountRequest;
import com.vn.tpbank.request.UpdateInformationRequest;

@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BankAccountRepository bankAccountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public String login(String username, String pass) {
        User user = userRepository.findByUserNameAndUserPass(username, pass);
        if (user != null) {
            return "Login successfully. Role: " + user.getRole();
        }
        return "Wrong username or password!";
    }

    @Override
    public String RegisterCreateBankAccount(RegisterBankAccountRequest request) {
        Customer customer = customerRepository.findById(request.getCustomerId()).get();
        if (customer == null) {
            return "Customer not found";
        }

        BankAccount account = new BankAccount();
        account.setBankAccountId(0l);
        account.setBankName(request.getBankName());
        account.setBalance(0l);
        account.setLockStatus("active");
        account.setCustomer(customer);

        bankAccountRepository.save(account);
        return "Create bank account successfully";
    }

    @Override
    public String changePassword(String userName, String userPass, String newUserPass) {
        User user = userRepository.findByUserNameAndUserPass(userName, userPass);
        if (user == null) {
            return "Wrong username or password!";
        }

        user.setUserPass(newUserPass);
        userRepository.save(user);
        return "Change password successfully.";
    }

    @Override
    public List<BankAccount> getAllAccount(Long customerId) {
        return bankAccountRepository.findByCustomerId(customerId);
    }

    @Override
    public String deposit(Long accountNumber, Long amount) {
        BankAccount account = bankAccountRepository.findById(accountNumber).get();
        if (account == null) {
            return "Account not found";
        }

        account.setBalance(account.getBalance() + amount);
        bankAccountRepository.save(account);
        return "Deposit successfully";
    }

    @Override
    public String transfer(Long accountNumber, Long accountNumber2, Long amount) {
        BankAccount account = bankAccountRepository.findById(accountNumber).get();
        if (account == null) {
            return "Account not found";
        }
        BankAccount account2 = bankAccountRepository.findById(accountNumber2).get();
        if (account2 == null) {
            return "Account not found";
        }
        if (account.getBalance() < amount) {
            return "Not enough money";
        }
        account.setBalance(account.getBalance() - amount);
        account2.setBalance(account2.getBalance() + amount);
        bankAccountRepository.save(account);
        return "Transfer successfully";

    }

    @Override
    public String withdraw(Long accountNumber, Long amount) {
        BankAccount account = bankAccountRepository.findById(accountNumber).get();
        if (account == null) {
            return "Account not found";
        }
        if (account.getBalance() < amount) {
            return "Not enough money";
        }
        account.setBalance(account.getBalance() - amount);
        bankAccountRepository.save(account);
        return "Withdraw successfully";
    }

    @Override
    public String updateInformation(UpdateInformationRequest request) {
        BankAccount account = bankAccountRepository.findById(request.getBankAccountId()).get();
        if (account == null) {
            return "Account not found";
        }
        account.setLockStatus(request.getLockStatus());

        Customer customer = customerRepository.findById(account.getCustomer().getCustomerId()).get();
        if (customer == null) {
            return "Customer not found";
        }
        customer.setCustomerName(request.getCustomerName());
        customer.setCustomerAddress(request.getCustomerAddress());
        customer.setCustomerPhone(request.getCustomerPhone());
        customer.setCustomerEmail(request.getCustomerEmail());
        customer.setCustomerDob(request.getCustomerDob());

        bankAccountRepository.save(account);
        customerRepository.save(customer);

        return "Update information successfully";
    }
}