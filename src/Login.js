import "./components/styles.css";

export default function Login() {
  document.body.style.backgroundColor = "#333";
  return (
    <main className="login-section">
      <section className="login-form">
        <aside className="banner-sidebar">
          <img src="./images/login--banner.png" alt="Login Banner" />
        </aside>

        <aside className="login-sidebar">
          <section className="card">
            <h2 className="card__header">Welcome!</h2>

            <section className="input-section">
              <input
                type="text"
                className="input input__card"
                id="username"
                placeholder="Username"
                autoFocus
              />

              <input
                type="password"
                className="input input__card"
                id="password"
                placeholder="Password"
              />

              <button className="btn">Sign In</button>

              <span>
                Don't have an account yet?<span>Sign up</span>
              </span>

              <span>
                <span>Terms & Conditions | </span>
                <span>Privacy Policy | </span>
                <span>Support</span>
              </span>
            </section>
          </section>
        </aside>
      </section>
    </main>
  );
}
