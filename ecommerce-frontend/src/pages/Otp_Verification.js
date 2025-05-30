import { useLocation } from "react-router-dom";
import "./Otp_Verification.css";
import {useState} from "react";

function Otp_Verification() {
    const location = useLocation();
    const { state } = location;
    const { inputs, setInputs } = useState({ otp: "" });

    const handleOtpSetup =  (event) => {
        const { name , value } = event.target;
        setInputs((prev) => ({ ...prev, [name]: value }));
    }
    const handleLoginSignup = (event) => {
        event.preventDefault();
        const dataToSend = {
            ...state.inputs,
            otp: inputs.otp,
        };
        // Simulate OTP check and create account
        fetch("http://localhost:5454/auth/signup", {
            method: "POST",
            headers: {
                "Content-type": "application/json",
            },
            body: JSON.stringify(state.inputs),
        })
            .then((res) => {
                if (!res.ok) throw new Error("Signup failed");
                return res.json();
            })
            .then((data) => {
                console.log("User created successfully", data);
                alert("Account has been created.");
            })
            .catch((err) => {
                console.error(err);
                alert("Signup failed");
            });
    };

    return (
        <div className="otp-container">
            <div className="otp-box">
                <h2 className="otp-heading">OTP Verification</h2>
                <form className="otp-form">
                    <input type="text" placeholder="Enter OTP" onChange={handleOtpSetup} />
                    <button type="submit" onClick={handleLoginSignup}>Verify</button>
                </form>
            </div>
        </div>
    );
}

export default Otp_Verification;
