import axios from 'axios';

const BASE_URL = 'http://localhost:8000'; // URL of Backend, remember to add the api

/* This file contains all the necessary configuration of interceptor
 *
 * Initialise interceptor with configuration
 * Axios is configured to connect to the base url by default
 * */

export default axios.create({
    baseURL: BASE_URL,
    // You can set the headers here or in the credentials middlewa
    headers: {"Access-Control-Allow-Origin": "*"},
});

// This is to be used for any routes that requires authentication first
export const axiosPrivate = axios.create({
    baseURL: BASE_URL,
    headers: {'Content-Type': 'application/json'},
    withCredentials: true
});
