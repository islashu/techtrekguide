package com.example.complete.service;

import com.example.complete.model.InsuranceClaim;
import com.example.complete.model.InsurancePolicy;
import com.example.complete.repository.InsuranceClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsuranceClaimServiceImpl implements InsuranceClaimService {


    @Autowired
    InsuranceClaimRepository insuranceClaimRepository;

    @Autowired
    InsurancePolicyService insurancePolicyService;

    @Override
    public InsuranceClaim getClaimById(Long id) {
        return insuranceClaimRepository.findById(id).get();
    }

    @Override
    public InsuranceClaim saveClaim(InsuranceClaim claim) {
        System.out.println(claim.toString());
        return insuranceClaimRepository.save(claim);
    }

    @Override
    public void updateClaimWithPolicy(Long claimId, Long policyId) {
        InsuranceClaim claim = insuranceClaimRepository.findById(claimId).orElse(null);
        if(claim != null) {
            InsurancePolicy policy = insurancePolicyService.getPolicyById(policyId);
            if(policy != null) {
                claim.setInsurancePolicy(policy);
            }
        }
        insuranceClaimRepository.save(claim);
    }

}
