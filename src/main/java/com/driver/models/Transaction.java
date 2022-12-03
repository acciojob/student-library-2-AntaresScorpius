package com.driver.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String transactionId = UUID.randomUUID().toString(); // externalId

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    @JsonIgnoreProperties("books")
    private Card card;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    @JsonIgnoreProperties("transactions")
    private Book book;

    private int fineAmount;

    @Column(columnDefinition = "TINYINT(1)")
    private boolean isIssueOperation;

    @Enumerated(value = EnumType.STRING)
    private TransactionStatus transactionStatus;

    @CreationTimestamp
    private Date transactionDate;
}

