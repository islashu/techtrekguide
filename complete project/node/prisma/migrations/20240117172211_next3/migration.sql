/*
  Warnings:

  - You are about to alter the column `UserEmail` on the `User` table. The data in that column could be lost. The data in that column will be cast from `Text` to `VarChar(100)`.
  - A unique constraint covering the columns `[UserEmail]` on the table `User` will be added. If there are existing duplicate values, this will fail.

*/
-- AlterTable
ALTER TABLE "User" ALTER COLUMN "UserEmail" SET DATA TYPE VARCHAR(100);

-- CreateIndex
CREATE UNIQUE INDEX "User_UserEmail_key" ON "User"("UserEmail");
