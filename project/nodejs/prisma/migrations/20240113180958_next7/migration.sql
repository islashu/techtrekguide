/*
  Warnings:

  - The primary key for the `AdditionalUserDetails` table will be changed. If it partially fails, the table could be left without primary key constraint.
  - The `id` column on the `AdditionalUserDetails` table would be dropped and recreated. This will lead to data loss if there is data in the column.
  - Added the required column `approvedUserId` to the `InsurancePolicies` table without a default value. This is not possible if the table is not empty.
  - Added the required column `pendindUserId` to the `InsurancePolicies` table without a default value. This is not possible if the table is not empty.

*/
-- DropForeignKey
ALTER TABLE "AdditionalUserDetails" DROP CONSTRAINT "AdditionalUserDetails_userId_fkey";

-- DropForeignKey
ALTER TABLE "InsurancePolicies" DROP CONSTRAINT "InsurancePolicies_insuranceId_fkey";

-- AlterTable
ALTER TABLE "AdditionalUserDetails" DROP CONSTRAINT "AdditionalUserDetails_pkey",
DROP COLUMN "id",
ADD COLUMN     "id" SERIAL NOT NULL,
ADD CONSTRAINT "AdditionalUserDetails_pkey" PRIMARY KEY ("id");

-- AlterTable
ALTER TABLE "InsurancePolicies" ADD COLUMN     "approvedUserId" INTEGER NOT NULL,
ADD COLUMN     "pendindUserId" INTEGER NOT NULL;

-- AddForeignKey
ALTER TABLE "User" ADD CONSTRAINT "User_employeeId_fkey" FOREIGN KEY ("employeeId") REFERENCES "AdditionalUserDetails"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "InsurancePolicies" ADD CONSTRAINT "InsurancePolicies_pendindUserId_fkey" FOREIGN KEY ("pendindUserId") REFERENCES "User"("employeeId") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "InsurancePolicies" ADD CONSTRAINT "InsurancePolicies_approvedUserId_fkey" FOREIGN KEY ("approvedUserId") REFERENCES "User"("employeeId") ON DELETE RESTRICT ON UPDATE CASCADE;
