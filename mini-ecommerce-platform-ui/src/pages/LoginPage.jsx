import {useContext, useState} from "react";
import {AuthContext} from "../AuthContext.js";

function LoginPage() {
    const [name, setName] = useState("");
    const {user, login} = useContext(AuthContext)

    function handleSubmit(event) {
        event.preventDefault();
        if (!name.trim()) return;
        login(name);
    }

    return (
        <div style={{padding: "0 1.5rem"}}>
            <h1>Login</h1>
            <form onSubmit={handleSubmit} style={{marginTop: "1rem"}}>
                <label>
                    Name
                    <input
                        type="text"
                        placeholder="Type your name..."
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        style={{marginLeft: "0.5rem"}}
                    />
                </label>
                <button type="submit" style={{marginLeft: "0.5rem"}}>
                    Login
                </button>

                {user.isAuth && <p>User Logged In</p>}
            </form>
        </div>
    )
}
export default LoginPage