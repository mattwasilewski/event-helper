import axios from "axios";

const API_URL = "http://localhost:8080/api/auth/";

const register = (username, password) => {
    let customConfig = {
        headers: {
            'Content-Type': 'application/json'
        }
    };
    let json = JSON.stringify({
        username: username,
        password: password,
    })
    return axios.post(API_URL + "signup", json, customConfig)
};

const login = (username, password) => {
    let customConfig = {
        headers: {
            'Content-Type': 'application/json'
        }
    };
    let json = JSON.stringify({
        username: username,
        password: password,
    })
    return axios
        .post(API_URL + "login", json, customConfig)
};

const logout = () => {
    return axios.post(API_URL + "signout").then((response) => {
        return response.data;
    });
};


const AuthService = {
    register,
    login,
    logout,
}

export default AuthService;
