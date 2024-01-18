package com.example.complete.service;

import com.example.complete.model.InsurancePolicy;
import com.example.complete.repository.InsurancePolicyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class InsurancePolicyServiceImpl implements InsurancePolicyService {

    Logger logger = LoggerFactory.getLogger(InsurancePolicyServiceImpl.class);

    @Autowired
    private UserService userService;

    @Autowired
    private InsurancePolicyRepository insurancePolicyRepository;

    @Override
    public void createPolicy(InsurancePolicy policy) {
        insurancePolicyRepository.save(policy);
    }

    @Override
    public InsurancePolicy getPolicyById(Long id) {
        logger.info("Inside getPolicyById method of InsurancePolicyServiceImpl");
        InsurancePolicy insurancePolicy = insurancePolicyRepository.findById(id).get();
        return insurancePolicy;
    }

    @Override
    public void updatePolicyWithUser(Long userId, Long policyId) {
        InsurancePolicy policy = insurancePolicyRepository.findById(policyId).get();
        policy.setUser(userService.getUserById(userId));
        insurancePolicyRepository.save(policy);
    }

}
