import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import "./registration.css";
import "./components/input.css";
import { REGISTER_ENDPOINT } from "./api";

export default function Registration() {
  const [formData, setFormData] = useState({
    username: "",
    password: "",
    confirmPassword: "",
    firstname: "",
    lastname: "",
    userType: "",
    email: "",
  });
  const [formErrors, setFormErrors] = useState({});
  const navigate = useNavigate();
  const [error, setError] = useState("");

  const validateForm = () => {
    const errors = {};
    if (!formData.username) {
      errors.username = "Username is required";
    }
    if (!formData.password) {
      errors.password = "Password is required";
    } else if (formData.password.length < 8) {
      errors.password = "Password must be at least 8 characters";
    }
    if (formData.password !== formData.confirmPassword) {
      errors.confirmPassword = "Passwords do not match";
    }
    if (!formData.firstname) {
      errors.firstname = "Firstname is required";
    }
    if (!formData.lastname) {
      errors.lastname = "Lastname is required";
    }
    if (!formData.email) {
      errors.email = "Email is required";
    } else if (!/\S+@\S+\.\S+/.test(formData.email)) {
      errors.email = "Invalid email address";
    }
    if (!formData.userType) {
      errors.userType = "User type is required";
    }
    setFormErrors(errors);
    return Object.keys(errors).length === 0;
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!validateForm()) return;

    try {
      const response = await fetch(REGISTER_ENDPOINT, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(formData),
      });

      if (!response.ok) {
        // If response status is not ok, parse the error message as text
        const errorMessage = await response.text();
        alert(errorMessage); // Display the error message to the user
        setError(errorMessage);
      } else {
        // Registration successful
        alert("Registration successful!");
        navigate("/login");
      }
    } catch (error) {
      // Handle unexpected errors
      console.error("Registration error:", error.message);
      alert("Registration failed. Please try again.");
    }
  };

  return (
    <main className="registration-container">
      <div className="toast-box">
        {/* Display error message */}
        <p>{error}</p>
      </div>

      <section className="card card-form">
        {/* Banner section */}
        <aside className="form-registration-banner">
          <img
            src="./assets/banner/banner_registration.webp"
            alt="Login Banner"
          />
        </aside>

        {/* Registration form */}
        <form className="card form-login" onSubmit={handleSubmit}>
          <h1 className="registration-heading">Create Account</h1>

          {/* Form fields */}
          <section className="form-section">
            <div className="small-input-group">
              <input
                type="text"
                className="input input-box-form__s"
                placeholder="Firstname"
                name="firstname"
                value={formData.firstname}
                onChange={handleInputChange}
                autoFocus
                required
              />
              {formErrors.firstname && (
                <div className="error">{formErrors.firstname}</div>
              )}
              <input
                type="text"
                className="input input-box-form__s"
                placeholder="Lastname"
                name="lastname"
                value={formData.lastname}
                onChange={handleInputChange}
                required
              />
              {formErrors.lastname && (
                <div className="error">{formErrors.lastname}</div>
              )}
            </div>

            <input
              type="text"
              className="input input-box-form"
              placeholder="Username"
              name="username"
              value={formData.username}
              onChange={handleInputChange}
              required
            />

            <input
              type="password"
              className="input input-box-form"
              placeholder="Password"
              name="password"
              value={formData.password}
              onChange={handleInputChange}
              required
            />

            <input
              type="password"
              className="input input-box-form"
              placeholder="Confirm Password"
              name="confirmPassword"
              value={formData.confirmPassword}
              onChange={handleInputChange}
              required
            />
            {formErrors.confirmPassword && (
              <div className="error">{formErrors.confirmPassword}</div>
            )}

            <input
              type="email"
              className="input input-box-form"
              placeholder="Email"
              name="email"
              value={formData.email}
              onChange={handleInputChange}
              required
            />
            {formErrors.email && (
              <div className="error">{formErrors.email}</div>
            )}

            <select
              id="usertype"
              className="input input-box-form input-select"
              name="userType"
              value={formData.userType}
              onChange={handleInputChange}
              required
            >
              <option value="" disabled hidden>
                Select User Type
              </option>
              <option value="student">Student</option>
              <option value="teacher">Teacher</option>
            </select>
            {formErrors.userType && (
              <div className="error">{formErrors.userType}</div>
            )}

            <button type="submit" className="btn btn--small btn--primary">
              Sign Up
            </button>
          </section>

          {/* Links section */}
          <div className="link-group">
            <span>
              Already have an account?
              <Link to="/login">
                <span className="li-signup"> Sign in</span>
              </Link>
            </span>
            <span>
              <span>Terms and Condition |</span>
              <span>Privacy Policy |</span>
              <span>Support</span>
            </span>
          </div>
        </form>
      </section>
    </main>
  );
}
