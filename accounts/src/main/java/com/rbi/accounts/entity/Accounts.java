package com.rbi.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter@Setter@ToString@AllArgsConstructor@NoArgsConstructor
public class Accounts extends BaseEntity {

    @Column(name = "customer_id")
    private long customerId;

    @Id
    @Column(name = "account_number")
    private long accountNumber;

    @Column(name = "branch_address")
    private String branchAddress;

    @Column(name = "account_type")
    private String accountType;


}
