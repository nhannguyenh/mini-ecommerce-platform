import {useForm} from "react-hook-form";

function SignupForm() {
    const {
        register,
        handleSubmit,
        formState: {errors}
    } = useForm();

    function onSubmit(data) {
        console.log(`email: ${data.email} && password: ${data.password}`);
    }

    return (
        <div style={{maxWidth: 400, margin: "2rem auto"}}>
            <h1>Sign up</h1>

            <form onSubmit={handleSubmit(onSubmit)}>
                <div style={{marginBottom: "1rem"}}>
                    <label>
                        Email
                        <input
                            type="email"
                            {...register("email", {required: "Email is required"})}
                            placeholder="type your email ..."
                        />
                    </label>
                    {errors.email && (
                        <p style={{color: "red"}}>{errors.email.message}</p>
                    )}
                </div>
                <div style={{marginBottom: "1rem"}}>
                    <label>
                        Password
                        <input
                            type="password"
                            {...register("password", {
                                required: "Password is required",
                                minLength: {
                                    value: 4,
                                    message: "Password must be at least 4 characters"
                                }
                            })}
                            placeholder="*********"
                        />
                    </label>
                    {errors.password && (
                        <p style={{color: "red"}}>{errors.password.message}</p>
                    )}
                </div>
                <button type="submit">Create account</button>
            </form>
        </div>
    )
}

export default SignupForm;