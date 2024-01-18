-- AlterTable
CREATE SEQUENCE role_roleid_seq;
ALTER TABLE "Role" ALTER COLUMN "roleId" SET DEFAULT nextval('role_roleid_seq');
ALTER SEQUENCE role_roleid_seq OWNED BY "Role"."roleId";

-- CreateTable
CREATE TABLE "AdditionalUserDetails" (
    "id" TEXT NOT NULL,
    "userId" INTEGER NOT NULL,
    "nric" TEXT NOT NULL,
    "dateOfBirth" TIMESTAMP(3) NOT NULL,

    CONSTRAINT "AdditionalUserDetails_pkey" PRIMARY KEY ("id")
);

-- CreateIndex
CREATE UNIQUE INDEX "AdditionalUserDetails_userId_key" ON "AdditionalUserDetails"("userId");

-- AddForeignKey
ALTER TABLE "AdditionalUserDetails" ADD CONSTRAINT "AdditionalUserDetails_userId_fkey" FOREIGN KEY ("userId") REFERENCES "User"("employeeId") ON DELETE RESTRICT ON UPDATE CASCADE;
