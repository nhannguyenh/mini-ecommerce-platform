import {useContext} from "react";
import {Link} from "react-router-dom";
import {AuthContext} from "../AuthContext.js";

function Navbar() {
    const {user, logout} = useContext(AuthContext);
    return (
        <header
            style={{
                padding: "1rem 1.5rem",
                marginBottom: "1rem",
                borderBottom: "1px solid #e5e7eb",
                display: "flex",
                justifyContent: "space-between",
            }}
        >
            <nav style={{ display: "flex", gap: "1rem" }}>
                <Link to={"/"}>Home</Link>
                <Link to={"/profile"}>Profile</Link>
            </nav>
            <div>
                {
                    !user.isAuth ? (
                        <Link to={"/login"}>Login</Link>
                    ) : (
                        <button onClick={logout}>Logout</button>
                    )
                }

            </div>
        </header>
    )
}
export default Navbar