import {todoApi} from "../utils/api.js";

export const createTodo = async ( todoData) => {
    const todo = await todoApi.post("/todo/add", todoData)
        .then((response) => {
            console.log("Todo Created: ", response.data);
        }).catch(error => {
            console.log("Todo Error: ", error);
        });
}