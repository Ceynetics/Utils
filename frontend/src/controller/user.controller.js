import  {userApi} from "../utils/api.js";
import {loginSuccess} from "../store/authSlice.js";

export const loginUser= async (dispatch, loginData) => {

      const user = await userApi.post("user/login",  loginData )
          .then((response) => {
              const token = response.data.token;

              dispatch(loginSuccess(token))
              console.log("User Logged In: ", response.data);
                localStorage.setItem("jwttoken", token);
          }
      ).catch(error => {console.log("Login Error: ", error);});
}


