package bankingapplication.cetin.service;

import bankingapplication.cetin.entity.Account;
import bankingapplication.cetin.exception.TransactionException;

import java.util.List;

public interface TransferService {
    void sendMoney(Long fromAccountId, Long toAccountId, double amount)throws TransactionException;
    Account findById(Long id);
    List<Account> getAll();
}
