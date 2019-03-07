package bankingapplication.cetin.controller;

import bankingapplication.cetin.entity.Account;
import bankingapplication.cetin.exception.TransactionException;
import bankingapplication.cetin.form.AccountStatementForm;
import bankingapplication.cetin.form.MoneyTransferForm;
import bankingapplication.cetin.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    @Autowired
    TransferService transferService;

    @GetMapping("/")
    public String showAccounts(Model model) {
        model.addAttribute("accounts", transferService.getAll());
        return "home_page";
    }

    @GetMapping("/transfer")
    public String transferMoney(Model model) {
        MoneyTransferForm form = new MoneyTransferForm(1L, 2L, 700d);
        model.addAttribute("moneyTransferForm", form);
        return "money_transfer";
    }

    @PostMapping("/transfer")
    public String transferMoney(Model model, MoneyTransferForm moneyTransferForm) {
        try {
            transferService.sendMoney(moneyTransferForm.getFromAccountId(),
                    moneyTransferForm.getToAccountId(),
                    moneyTransferForm.getAmount());
        } catch (TransactionException e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "/money_transfer";
        }
        return "redirect:/";
    }

    @GetMapping("/statement")
    public String viewStatement(Model model) {
        AccountStatementForm form = new AccountStatementForm();
        model.addAttribute("accountStatementForm", form);
        return "account_statement";
    }

    @PostMapping("/statement")
    public String viewStatement(Model model, AccountStatementForm form) {
        Account account = transferService.findById(form.getAccountId());
        model.addAttribute("account", account);
        return "account_statement";
    }

}
