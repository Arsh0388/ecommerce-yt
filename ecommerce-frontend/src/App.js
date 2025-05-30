import './App.css';
import React from 'react';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import Layout from "./pages/Layout";
import NoPage from "./pages/NoPage";
import Login_Form from "./pages/Login_Form";
import Signup_Form from "./pages/Signup_Form";
import Otp_Verification from "./pages/Otp_Verification";

function App() {
    return (
        <div className="App">
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<Layout />} >
                        <Route index element={<Home />} />
                        <Route path="*" element={<NoPage />} />
                        <Route path="/login-signup" element={<Login_Form />} />
                        <Route path = "signup" element = {<Signup_Form />} />
                        <Route path = "Otp_Verification" element = {<Otp_Verification />} />
                    </Route>

                </Routes>
            </BrowserRouter>
        </div>
    );
}

export default App;
