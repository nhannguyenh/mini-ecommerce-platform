import './App.css'
import {BrowserRouter, Route, Routes} from 'react-router-dom';
import RegisterPage from "./pages/RegisterPage.jsx";
import SignupForm from "./components/SignupForm.jsx";

function App() {
    return (
        <>
            <BrowserRouter>
                <Routes>
                    <Route path="/register" element={<RegisterPage/>}/>
                </Routes>
            </BrowserRouter>
            <div>
                <SignupForm/>
            </div>
        </>
    )
}

export default App
