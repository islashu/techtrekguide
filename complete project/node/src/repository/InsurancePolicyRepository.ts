// Avoid create multiple prisma client as it will lag the application due to excess number of the same connections
import {prisma} from './UserRepository';
import {exitOnError} from 'winston';

//READ OPERATIONS
export const getAllInsurancePolicies = () => {
    return prisma.insurancePolicy.findMany({
        where: {
            insuranceType: 'Life insurance'
        }
    });
};

export const getAllInsurancePoliciesWithUser = (userId: number) => {
    return prisma.insurancePolicy.findMany({
        // This is effectively performing a JOIN
        where: {
            user: {
                userId: userId
            }
        }
    });
};

export const getDistinctTypeOfInsurance = () => {
    return prisma.insurancePolicy.findMany({
        where: {
            insuranceType: 'Life insurance'
        },
        distinct: ['insuranceType', "policyTerm"],
    });
};

export const getDistinctTypeOfInsuranceTes = () => {
    return prisma.insurancePolicy.findMany({
        where: {
            insuranceType: 'Life insurance'
        },
    });
};

export const getInsurancePolicyWithAggregate = (userId: number) => {
    return prisma.insurancePolicy.aggregate({
        _count: {
            insuranceType: true
        },
        where: {
            user: {
                userId: userId
            }
        },
    })
}

export const getInsurancePolicyWithGroupBy = (userId: number) => {
    return prisma.insurancePolicy.groupBy({
        by: ['insuranceType'],
        where: {
            user: {
                userId: userId
            }
        },
        _count: {
            insuranceType: true
        },
    })
}

export const getInsurancePolicyWithOrderBy= (userId: number) => {
    return prisma.insurancePolicy.findMany({
        where: {
            user: {
                userId: userId
            }
        },
        orderBy: {
            insuranceType: 'desc'
        },
        // Same as LIMIT in SQL, take only 10
        take: 10,
        // Skip the first 1-
        skip: 10,
    });
}

export const getInsuranceWithModifiers = (userId: number) => {
    return prisma.insurancePolicy.findMany({
        where: {
            user: {
                // userId: {in : [userId, 2,3,4,5,6,]},
                userId: {notIn : [userId, 2,3,4,5,6,]},
                // Greater than 18 years old
                age: {lt: 5, gt: 18},
            },
            insuranceType: {
                // Check if a string contains another certain string
                contains: 'Life',
                startsWith: 'Life',
                endsWith: 'Insurance',
            }
        },
        // Same as LIMIT in SQL, take only 10
        take: 10,
        // Skip the first 1-
        skip: 10,
    });
}

export const getInsurancePolicyWithANDoperator = (userId: number) => {
    return prisma.insurancePolicy.findMany({
        // Same as above but using AND operator
        where : {
            AND: [
                {
                    insuranceType: 'Life insurance'
                },
                {
                    user: {
                        userId: userId
                    }
                }
            ],
            NOT : [
                {
                    insuranceType: 'Life insurance'
                }
            ],
            OR : [
                {
                    insuranceType: 'Life insurance'
                },
                {
                    insuranceType: 'Life insurance'
                }
            ]
        }
    })
}

export const getInsurancePolicyById = (id: number) => {
    return prisma.insurancePolicy.findUnique({
        where: {
            insuranceId: id
        }
    });
};

// Create new object in prisma
export const createInsurancePolicy = () => {
    return prisma.insurancePolicy.create({
        //Use "data" field to specify the data to be inserted
        data: {
            insuranceType: 'Life insurance',
            policyStartDate: new Date(),
            policyEndDate: new Date(),
            policyTerm: '1 year',
            claimLimit: 1000.0,
            remainingClaimLimit: 1000.0
        },
        // When returning the created object, do you want to get the user object details as well
        include: {
            user: true
        }
    });
};


//CREATE OPERATIONS

export const createInsurancePolicyAndSelectCertainProperties = () => {
    return prisma.insurancePolicy.create({
        //Use "data" field to specify the data to be inserted
        data: {
            insuranceType: 'Life insurance',
            policyStartDate: new Date(),
            policyEndDate: new Date(),
            policyTerm: '1 year',
            claimLimit: 1000.0,
            remainingClaimLimit: 1000.0
        },
        // When returning the created object, you may want to get back certain properties only, you can use select or include
        select: {
            insuranceId: true,
            insuranceType: true,
            user: {
                select: {
                    firstName: true
                }
            }
        }
    });
};

// Create multiple insurance policies at one go
export const createMultipleInsurancePolicies = () => {
    return prisma.insurancePolicy.createMany({
        // Data must be in an array and include cannot be used in create many
        data: [
            {
                insuranceType: 'Life insurance',
                policyStartDate: new Date(),
                policyEndDate: new Date(),
                policyTerm: '1 year',
                claimLimit: 1000.0,
                remainingClaimLimit: 1000.0
            },
            {
                insuranceType: 'Life insurance',
                policyStartDate: new Date(),
                policyEndDate: new Date(),
                policyTerm: '1 year',
                claimLimit: 1000.0,
                remainingClaimLimit: 1000.0
            }
        ]
    });
};

// Create an insurance policy and a new user together and connect them together
export const createInsurancePolicyAndNewUser = () => {
    return prisma.insurancePolicy.create({
        data: {
            insuranceType: 'Life insurance',
            policyStartDate: new Date(),
            policyEndDate: new Date(),
            policyTerm: '1 year',
            claimLimit: 1000.0,
            remainingClaimLimit: 1000.0,
            user: {
                create: {
                    userEmail: 'userEmail@gmail.com',
                    firstName: 'firstName',
                    lastName: 'lastName',
                    password: 'password',
                    age: 18
                }
            }
        }
    });
};


// UPDATING RELATIONSHIPS
// Assuming your insurance policy does not have a user and you want to connect an existing user to this insurance policy
export const connectInsurancePolicyToExistingUser = (insuranceId: number, userId: number) => {
    return prisma.insurancePolicy.update({
        // Find the insurance policy first using id
        where: {
            insuranceId: insuranceId
        },
        // Once found
        data: {
            // Find and connect the user to the insurance policy
            user: {
                connect: {
                    // via the Id, preferably a field that is unique
                    userId: userId
                }
            }
        }
    });
};

export const updateInsurancePolicy = (insuranceId: number) => {
    return prisma.insurancePolicy.update({
        where: {
            insuranceId: insuranceId
        },
        data: {
            insuranceType: 'Life insurance updated',
            policyStartDate: new Date(),
            policyEndDate: new Date(),
            policyTerm: '1 year',
            claimLimit: 1000.0,
            remainingClaimLimit: 2000.0
        }
    });
}

// Removing a user from an insurance policy
export const removeUserFromInsurancePolicy = (insuranceId: number, userId: number) => {
    return prisma.insurancePolicy.update({
        where: {
            insuranceId: insuranceId
        },
        data: {
            user: {
                disconnect: {
                    userId: userId
                }
            }
        }
    });
};

// All operations that can be applied to a field in read operations can be applied to delete operations as well
export const deleteInsurancePolicy = (insuranceId: number) => {
    return prisma.insurancePolicy.delete({
        where: {
            insuranceId: insuranceId
        }
    });
}
