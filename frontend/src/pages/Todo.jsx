import {Button, Card, Dialog, Input, Option, Select, Textarea, Typography} from "@material-tailwind/react";
import {useState} from "react";
import {createTodo} from "../controller/todo.controller.js";

export const Todo = () => {

    const [dialogOpen, setDialogOpen] = useState(false);

    const [todoData, setTodoData] = useState({
        title: "",
        description: "",
        priority: "",
        completed: false
    });

    const handleTodoClick = (priority) => {
        setDialogOpen(!dialogOpen);
        setTodoData({
            ...todoData,
            priority: priority
        });
    }

    const handleTodoOnChange = (e) => {
        const {name, value} = e.target;
        setTodoData({
            ...todoData,
            [name]: value
        });
    }

    const handleTodoSubmit = async () => {
        await createTodo(todoData);
    }

    return (
        <div className="w-screen flex mt-20 gap-2 justify-center">
            <Dialog open={dialogOpen} handler={handleTodoClick} className="p-2 grid grid-cols-1 gap-2">
                <Typography
                    variant="h4"
                    className="text-center text-gray-800"> Add Note
                </Typography>
                <Input
                    label="Title"
                    name="title"
                    onChange={handleTodoOnChange}
                />
                <Textarea
                    label="Description"
                    name="description"
                    onChange={handleTodoOnChange}
                />
                <Button onClick={handleTodoSubmit}>Add Note</Button>
            </Dialog>

            <Card className="w-8/12 border-2 border-gray-800 items-center flex justify-center">
                <Typography className="text-center mt-2 text-gray-800" variant="h4">Create TODO</Typography>
                <div className="grid grid-cols-2 p-2 gap-2 grid-rows-2 w-full ">
                   <Button  variant="outlined" onClick={()=>handleTodoClick("URGENT_AND_IMPORTANT")}>
                       Urgent and Important
                   </Button>
                    <Button variant="outlined" className="h-40" onClick={()=>handleTodoClick("IMPORTANT_BUT_NOT_URGENT")}>
                        Not Urgent but Important
                    </Button>
                    <Button variant="outlined" className="h-40" onClick={()=>handleTodoClick("URGENT_BUT_NOT_IMPORTANT")}>
                        Not Important but Urgent
                    </Button>
                    <Button variant="outlined" className="h-40" onClick={()=>handleTodoClick("NOT_URGENT_AND_NOT_IMPORTANT")}>
                        Not Important and Not Urgent
                    </Button>
                </div>
            </Card>
        </div>
    )
}
