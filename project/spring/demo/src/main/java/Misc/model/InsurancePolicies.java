package Misc.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Data
@Table(name = "InsurancePolicies")
public class InsurancePolicies {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "policyName")
    private String policyName;

    @Column(name = "policyType")
    private String policyType;

    @Column(name = "policyDescription")
    private String policyDescription;

    @ManyToOne(fetch = FetchType.LAZY )
    // Name the foreign key column
    @JoinColumn(name = "user_id")
    private oldUser user;

    public InsurancePolicies() {
        this.policyName = "AIC";
        this.policyType = "LIFE";
        this.policyDescription = "Life Insurance";
    }

}
