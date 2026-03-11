import {useContext} from "react";
import {AuthContext} from "../AuthContext.js";

function ProfilePage() {
    const {user} = useContext(AuthContext);
    return (
        <div style={{ padding: "0 1.5rem" }}>
            <h1>Profile</h1>
            <p>Name: {user.name}</p>
            <p>Here you could show more user info from the context.</p>
        </div>
    )
}

export default ProfilePage
