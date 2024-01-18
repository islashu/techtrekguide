-- CreateTable
CREATE TABLE "User" (
    "employeeId" SERIAL NOT NULL,
    "Password" VARCHAR(20) NOT NULL,
    "FirstName" VARCHAR(50) NOT NULL,
    "LastName" VARCHAR(50) NOT NULL,
    "age" INTEGER NOT NULL,

    CONSTRAINT "User_pkey" PRIMARY KEY ("employeeId")
);

-- CreateTable
CREATE TABLE "InsurancePolicy" (
    "insuranceId" SERIAL NOT NULL,
    "InsuranceType" VARCHAR(100) NOT NULL,
    "PolicyStartDate" TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "PolicyTerm" VARCHAR(100) NOT NULL,
    "PolicyEndDate" TIMESTAMP(3) NOT NULL,
    "ClaimLimit" DOUBLE PRECISION NOT NULL DEFAULT 0,
    "RemainingClaimLimit" DOUBLE PRECISION NOT NULL DEFAULT 0,
    "insuranceUserId" INTEGER NOT NULL,

    CONSTRAINT "InsurancePolicy_pkey" PRIMARY KEY ("insuranceId")
);

-- AddForeignKey
ALTER TABLE "InsurancePolicy" ADD CONSTRAINT "InsurancePolicy_insuranceUserId_fkey" FOREIGN KEY ("insuranceUserId") REFERENCES "User"("employeeId") ON DELETE RESTRICT ON UPDATE CASCADE;
