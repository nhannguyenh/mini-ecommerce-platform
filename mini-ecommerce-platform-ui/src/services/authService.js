import axios from "axios";

const API_URL = "http://localhost:8080/api";

export const register = async (data) => {
    const response = await axios.post(`${API_URL}/users/register`, data);
    return response.data;
};

export const login = async (data) => {
    const response = await axios.post(`${API_URL}/auth/login`, data);
    return response.data;
};