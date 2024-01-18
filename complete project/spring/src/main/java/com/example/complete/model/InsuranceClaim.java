package com.example.complete.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="claimId")
public class InsuranceClaim {

    @Id
    @GeneratedValue
    private Integer claimId;

    @Column(name = "FirstName", length = 50)
    private String firstName;

    @Column(name = "LastName", length = 50)
    private String lastName;

    @Column(name = "ExpenseDate", length = 255)
    private String expenseDate;

    @Column(name = "Amount" )
    private Float amount;

    @Column(name = "Purpose", length = 255)
    private String purpose;

    @Column(name = "FollowUp", length = 1)
    private int followUp;

    @Column(name = "PreviousClaimId")
    private Integer previousClaimId;

    @Column(name = "Status", length = 20)
    private String status;

    @Column(name = "LastEditedClaimDate", length = 255)
    private String lastEditedClaimDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    InsurancePolicy insurancePolicy;

}
