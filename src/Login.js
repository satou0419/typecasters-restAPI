import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import DialogBox from "./components/DialogBox";
import "./components/input.css";
import "./login.css";

export default function Login({ setIsLoggedIn }) {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(null);
  const [showDialog, setShowDialog] = useState(false); // State to manage dialog visibility
  const [dialogContent, setDialogContent] = useState({
    title: "",
    content: "",
    buttons: [],
  });

  const navigate = useNavigate();

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

      // Set authentication status to true and store it in session storage
      sessionStorage.setItem("isLoggedIn", "true");
      setIsLoggedIn(true); // Update the loggedIn state
      navigate("/dashboard"); // Redirect to the dashboard

      console.log("Login successful!");
    } catch (error) {
      openDialog("Error", "Invalid username or password", [
        { label: "Retry", onClick: closeDialog },
      ]);
      setError("Invalid username or password");
      console.error(error);
    }
  };

  // Function to open the dialog box
  const openDialog = (title, content, buttons) => {
    setDialogContent({ title, content, buttons });
    setShowDialog(true);
  };

  // Function to close the dialog box
  const closeDialog = () => {
    setShowDialog(false);
  };

  return (
    <main className="login-container">
      {/* Overlay */}
      {showDialog && <div className="overlay" />}

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

      {/* DialogBox component */}
      {showDialog && (
        <DialogBox
          title={dialogContent.title}
          content={dialogContent.content}
          buttons={dialogContent.buttons}
        />
      )}
    </main>
  );
}
