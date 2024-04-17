import React, { useState } from "react";
import "./login.css";

export default function Login() {
  document.body.style.backgroundColor = "#333";

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(null);

  const handleSubmit = async (event) => {
    event.preventDefault();

    try {
      const response = await fetch("http://localhost:8080/user/login", {
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
        throw new Error("Login failed");
      }

      console.log("Login successful!");
    } catch (error) {
      setError("Invalid username or password");
      console.error(error);
    }
  };

  return (
    <main className="login-container">
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
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
          <input
            type="password"
            className="input input-box-form"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />

          {error && <div className="error-message">{error}</div>}

          <button
            type="submit"
            className="btn btn--small btn--primary btn-signup"
          >
            Sign in
          </button>

          <div className="link-group">
            <span>
              Don't have an account yet?{" "}
              <span className="li-signup">Sign up</span>
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
