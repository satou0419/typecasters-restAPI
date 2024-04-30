import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import "./registration.css";
import "./components/input.css";
import { REGISTER_ENDPOINT } from "./api";

export default function Registration() {
  // State variables to store form data
  const [formData, setFormData] = useState({
    username: "",
    password: "",
    firstname: "",
    lastname: "",
    userType: "",
    confirmPassword: "",
    email: "",
  });

  const navigate = useNavigate();

  // Function to handle form input changes
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  // Function to handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault();
    // API URL for user registration

    try {
      const response = await fetch(REGISTER_ENDPOINT, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(formData),
      });
      if (!response.ok) {
        throw new Error("Registration failed");
      }
      alert("Registration successful!");
      navigate("/login");
    } catch (error) {
      console.error("Registration error:", error.message);
      alert("Registration failed. Please try again.");
    }
  };

  return (
    <main className="registration-container">
      <section className="card card-form">
        <aside className="form-registration-banner">
          <img
            src="./assets/banner/banner_registration.webp"
            alt="Login Banner"
          />
        </aside>

        <form className="card form-login" onSubmit={handleSubmit}>
          <h1 className="registration-heading">Create Account</h1>

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
              <input
                type="text"
                className="input input-box-form__s"
                placeholder="Lastname"
                name="lastname"
                value={formData.lastname}
                onChange={handleInputChange}
                required
              />
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
            <input
              type="email"
              className="input input-box-form"
              placeholder="Email"
              name="email"
              value={formData.email}
              onChange={handleInputChange}
              required
            />

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
            <button type="submit" className="btn btn--small btn--primary">
              Sign Up
            </button>
          </section>

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
