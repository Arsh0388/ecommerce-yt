import { Outlet, Link } from "react-router-dom";

const Layout = () => {
    return (
        <>
            <nav>
                <ul>
                    <li>
                        <Link to="/">Home</Link>
                    </li>
                    <li>
                        <Link to="login-signup">Login / Signup</Link> {/* ✅ Fix path */}
                    </li>
                </ul>
            </nav>

            {/* 🔽 This renders child route components like <Home />, <Login_Form />, etc. */}
            <Outlet />
        </>
    )
}

export default Layout;
