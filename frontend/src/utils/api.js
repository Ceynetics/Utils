import axios from "axios";

// Instance for user API
const userApi = axios.create({
    baseURL: "http://localhost:8080/", // User API base URL
});

// Instance for todo API
const todoApi = axios.create({
    baseURL: "http://localhost:8081/", // Todo API base URL
});

export { userApi, todoApi };
