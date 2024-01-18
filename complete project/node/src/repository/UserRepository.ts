import {InsurancePolicy, PrismaClient, User} from "@prisma/client";

const prisma = new PrismaClient({});

// Include base methods only
export const createUser = async (firstName :string, lastName :string, age: number , email:string, password:string): Promise<User> => {
    const user: User= await prisma.user.create({
        data: {
            userEmail: email,
            password: password,
            firstName: firstName,
            lastName: lastName,
            age: age,
            insurancePolicies: {}
        }
    })
    return user
}

export const findUser = async (userEmail: string): Promise<User> => {
    // Find unique requires the field to be marked as @unique in the schema
    const user: User | null= await prisma.user.findUnique({
        where: {
            userEmail: userEmail,
        }
    })

    if (!user) throw new Error('User not found')
    return user
}

export const updateUserWithAccessToken = async(userEmail: string, accessToken: string): Promise<void> => {
    const user: User | null = await prisma.user.update({
        where: {
            userEmail: userEmail
        },
        data: {
            accessToken: accessToken
        }
    })
}




