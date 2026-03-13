import axios from "axios";
import {useState} from "react";
import {useNavigate} from "react-router-dom";

function RegisterPage() {
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
            await axios.post('/api/users/register', formData);
            navigate("/login");
        } catch (e) {
            setError(e.response?.data?.message || "Register failed");
        }
    }


    return (
        <div className="page">
            <div className="container">
                <div className="auth-container">
                    <h1 className="page-title">Register</h1>
                    {error && (
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
                            Register
                        </button>
                    </form>
                </div>
            </div>
        </div>
    )
}

export default RegisterPage
