package com.example.complete.repository;

import com.example.complete.model.InsuranceClaim;
import com.example.complete.model.InsurancePolicy;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsurancePolicyRepository extends JpaRepository<InsurancePolicy, Long> {

    // JPQL
    @Query(value = "select ip from InsurancePolicy ip where ip.insuranceType= :insuranceType")
    public InsurancePolicy getInsurancePolicyByPolicyNumber(@Param("insuranceType") String insuranceType);

    // Native query
    @Query(value = "select * from InsurancePolicy where startDate = :startDate", nativeQuery = true)
    public List<InsurancePolicy> getInsurancePoliciesByStartDate(String startDate);

    // JPQL
    @Query(value = "select ip from InsurancePolicy ip ORDER BY ip.insuranceId desc")
    public List<InsurancePolicy> getPage(Pageable pageable);
    // In service call this and add the following getPage(PageRequest.of(1,10)), this is the Pageable object
}
