package com.rogers.account.management.entities;

import com.rogers.account.management.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GenericGenerator(name = "account_id", strategy = "com.rogers.account.management.configs.UniqueIdGenerator")
    @GeneratedValue(generator = "account_id")
    @Column(name = "account_id", length = 6)
    private String accountId;
    @Column(nullable = false, length = 20)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column
    private int age;
    @Column(nullable = false)
    private AccountStatus status;
    @Column(nullable = false)
    private String securityPIN;
    @OneToMany(targetEntity = Address.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    List<Address> addresses;
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

}
