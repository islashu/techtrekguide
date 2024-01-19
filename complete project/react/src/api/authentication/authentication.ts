import {LoginDetails, RegisterDetails} from "../../components/LoginPage.tsx";
import axios from "../config/axios.ts";


export const httpAuthenticationLogin = async (loginDetails: LoginDetails) => {
    return await axios.post('/auth/login', loginDetails)
}

export const httpAutheticationRegister = async (registerDetails: RegisterDetails) => {
    return await axios.post('/auth/register', registerDetails)
}

export const httpAutheticationTest = async () => {
    return await axios.get('/auth/test')
}

