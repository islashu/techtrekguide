/*
  Warnings:

  - Changed the type of `ExpenseDate` on the `InsuranceClaims` table. No cast exists, the column would be dropped and recreated, which cannot be done if there is data, since the column is required.
  - Changed the type of `FollowUp` on the `InsuranceClaims` table. No cast exists, the column would be dropped and recreated, which cannot be done if there is data, since the column is required.
  - Changed the type of `LastEditClaimDate` on the `InsuranceClaims` table. No cast exists, the column would be dropped and recreated, which cannot be done if there is data, since the column is required.

*/
-- CreateEnum
CREATE TYPE "Status" AS ENUM ('PENDING', 'APPROVED', 'REJECTED');

-- CreateEnum
CREATE TYPE "FollowUp" AS ENUM ('YES', 'NO');

-- AlterTable
ALTER TABLE "InsuranceClaims" DROP COLUMN "ExpenseDate",
ADD COLUMN     "ExpenseDate" TIMESTAMP(3) NOT NULL,
DROP COLUMN "FollowUp",
ADD COLUMN     "FollowUp" "FollowUp" NOT NULL,
DROP COLUMN "LastEditClaimDate",
ADD COLUMN     "LastEditClaimDate" TIMESTAMP(3) NOT NULL;

-- AlterTable
ALTER TABLE "User" ALTER COLUMN "age" SET DEFAULT 18;

-- CreateTable
CREATE TABLE "Role" (
    "roleId" SERIAL NOT NULL,
    "roleName" TEXT NOT NULL,
    "roleFunction" TEXT NOT NULL,

    CONSTRAINT "Role_pkey" PRIMARY KEY ("roleId")
);

-- CreateTable
CREATE TABLE "_RoleToUser" (
    "A" INTEGER NOT NULL,
    "B" INTEGER NOT NULL
);

-- CreateIndex
CREATE UNIQUE INDEX "_RoleToUser_AB_unique" ON "_RoleToUser"("A", "B");

-- CreateIndex
CREATE INDEX "_RoleToUser_B_index" ON "_RoleToUser"("B");

-- AddForeignKey
ALTER TABLE "_RoleToUser" ADD CONSTRAINT "_RoleToUser_A_fkey" FOREIGN KEY ("A") REFERENCES "Role"("roleId") ON DELETE CASCADE ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "_RoleToUser" ADD CONSTRAINT "_RoleToUser_B_fkey" FOREIGN KEY ("B") REFERENCES "User"("employeeId") ON DELETE CASCADE ON UPDATE CASCADE;
