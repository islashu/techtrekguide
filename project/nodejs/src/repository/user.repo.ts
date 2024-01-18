import {PrismaClient, User} from '@prisma/client';
const prisma = new PrismaClient();

export const createUser = async (): Promise<User> => {
    const user = await prisma.user.create({
        data: {
            firstName: 'Ben',
            lastName: 'Choo',
            password: 'password',
            age: 20,
            roles: {
                create: {
                    roleName: 'User',
                    roleFunction: 'Test'
                }
            },
            pendingInsurancePolicies: {
                create: {
                    insuranceType: "Life",
                    approvedUserId: 1
                }
            },
            approvedInsurancePolicies: {
                create: {
                    pendindUserId: 2,
                    insuranceType: "life"
                }
            }

        },
        include: {
            insurancePolicies: true,
            roles: true
        }
    });
    return user;
};
