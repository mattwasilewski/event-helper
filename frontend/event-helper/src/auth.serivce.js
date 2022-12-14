import axios from "axios";

const API_URL = "http://localhost:3000/api/auth/";


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

const login = (email, password) => {
    let customConfig = {
        headers: {
            'Content-Type': 'application/json'
        }
    };
    let json = JSON.stringify({
        email: email,
        password: password,
    })
    return axios
        .post(API_URL + "login", json, customConfig).then((response) => {
            localStorage.setItem("user", JSON.stringify(response.data))
            const user = JSON.parse(localStorage.getItem('user'))
            console.log('moj user: ' + user.value)
            const zabawa = parseJwt(user.value)
            console.log('login chyba hehe: ' + zabawa.sub)
        })
}

const parseJwt = (token) => {
    const base64Url = token.split('.')[1];
    const base64 = base64Url.replace('-', '+').replace('_', '/');
    console.log('return: ' + window.atob(base64))
    return JSON.parse(window.atob(base64));
}

const getCurrentUser = () => {
    return JSON.parse(localStorage.getItem('user'));
}


const logout = () => {
    return axios.post(API_URL + "logout").then((response) => {
        localStorage.removeItem("user");
        return response.data;
    });
};


const AuthService = {
    register,
    login,
    logout,
    getCurrentUser,
    parseJwt
}

export default AuthService;
