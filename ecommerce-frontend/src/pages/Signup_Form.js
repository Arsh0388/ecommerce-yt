import "./Login_form.css"; // Reuse styles or create new if needed
import { useState } from "react";
import {useNavigate} from "react-router-dom";

const Signup_Form = () => {
    const [inputs, setInputs] = useState({});
    const navigate = useNavigate();
    const onChange = (event) => {
        const { name, value } = event.target;
        setInputs((values) => ({ ...values, [name]: value }));
    };

    const signup_form_submit = (event) => {
        event.preventDefault();

        if (inputs.password !== inputs.ConfirmPassword) {
            alert("Password Mismatch");
            return;
        }

        // Now navigate, since passwords match
        // send otp to the mail for verification
        fetch("http://localhost:5454/auth/send_otp", {
                method: "Post",
                headers: {
                    "Content-type": "application/json",
                },
                body: JSON.stringify(inputs),
            }
        )
        .then((res) => {
            if (!res.ok) throw new Error("Signup failed");
            return res.json();
        })
        .then((data) => {
            console.log("Signup Verification code sent", data);
            alert("Account will be created after that ");
            navigate("/Otp_Verification", { state: inputs });  // Pass data via state
        })
        .catch((err) => {
            console.error(err);
            alert("Signup failed");
        });

    };


    return (
        <div className="login-container">
            <div className="login-box">
                <h2 className="login-heading">Create Your Account</h2>
                <form className="login-form" onSubmit={signup_form_submit}>
                    <input type="text" name="username" placeholder="Username" onChange={onChange} required/>
                    <input type="email" name="email" placeholder="Email" onChange={onChange} required/>
                    <input type="password" name="password" placeholder="Password" onChange={onChange} required/>
                    <input type="password" name="ConfirmPassword" placeholder="Confirm Password" onChange={onChange} required/>
                    <button type="submit">Sign Up</button>
                </form>
            </div>
        </div>
    );
};

export default Signup_Form;
