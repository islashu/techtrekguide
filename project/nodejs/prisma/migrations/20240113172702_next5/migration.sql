/*
  Warnings:

  - You are about to drop the column `userId` on the `InsurancePolicies` table. All the data in the column will be lost.

*/
-- DropForeignKey
ALTER TABLE "InsurancePolicies" DROP CONSTRAINT "InsurancePolicies_userId_fkey";

-- AlterTable
ALTER TABLE "InsurancePolicies" DROP COLUMN "userId";

-- AddForeignKey
ALTER TABLE "InsurancePolicies" ADD CONSTRAINT "InsurancePolicies_insuranceId_fkey" FOREIGN KEY ("insuranceId") REFERENCES "User"("employeeId") ON DELETE RESTRICT ON UPDATE CASCADE;
