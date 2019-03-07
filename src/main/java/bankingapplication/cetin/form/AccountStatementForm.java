package bankingapplication.cetin.form;

import lombok.Data;

@Data
public class AccountStatementForm {
    private Long accountId;

    public AccountStatementForm(Long accountId){
        this.accountId=accountId;
    }
    public AccountStatementForm(){}
}
