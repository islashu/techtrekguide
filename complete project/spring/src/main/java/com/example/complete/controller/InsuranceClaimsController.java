package com.example.complete.controller;

import com.example.complete.model.InsuranceClaim;
import com.example.complete.model.InsurancePolicy;
import com.example.complete.service.InsuranceClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/claim")
public class InsuranceClaimsController {

    @Autowired
    InsuranceClaimService insuranceClaimService;

    @GetMapping("/getClaimById/{claimId}")
    public ResponseEntity<InsuranceClaim> getPolicy(@PathVariable("claimId") Long claimId) {
        return ResponseEntity.ok(insuranceClaimService.getClaimById(claimId));
    }

    @PostMapping("/createClaim")
    public ResponseEntity<Void> createPolicy(@RequestBody InsuranceClaim insuranceClaim) {
        insuranceClaimService.saveClaim(insuranceClaim);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/updateClaimWithPolicy/{policyId}/{claimId}")
    public ResponseEntity<Void> updateClaimWithPolicy(@PathVariable Long policyId, @PathVariable Long claimId) {
        insuranceClaimService.updateClaimWithPolicy(claimId, policyId);
        return ResponseEntity.ok().build();
    }
}
