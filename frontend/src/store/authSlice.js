import { createSlice } from '@reduxjs/toolkit';

const initialState = {
    isAuthenticated: false,
    token: null, // Store the JWT token here
};

const authSlice = createSlice({
    name: 'auth',
    initialState,
    reducers: {
        loginSuccess: (state, action) => {
            state.isAuthenticated = true;
            state.token = action.payload; // Save JWT token
        },
        logoutSuccess: (state) => {
            state.isAuthenticated = false;
            state.token = null;
        },
    },
});

export const { loginSuccess, logoutSuccess } = authSlice.actions;

export default authSlice.reducer;
