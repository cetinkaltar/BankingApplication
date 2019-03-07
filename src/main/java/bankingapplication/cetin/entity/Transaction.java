package bankingapplication.cetin.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Transaction")
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    @CreationTimestamp
    private LocalDateTime transferTime;

    @Column(name = "Amount", nullable = false)
    private double amount;

    @Column(name = "Way")
    private String way;

    @Column(name = "Receiver")
    private Long receiver;

    @Column(name = "Sender")
    private Long sender;

    public Transaction(){}

    public Transaction(String way, Long receiver, Long sender,double amount){
        this.way=way;
        this.receiver=receiver;
        this.sender=sender;
        this.amount=amount;
    }

}
