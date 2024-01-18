-- AlterTable
ALTER TABLE "AdditionalUserDetails" ALTER COLUMN "dateOfBirth" SET DATA TYPE TEXT;

-- AlterTable
ALTER TABLE "Role" ALTER COLUMN "roleName" SET DEFAULT '',
ALTER COLUMN "roleFunction" SET DEFAULT '';
