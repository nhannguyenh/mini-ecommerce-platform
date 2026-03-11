import './App.css'
import {useState} from "react";
import {Route, Routes} from "react-router-dom";
import {AuthContext} from "./AuthContext.js";
import Navbar from "./components/Navbar.jsx";
import HomePage from "./pages/HomePage.jsx";
import LoginPage from "./pages/LoginPage.jsx";
import ProfilePage from "./pages/ProfilePage.jsx";


function App() {
    const [user, setUser] = useState({
        name: "",
        isAuth: false
    });

    function login(name) {
        setUser({
            name: name,
            isAuth: true
        })
    }

    function logout() {
        setUser({
            name: "",
            isAuth: false
        })
    }

    return (
        <div>
            <AuthContext.Provider value={{user, login, logout}}>
                <Navbar/>
                <Routes>
                    <Route path="/" element={<HomePage/>}/>
                    <Route path="/profile" element={<ProfilePage/>}/>
                    <Route path="/login" element={<LoginPage/>}/>
                    <Route path="*" element={<h1>404 Not Found</h1>}/>
                </Routes>
            </AuthContext.Provider>
        </div>
    )
}

export default App
