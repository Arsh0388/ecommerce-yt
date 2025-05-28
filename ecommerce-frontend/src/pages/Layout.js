import { Outlet, Link } from "react-router-dom";
import './Layout.css';

const Layout = () => {
    return (
        <>
            <nav className="navbar">
                <ul className="nav-list">
                    <li className="nav-item brand">
                        <Link to="/">Edmonton Bazaar</Link>
                    </li>
                    <div className="nav-main-links">
                        <li className="nav-item"><a href="#news">News</a></li>
                        <li className="nav-item"><a href="#contact">Contact</a></li>
                        <li className="nav-item"><a href="#about">About</a></li>
                    </div>
                    <div className="nav-actions">
                        <div className="search-group">
                            <input type="text" placeholder="Search..." className="search-input" />
                            <button className="search-button">🔍</button>
                        </div>
                        <button className="nav-btn">❤️</button>
                        <button className="nav-btn">🛒</button>
                        <Link className="nav-btn" to = "/login-signup" >👤</Link>
                    </div>
                </ul>
            </nav>

            <main className="content">
                <Outlet />
            </main>
        </>
    );
};

export default Layout;
