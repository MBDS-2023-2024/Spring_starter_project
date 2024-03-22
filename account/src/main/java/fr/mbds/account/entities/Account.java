package fr.mbds.account.entities;


import fr.mbds.account.enums.CurrencyType;
import fr.mbds.account.models.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Double balance;
    private CurrencyType currency;
    private Long customerId;
    private LocalDate dateCreated;
    @Transient
    private Customer customer;
}