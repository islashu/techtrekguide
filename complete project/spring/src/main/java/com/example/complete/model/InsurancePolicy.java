package com.example.complete.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="insuranceId")
public class InsurancePolicy {

    @Id
    @GeneratedValue
    @Column(name = "InsuranceID")
    private int insuranceId;
    @Column(name = "InsuranceType", length = 100)
    private String insuranceType;
    @Column(name = "PolicyStartDate", length = 255)
    private String policyStartDate;
    @Column(name = "PolicyEndDate", length = 255)
    private String policyEndDate;
    @Column(name = "PolicyTerm", length = 1)
    private String policyTerm;
    @Column(name = "ClaimLimit")
    private Float claimLimit;

    @Column(name = "RemainingClaimLimit")
    private Float remainingClaimLimit;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "EmployeeID")
    private User user;

    @OneToMany(mappedBy = "insurancePolicy", cascade = CascadeType.ALL)
    private List<InsuranceClaim> insuranceClaims;

    @Override
    public String toString() {
        return "InsurancePolicy{" +
                "insuranceId=" + insuranceId +
                ", insuranceType='" + insuranceType + '\'' +
                ", policyStartDate='" + policyStartDate + '\'' +
                ", policyEndDate='" + policyEndDate + '\'' +
                ", policyTerm='" + policyTerm + '\'' +
                ", claimLimit=" + claimLimit +
                ", remainingClaimLimit=" + remainingClaimLimit +
                ", user=" + user +
                ", insuranceClaims=" + insuranceClaims +
                '}';
    }
}
