/*
  Warnings:

  - The primary key for the `InsurancePolicy` table will be changed. If it partially fails, the table could be left without primary key constraint.
  - You are about to drop the column `insuranceId` on the `InsurancePolicy` table. All the data in the column will be lost.
  - You are about to drop the column `age` on the `User` table. All the data in the column will be lost.
  - Added the required column `Age` to the `User` table without a default value. This is not possible if the table is not empty.

*/
-- AlterTable
ALTER TABLE "InsurancePolicy" DROP CONSTRAINT "InsurancePolicy_pkey",
DROP COLUMN "insuranceId",
ADD COLUMN     "InsuranceId" SERIAL NOT NULL,
ADD CONSTRAINT "InsurancePolicy_pkey" PRIMARY KEY ("InsuranceId");

-- AlterTable
ALTER TABLE "User" DROP COLUMN "age",
ADD COLUMN     "Age" INTEGER NOT NULL;

-- CreateTable
CREATE TABLE "InsuranceClaim" (
    "ClaimId" SERIAL NOT NULL,
    "FirstName" VARCHAR(50) NOT NULL,
    "LastName" VARCHAR(50) NOT NULL,
    "ExpenseDate" VARCHAR(255) NOT NULL,
    "amount" DOUBLE PRECISION NOT NULL,
    "purpose" VARCHAR(255) NOT NULL,
    "followUp" INTEGER NOT NULL,
    "previousClaimId" INTEGER NOT NULL,
    "status" VARCHAR(20) NOT NULL,
    "LastEditedClaimDate" TIMESTAMP(3) NOT NULL,
    "policyId" INTEGER NOT NULL,

    CONSTRAINT "InsuranceClaim_pkey" PRIMARY KEY ("ClaimId")
);

-- AddForeignKey
ALTER TABLE "InsuranceClaim" ADD CONSTRAINT "InsuranceClaim_policyId_fkey" FOREIGN KEY ("policyId") REFERENCES "InsurancePolicy"("InsuranceId") ON DELETE RESTRICT ON UPDATE CASCADE;
