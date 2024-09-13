
import React from 'react'
import {Button} from "@material-tailwind/react";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import {Home} from "./pages/Home.jsx";
import {Header} from "./components/Header.jsx";
import {Todo} from "./pages/Todo.jsx";

const  App = () => {
    return (
        <BrowserRouter>
            <Header/>
            <Routes>
                <Route path="/" element={<Home/>} />
                <Route path="/todo" element={<Todo/>} />
            </Routes>
        </BrowserRouter>
    )
}

export default App;
