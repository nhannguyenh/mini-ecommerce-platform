import './App.css'
import axios from "axios";
import {useEffect, useState} from "react";
import {Route, Routes} from "react-router-dom";
import Navbar from "./components/Navbar.jsx";
import HomePage from "./pages/HomePage.jsx";
import LoginPage from "./pages/LoginPage.jsx";
import RegisterPage from "./pages/RegisterPage.jsx";
import CheckoutPage from "./pages/shopping-cart/CheckoutPage.jsx";


function App() {
    const [user, setUser] = useState(null);
    const [products, setProducts] = useState([]);

    useEffect(() => {
        axios.get("http://localhost:8080/api/products")
            .then((response) => {
                setProducts(response.data);
            });
    }, []);
    const [error, setError] = useState('');

    return (
        <div className="app">
            <Navbar user={user} setUser={setUser} />
            <Routes>
                <Route path={"/"} element={<HomePage products={products} />} />
                <Route path={"/register"} element={<RegisterPage />} />
                <Route path={"login"} element={<LoginPage setUser={setUser} />} />
                <Route path={"/checkout"} element={<CheckoutPage setError={setError} />} />
            </Routes>
        </div>
    )
}

export default App
