package com.example.complete.service;

import com.example.complete.model.InsuranceClaim;
import com.example.complete.model.InsurancePolicy;

public interface InsuranceClaimService {

    public InsuranceClaim getClaimById(Long id);

    public InsuranceClaim saveClaim(InsuranceClaim claim);

    public void updateClaimWithPolicy(Long claimId, Long policyId);
}
