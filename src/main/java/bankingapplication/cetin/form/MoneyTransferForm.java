package bankingapplication.cetin.form;

import lombok.Data;

@Data
public class MoneyTransferForm {
    private Long fromAccountId;
    private Long toAccountId;
    private Double amount;

    public MoneyTransferForm(Long fromAccountId, Long toAccountId, Double amount) {
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
    }
}
