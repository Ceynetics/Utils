import {Button, Card, Dialog, Input, Typography} from "@material-tailwind/react";
import React, {useState} from 'react'
import {loginUser} from "../controller/user.controller.js";
import {useDispatch} from "react-redux";

export const Header = () => {

    const dispatch = useDispatch()

    const [loginOpen, setLoginOpen] = useState(false);
    const [loginData, setLoginData] = useState({
        email: "",
        password: ""
    });

    const handleLogin = () => {
        setLoginOpen(!loginOpen);
    }

    const handleLoginSubmit = async () => {
        console.log("Login Data: ", loginData);
        await dispatch(loginUser(dispatch, loginData));
        setLoginOpen(!loginOpen);
    }

    const handleLoginOnChange = (e) => {
        const {name, value} = e.target;
        setLoginData({
            ...loginData,
            [name]: value
        });
    }

    return (
        <div>
            <div className="bg-white shadow-md p-4 flex justify-between items-center">
                <h1 className="text-2xl font-semibold text-gray-700">Home</h1>
                <div>
                    <Button className="bg-blue-500 hover:bg-blue-600 text-white px-4 py-2 rounded-md" onClick={handleLogin}>Login</Button>
                    <Button className="bg-blue-500 hover:bg-blue-600 text-white px-4 py-2 rounded-md ml-2">Register</Button>
                </div>
            </div>

            <Dialog size="sm" open={loginOpen}>
                <Card className="p-4 items-center w-full">

                      <div className="flex items-center w-full justify-between p-2">
                          <Typography variant="h5" className="mb-2 text-gray-800">
                              Login User
                          </Typography>
                          <Typography variant="h5"  className="mb-2 text-gray-800 " onClick={()=>{handleLogin()}}>X</Typography>
                      </div>

                    <div className="grid grid-cols-1 gap-2 w-full">
                        <Input  name="email" type="email" label="Email"  onChange={handleLoginOnChange} required/>
                        <Input name="password" type="text" label="Password"  onChange={handleLoginOnChange} required/>
                    </div>
                    <Button fullWidth className="mt-2" onClick={handleLoginSubmit}>Login</Button>
                </Card>

            </Dialog>

        </div>
    )
}
