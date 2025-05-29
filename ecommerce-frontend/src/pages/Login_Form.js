import { Form } from "react-router-dom";
import "./Login_form.css";
import {useState} from "react"

const Login_Form = () => {
    const [inputs, setInputs] = useState({}); //using useState hook

    const onChange = (event) => {
        const {name:field_name, value } = event.target; // this specifies the mapping.
        // username : "username"
        // password : "password"
        // it changes the input fields and same event handler can be used again and again.
        setInputs((values => ({...values, [field_name]: value})))

    }
    const login_form_submit = (event) => {
        // check for all the fields that are required
        // onsubmit make a post request to the api endpoint.
        event.preventDefault();
        if (event.target.username === null || event.target.password === null ) {
            console.log(inputs);
            return alert("Invalid input. Please provide the login credentials");
        }
        // send the login credential to the backend
        fetch(`http://localhost:5454/auth/login`, {
                method: "POST",
                headers : {
                    "Content-type" : "application/json"
                },
                body : JSON.stringify({
                    username: event.target.username,
                    password: event.target.password
                })
            }
        )
        .then(res => {
            if (!res.ok) throw new Error("login failed");
            return res.json();
        })
        .then(data => {
            console.log("Login success" , data);
        })
        console.log(inputs);
    }

    return (
        <div className="login-container">
            <div className="login-box">
                <h2 className="login-heading">Welcome Back 👋</h2>
                <p className="login-subtext">Login to continue shopping with us</p>

                <form method="post" className="login-form">
                    <input type="text" name="username" placeholder = "Email or Username" value={inputs.username} onChange={onChange} required />
                    <input type="password" name="password" placeholder = "Password" value= {inputs.password } onChange={onChange} required />
                    <button type="submit" onClick = {login_form_submit}>Login</button>
                </form>

                <div className="divider"><span>or</span></div>

                <button className="google-login">Sign in with Google</button>

                <div className="login-footer">
                    <a href="#">Forgot Password?</a>
                    <a href="#">Create Account</a>
                </div>
            </div>
        </div>
    );
};

export default Login_Form;
