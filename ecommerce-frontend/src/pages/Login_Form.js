import { Form } from "react-router-dom";
import "./Login_form.css";

const Login_Form = () => {
    return (
        <div className="login-container">
            <div className="login-box">
                <h2 className="login-heading">Welcome Back 👋</h2>
                <p className="login-subtext">Login to continue shopping with us</p>

                <form method="post" className="login-form">
                    <input type="text" name="username" placeholder="Email or Username" required />
                    <input type="password" name="password" placeholder="Password" required />
                    <button type="submit">Login</button>
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
