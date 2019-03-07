package bankingapplication.cetin.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Account")
@Data
public class Account {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "Full_Name", length = 128, nullable = false)
    private String fullName;

    @Column(name = "Balance", nullable = false)
    private double balance;

    @OneToMany(
            cascade = CascadeType.ALL
    )
    private List<Transaction> transactions = new ArrayList<>();

    public void addMoney(double amount) {
        balance = balance + amount;
    }

    public Account(String fullName, double balance) {
        this.fullName = fullName;
        this.balance = balance;
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public Account() {
    }

    public Account(Long id, String fullName, double balance) {
        this.id = id;
        this.fullName = fullName;
        this.balance = balance;
    }
}
