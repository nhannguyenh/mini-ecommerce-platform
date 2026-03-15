import {Link} from "react-router-dom";

function Navbar({user}) {
    return (
        <nav className="navbar">
            <div className="navbar-container">
                <Link to="/" className="navbar-brand">
                    Ecommerce
                </Link>
                <div className="navbar-links">
                    <Link to="/" className="navbar-link">
                        Home
                    </Link>
                    <Link to="/checkout" className="navbar-link">
                        Cart
                    </Link>
                </div>
                <div className="navbar-auth">
                    {user != null ? (
                        <div className="navbar-auth-links">
                            <p className="navbar-link welcome-text">
                                Welcome, <span>{user.email}</span>
                            </p>
                            <button className="btn btn-primary">
                                Logout
                            </button>
                        </div>
                    ) : (
                        <div className="navbar-auth-links">
                            <Link to="/login" className="btn btn-secondary">
                                Login
                            </Link>
                            <Link to="/register" className="btn btn-primary">
                                Register
                            </Link>
                        </div>
                    )}
                </div>
            </div>
        </nav>
    )
}

export default Navbar
