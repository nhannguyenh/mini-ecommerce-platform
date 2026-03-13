import axios from "axios";
import {useState} from "react";
import {useNavigate} from "react-router-dom";

function LoginPage() {
    const [formData, setFormData] = useState({
        email: "",
        password: ""
    });
    const [error, setError] = useState("");
    const navigate = useNavigate();

    function handleChange(e) {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        })
    }

    async function handleSubmit(e) {
        e.preventDefault();
        try {
            const response = await axios.post('/api/auth/login', formData);
            localStorage.setItem("token", response.data.token);
            // setUser(response.data.email);
            navigate("/");
        } catch (e) {
            setError(e.response?.data?.message || "Login failed");
        }
    }

    return (
        <div className="page">
            <div className="container">
                <div className="auth-container">
                    <h1 className="page-title">Login</h1>
                    {error.email && (
                        <span className="form-error">{error}</span>
                    )}
                    <form className="auth-form" onSubmit={handleSubmit}>
                        <div className="form-group">
                            <label className="form-label" htmlFor="email">
                                Email
                            </label>
                            <input
                                className="form-input"
                                type="email"
                                name="email"
                                value={formData.email}
                                onChange={handleChange}
                                placeholder="Enter your email"
                                required
                            />
                        </div>
                        <div className="form-group">
                            <label className="form-label" htmlFor="password">
                                Password
                            </label>
                            <input
                                className="form-input"
                                type="password"
                                name="password"
                                value={formData.password}
                                onChange={handleChange}
                                placeholder="Enter your password"
                                required
                            />
                        </div>

                        <button
                            type="submit"
                            className="btn btn-primary btn-large btn-login">
                            Login
                        </button>
                    </form>
                </div>
            </div>
        </div>
    )
}

export default LoginPage
