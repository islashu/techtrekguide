/*
  Warnings:

  - You are about to drop the column `insuaranceType` on the `InsurancePolicies` table. All the data in the column will be lost.
  - Added the required column `insuranceType` to the `InsurancePolicies` table without a default value. This is not possible if the table is not empty.

*/
-- AlterTable
ALTER TABLE "InsurancePolicies" DROP COLUMN "insuaranceType",
ADD COLUMN     "insuranceType" VARCHAR(100) NOT NULL,
ALTER COLUMN "PolicyStartDate" SET DEFAULT 'now',
ALTER COLUMN "PolicyTerm" SET DEFAULT '1 year',
ALTER COLUMN "PolicyEndDate" SET DEFAULT '2025',
ALTER COLUMN "claimLimit" SET DEFAULT 300.00,
ALTER COLUMN "remainingClaimLimit" SET DEFAULT 1000;
