/*
  Warnings:

  - You are about to drop the column `Amount` on the `InsuranceClaims` table. All the data in the column will be lost.
  - You are about to drop the column `ExpenseDate` on the `InsuranceClaims` table. All the data in the column will be lost.
  - You are about to drop the column `FirstName` on the `InsuranceClaims` table. All the data in the column will be lost.
  - You are about to drop the column `FollowUp` on the `InsuranceClaims` table. All the data in the column will be lost.
  - You are about to drop the column `LastEditClaimDate` on the `InsuranceClaims` table. All the data in the column will be lost.
  - You are about to drop the column `LastName` on the `InsuranceClaims` table. All the data in the column will be lost.
  - You are about to drop the column `PreviousClaimID` on the `InsuranceClaims` table. All the data in the column will be lost.
  - You are about to drop the column `Purpose` on the `InsuranceClaims` table. All the data in the column will be lost.
  - You are about to drop the column `Status` on the `InsuranceClaims` table. All the data in the column will be lost.
  - You are about to drop the column `ClaimLimit` on the `InsurancePolicies` table. All the data in the column will be lost.
  - You are about to drop the column `InsuaranceType` on the `InsurancePolicies` table. All the data in the column will be lost.
  - You are about to drop the column `RemainingClaimLimit` on the `InsurancePolicies` table. All the data in the column will be lost.
  - You are about to drop the column `UserId` on the `InsurancePolicies` table. All the data in the column will be lost.
  - You are about to drop the column `age` on the `User` table. All the data in the column will be lost.
  - You are about to drop the column `firstName` on the `User` table. All the data in the column will be lost.
  - You are about to drop the column `lastName` on the `User` table. All the data in the column will be lost.
  - You are about to drop the column `password` on the `User` table. All the data in the column will be lost.
  - Added the required column `amount` to the `InsuranceClaims` table without a default value. This is not possible if the table is not empty.
  - Added the required column `expenseDate` to the `InsuranceClaims` table without a default value. This is not possible if the table is not empty.
  - Added the required column `firstName` to the `InsuranceClaims` table without a default value. This is not possible if the table is not empty.
  - Added the required column `followUp` to the `InsuranceClaims` table without a default value. This is not possible if the table is not empty.
  - Added the required column `lastEditClaimDate` to the `InsuranceClaims` table without a default value. This is not possible if the table is not empty.
  - Added the required column `lastName` to the `InsuranceClaims` table without a default value. This is not possible if the table is not empty.
  - Added the required column `previousClaimID` to the `InsuranceClaims` table without a default value. This is not possible if the table is not empty.
  - Added the required column `purpose` to the `InsuranceClaims` table without a default value. This is not possible if the table is not empty.
  - Added the required column `status` to the `InsuranceClaims` table without a default value. This is not possible if the table is not empty.
  - Added the required column `claimLimit` to the `InsurancePolicies` table without a default value. This is not possible if the table is not empty.
  - Added the required column `insuaranceType` to the `InsurancePolicies` table without a default value. This is not possible if the table is not empty.
  - Added the required column `remainingClaimLimit` to the `InsurancePolicies` table without a default value. This is not possible if the table is not empty.
  - Added the required column `userId` to the `InsurancePolicies` table without a default value. This is not possible if the table is not empty.
  - Added the required column `FirstName` to the `User` table without a default value. This is not possible if the table is not empty.
  - Added the required column `LastName` to the `User` table without a default value. This is not possible if the table is not empty.
  - Added the required column `Password` to the `User` table without a default value. This is not possible if the table is not empty.

*/
-- DropForeignKey
ALTER TABLE "InsurancePolicies" DROP CONSTRAINT "InsurancePolicies_UserId_fkey";

-- AlterTable
ALTER TABLE "InsuranceClaims" DROP COLUMN "Amount",
DROP COLUMN "ExpenseDate",
DROP COLUMN "FirstName",
DROP COLUMN "FollowUp",
DROP COLUMN "LastEditClaimDate",
DROP COLUMN "LastName",
DROP COLUMN "PreviousClaimID",
DROP COLUMN "Purpose",
DROP COLUMN "Status",
ADD COLUMN     "amount" DOUBLE PRECISION NOT NULL,
ADD COLUMN     "expenseDate" TIMESTAMP(3) NOT NULL,
ADD COLUMN     "firstName" VARCHAR(50) NOT NULL,
ADD COLUMN     "followUp" "FollowUp" NOT NULL,
ADD COLUMN     "lastEditClaimDate" TIMESTAMP(3) NOT NULL,
ADD COLUMN     "lastName" VARCHAR(50) NOT NULL,
ADD COLUMN     "previousClaimID" INTEGER NOT NULL,
ADD COLUMN     "purpose" VARCHAR(255) NOT NULL,
ADD COLUMN     "status" VARCHAR(20) NOT NULL;

-- AlterTable
ALTER TABLE "InsurancePolicies" DROP COLUMN "ClaimLimit",
DROP COLUMN "InsuaranceType",
DROP COLUMN "RemainingClaimLimit",
DROP COLUMN "UserId",
ADD COLUMN     "claimLimit" DOUBLE PRECISION NOT NULL,
ADD COLUMN     "insuaranceType" VARCHAR(100) NOT NULL,
ADD COLUMN     "remainingClaimLimit" DOUBLE PRECISION NOT NULL,
ADD COLUMN     "userId" INTEGER NOT NULL;

-- AlterTable
CREATE SEQUENCE user_version_seq;
ALTER TABLE "User" DROP COLUMN "age",
DROP COLUMN "firstName",
DROP COLUMN "lastName",
DROP COLUMN "password",
ADD COLUMN     "Age" INTEGER NOT NULL DEFAULT 18,
ADD COLUMN     "FirstName" VARCHAR(50) NOT NULL,
ADD COLUMN     "LastName" VARCHAR(50) NOT NULL,
ADD COLUMN     "Password" VARCHAR(20) NOT NULL,
ALTER COLUMN "version" SET DEFAULT nextval('user_version_seq');
ALTER SEQUENCE user_version_seq OWNED BY "User"."version";

-- AddForeignKey
ALTER TABLE "InsurancePolicies" ADD CONSTRAINT "InsurancePolicies_userId_fkey" FOREIGN KEY ("userId") REFERENCES "User"("employeeId") ON DELETE RESTRICT ON UPDATE CASCADE;
