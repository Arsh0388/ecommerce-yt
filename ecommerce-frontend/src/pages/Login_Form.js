import {Form} from "react-router-dom";

const Login_Form = () => {
    return (
        <div>
            <h4>Login with Username / Email Or Password!</h4>
            <form method = "post" className="Login-form">
                <input type = "text" placeholder = "username or email"/>
                <input type = "password" placeholder = "password" />
            </form>
        </div>
    );
}

export default Login_Form;