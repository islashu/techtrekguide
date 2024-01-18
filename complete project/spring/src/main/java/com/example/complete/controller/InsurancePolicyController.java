package com.example.complete.controller;

import com.example.complete.model.InsurancePolicy;
import com.example.complete.model.User;
import com.example.complete.service.InsurancePolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/policy")
public class InsurancePolicyController {

    @Autowired
    private InsurancePolicyService insurancePolicyService;

    @GetMapping("/getPolicy/{policyId}")
    public ResponseEntity<InsurancePolicy> getPolicy(@PathVariable("policyId") Long policyId) {
        InsurancePolicy policy = insurancePolicyService.getPolicyById(policyId);
        System.out.println("deserialisation "+ policy.toString());
        return ResponseEntity.ok(policy);
    }

    @PostMapping("/createPolicy")
    public ResponseEntity<Void> createPolicy(@RequestBody InsurancePolicy insurancePolicy) {
        insurancePolicyService.createPolicy(insurancePolicy);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/updatePolicy/{userId}/{policyId}")
    public ResponseEntity<Void> updatePolicyWithUser(@PathVariable Long userId, @PathVariable Long policyId) {
        insurancePolicyService.updatePolicyWithUser(userId, policyId);
        return ResponseEntity.ok().build();
    }
}
