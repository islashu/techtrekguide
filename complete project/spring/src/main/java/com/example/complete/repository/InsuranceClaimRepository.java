package com.example.complete.repository;

import com.example.complete.model.InsuranceClaim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceClaimRepository extends JpaRepository<InsuranceClaim, Long> {
}
