package com.example.complete.service;

import com.example.complete.model.InsurancePolicy;
import org.springframework.stereotype.Service;

public interface InsurancePolicyService {

    public void createPolicy(InsurancePolicy policy);
    public InsurancePolicy getPolicyById(Long id);
    public void updatePolicyWithUser(Long userId, Long policyId);
}
