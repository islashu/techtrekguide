-- CreateTable
CREATE TABLE "User" (
    "employeeId" SERIAL NOT NULL,
    "password" VARCHAR(20) NOT NULL,
    "firstName" VARCHAR(50) NOT NULL,
    "lastName" VARCHAR(50) NOT NULL,
    "age" INTEGER NOT NULL,
    "createdAt" TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "updatedAt" TIMESTAMP(3) NOT NULL,
    "version" INTEGER NOT NULL DEFAULT 1,

    CONSTRAINT "User_pkey" PRIMARY KEY ("employeeId")
);

-- CreateTable
CREATE TABLE "InsurancePolicies" (
    "insuranceId" SERIAL NOT NULL,
    "createdAt" TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "updatedAt" TIMESTAMP(3) NOT NULL,
    "version" INTEGER NOT NULL DEFAULT 1,
    "InsuaranceType" VARCHAR(100) NOT NULL,
    "PolicyStartDate" VARCHAR(255) NOT NULL,
    "PolicyTerm" VARCHAR(100) NOT NULL,
    "PolicyEndDate" VARCHAR(255) NOT NULL,
    "ClaimLimit" DOUBLE PRECISION NOT NULL,
    "RemainingClaimLimit" DOUBLE PRECISION NOT NULL,
    "UserId" INTEGER NOT NULL,

    CONSTRAINT "InsurancePolicies_pkey" PRIMARY KEY ("insuranceId")
);

-- CreateTable
CREATE TABLE "InsuranceClaims" (
    "claimId" SERIAL NOT NULL,
    "FirstName" VARCHAR(50) NOT NULL,
    "LastName" VARCHAR(50) NOT NULL,
    "ExpenseDate" VARCHAR(255) NOT NULL,
    "Amount" DOUBLE PRECISION NOT NULL,
    "Purpose" VARCHAR(255) NOT NULL,
    "FollowUp" INTEGER NOT NULL,
    "PreviousClaimID" INTEGER NOT NULL,
    "Status" VARCHAR(20) NOT NULL,
    "LastEditClaimDate" VARCHAR(255) NOT NULL,
    "createdAt" TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "updatedAt" TIMESTAMP(3) NOT NULL,
    "version" INTEGER NOT NULL DEFAULT 1,
    "policyId" INTEGER NOT NULL,

    CONSTRAINT "InsuranceClaims_pkey" PRIMARY KEY ("claimId")
);

-- AddForeignKey
ALTER TABLE "InsurancePolicies" ADD CONSTRAINT "InsurancePolicies_UserId_fkey" FOREIGN KEY ("UserId") REFERENCES "User"("employeeId") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "InsuranceClaims" ADD CONSTRAINT "InsuranceClaims_policyId_fkey" FOREIGN KEY ("policyId") REFERENCES "InsurancePolicies"("insuranceId") ON DELETE RESTRICT ON UPDATE CASCADE;
