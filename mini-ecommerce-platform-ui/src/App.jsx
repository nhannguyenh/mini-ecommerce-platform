import './App.css'
import {Route, Routes} from "react-router-dom";
import Navbar from "./components/Navbar.jsx";
import AuthPage from "./pages/AuthPage.jsx";
import CheckoutPage from "./pages/CheckoutPage.jsx";
import HomePage from "./pages/HomePage.jsx";


function App() {
    return (
        <div className="app">
            <Navbar />
            <Routes>
                <Route path={"/"} element={<HomePage />} />
                <Route path={"/auth"} element={<AuthPage />} />
                <Route path={"/checkout"} element={<CheckoutPage />} />
            </Routes>
        </div>
    )
}

export default App
