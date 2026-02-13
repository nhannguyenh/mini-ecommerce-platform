import {useState} from "react";
import {useNavigate} from "react-router-dom";
import {register} from "../services/authService";

const RegisterPage = () => {
    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        email: "",
        password: "",
        confirmPassword: "",
    });

    const [error, setError] = useState("");
    const [loading, setLoading] = useState(false);

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value,
        });
    };

    const validateForm = () => {
        if (!formData.email) return "Email is required";
        if (!formData.password) return "Password is required";
        if (formData.password.length < 6)
            return "Password must be at least 6 characters";
        return null;
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const validationError = validateForm();
        if (validationError) {
            setError(validationError);
            return;
        }

        try {
            setLoading(true);
            setError("");

            await register({
                email: formData.email,
                password: formData.password,
            });

            alert("Registration successful! Please login.");
            navigate("/login");
        } catch (err) {
            setError(
                err.response?.data?.message || "Registration failed. Try again."
            );
        } finally {
            setLoading(false);
        }
    };

    return (
        <div style={styles.container}>
            <div style={styles.card}>
                <h2>Create Account</h2>

                {error && <p style={styles.error}>{error}</p>}

                <form onSubmit={handleSubmit}>
                    <div style={styles.field}>
                        <label>Email</label>
                        <input
                            type="email"
                            name="email"
                            value={formData.email}
                            onChange={handleChange}
                            required
                        />
                    </div>

                    <div style={styles.field}>
                        <label>Password</label>
                        <input
                            type="password"
                            name="password"
                            value={formData.password}
                            onChange={handleChange}
                            required
                        />
                    </div>

                    <button type="submit" disabled={loading} style={styles.button}>
                        {loading ? "Registering..." : "Register"}
                    </button>
                </form>

                <p style={{ marginTop: "1rem" }}>
                    Already have an account?{" "}
                    <span
                        style={styles.link}
                        onClick={() => navigate("/login")}
                    >
            Login
          </span>
                </p>
            </div>
        </div>
    );
};

const styles = {
    container: {
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        height: "100vh",
        backgroundColor: "#f4f6f8",
    },
    card: {
        width: "350px",
        padding: "2rem",
        backgroundColor: "#fff",
        borderRadius: "8px",
        boxShadow: "0 4px 10px rgba(0,0,0,0.1)",
    },
    field: {
        display: "flex",
        flexDirection: "column",
        marginBottom: "1rem",
    },
    button: {
        width: "100%",
        padding: "0.75rem",
        backgroundColor: "#1976d2",
        color: "#fff",
        border: "none",
        borderRadius: "4px",
        cursor: "pointer",
    },
    error: {
        color: "red",
        marginBottom: "1rem",
    },
    link: {
        color: "#1976d2",
        cursor: "pointer",
    },
};

export default RegisterPage;