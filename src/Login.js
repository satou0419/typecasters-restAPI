import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./components/input.css";
import "./login.css";
import "./components/animation.css";
import { LOGIN_ENDPOINT } from "./api";
import Loader from "./Loader"; // Import Loader component

export const USER_TYPE = "userType";
export const USER_ID = "userID";

export default function Login({ setIsLoggedIn, setUserDetails }) {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(null);
  const [isLoading, setIsLoading] = useState(false); // Add isLoading state
  const navigate = useNavigate();

  const handleSubmit = async (event) => {
    event.preventDefault();
    setIsLoading(true); // Show loader while request is being processed

    try {
      const response = await fetch(LOGIN_ENDPOINT, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          username,
          password,
        }),
      });

      if (!response.ok) {
        const errorMessage = await response.text();
        if (response.status === 404) {
          setError("Username not found");
          document
            .querySelector(".input-box-form")
            .classList.add("animate-error");
        } else {
          setError("Incorrect password");
          document
            .querySelectorAll(".input-box-form")[1]
            .classList.add("animate-error");
        }
        throw new Error(errorMessage);
      }

      const userData = await response.json();
      console.log("UserType:", userData.userType);

      sessionStorage.setItem(USER_TYPE, userData.userType);
      sessionStorage.setItem(USER_ID, userData.userID);
      sessionStorage.setItem("isLoggedIn", "true");
      sessionStorage.setItem("userDetails", JSON.stringify(userData));

      setIsLoggedIn(true);
      setUserDetails(userData);

      navigate("/dashboard");
    } catch (error) {
      setTimeout(() => setError(null), 5000);
    } finally {
      setIsLoading(false); // Hide loader after request processing
    }
  };

  return (
    <main className="login-container">
      {isLoading && <Loader />} {/* Show loader conditionally */}
      {error && (
        <div className="toast-box">
          <p>{error}</p>
        </div>
      )}
      <section className="card card-form">
        <div className="card-form-banner">
          <img src="./assets/banner/banner_login.webp" alt="Login Banner" />
        </div>
        <form className="card form-login" onSubmit={handleSubmit}>
          <h1 className="form-heading">Welcome!</h1>

          <input
            type="text"
            className="input input-box-form"
            placeholder="Username"
            autoFocus
            required
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
          <input
            type="password"
            className="input input-box-form"
            required
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />

          <button
            type="submit"
            className="btn btn--small btn--primary btn-signup"
          >
            Sign in
          </button>

          <div className="link-group">
            <span>
              Don't have an account yet?{" "}
              <Link to="/registration">
                <span className="li-signup">Sign up</span>
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
