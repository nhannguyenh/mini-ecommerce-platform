import {useContext} from "react";
import {AuthContext} from "../AuthContext.js";

function HomePage() {
    const {user} = useContext(AuthContext);

    return (
        <div style={{padding: "0 1.5rem"}}>
            <h1>Home Page</h1>
            {
                user.isAuth ? (
                    <p>Welcome back, {user.name}</p>
                ) : (
                    <p>You are not logged in. Go to the login page to sign in.</p>
                )
            }
        </div>
    )
}
export default HomePage
