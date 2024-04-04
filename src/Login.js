import "./styles.css";

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
              />

              <input
                type="password"
                className="input input__card"
                id="password"
                placeholder="Password"
              />
            </section>
          </section>
        </aside>
      </section>
    </main>
  );
}
