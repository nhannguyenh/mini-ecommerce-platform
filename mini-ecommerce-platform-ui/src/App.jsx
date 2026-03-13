import './App.css'
import {Route, Routes} from "react-router-dom";
import Navbar from "./components/Navbar.jsx";
import CheckoutPage from "./pages/CheckoutPage.jsx";
import HomePage from "./pages/HomePage.jsx";
import LoginPage from "./pages/LoginPage.jsx";
import RegisterPage from "./pages/RegisterPage.jsx";


function App() {
    // const [user, setUser] = useState(null);
    // const [error, setError] = useState('');

    // useEffect(() => {
    //     const fetchUser = async () => {
    //         const token = localStorage.getItem("token");
    //
    //         if (token) {
    //             try {
    //                 const response = await axios.post("/api/auth/login", {
    //                     headers: {Authorization: `Bearer ${token}`}
    //                 });
    //                 setUser(response.data);
    //             } catch (e) {
    //                 setError(`Failed to fetch user: ${e.message}`);
    //                 localStorage.removeItem("token");
    //             }
    //
    //         }
    //     }
    //     fetchUser();
    // }, [])

    return (
        <div className="app">
            <Navbar />
            <Routes>
                <Route path={"/"} element={<HomePage />} />
                <Route path={"/register"} element={<RegisterPage />} />
                <Route path={"login"} element={<LoginPage />} />
                <Route path={"/checkout"} element={<CheckoutPage />} />
            </Routes>
        </div>
    )
}

export default App
