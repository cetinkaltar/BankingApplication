package bankingapplication.cetin.service.impl;

import bankingapplication.cetin.dao.AccountDao;
import bankingapplication.cetin.entity.Account;
import bankingapplication.cetin.entity.Transaction;
import bankingapplication.cetin.exception.TransactionException;
import bankingapplication.cetin.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransferServiceImpl implements TransferService {

    @Autowired
    AccountDao accountDao;

    public Account findById(Long id) {
       return accountDao.findById(id);
    }

    public List<Account> getAll() {
        return accountDao.getAll();
    }

    // MANDATORY: Transaction must be created before.
    @Transactional(propagation = Propagation.MANDATORY)
    public void addAmount(Long id, double amount) throws TransactionException {
        Account account = accountDao.findById(id);
        if (account == null) {
            throw new TransactionException("Account not found " + id);
        }
        account.addMoney(amount);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    protected void checkBalance(Long id, double amount) throws TransactionException {
        Account account = accountDao.findById(id);
        if (account == null) {
            throw new TransactionException("Account not found " + id);
        }
        if (account.getBalance() < amount) {
            throw new TransactionException(
                    "The money in the account '" + id + "' is not enough (" + account.getBalance() + ")");
        }
    }

    @Transactional(propagation = Propagation.MANDATORY)
    protected void checkAccounts(Long sender, Long receiver) throws TransactionException {
        if (sender == receiver) {
            throw new TransactionException(
                    "The account numbers are same '" + sender + ")");
        }
    }

    @Transactional(propagation = Propagation.MANDATORY)
    protected void checkAmount(double amount) throws TransactionException {
        if (amount < 0) {
            throw new TransactionException(
                    "The amount have to be positive");
        }
    }

    // Do not catch BankTransactionException in this method.
    //Checks all possible exceptions before
    @Transactional(propagation = Propagation.REQUIRES_NEW,
            rollbackFor = TransactionException.class)
    public void sendMoney(Long fromAccountId, Long toAccountId, double amount) throws TransactionException {
        checkAccounts(fromAccountId, toAccountId);
        checkAmount(amount);
        checkBalance(fromAccountId, amount);
        addAmount(toAccountId, amount);
        addAmount(fromAccountId, -amount);
        addTransactions(fromAccountId,toAccountId,amount);
    }

    //saves every records on the related account
    private void addTransactions(Long fromAccountId, Long toAccountId, double amount){
        Transaction transaction1 = new Transaction("out",toAccountId,fromAccountId,amount);
        Account account1 = accountDao.findById(fromAccountId);
        if (account1 != null) {
            account1.addTransaction(transaction1);
        }
        Transaction transaction2 = new Transaction("in",toAccountId,fromAccountId,amount);
        Account account2 = accountDao.findById(toAccountId);
        if (account2 != null) {
            account2.addTransaction(transaction2);
        }
    }

}
