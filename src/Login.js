import "./login.css";
import "./components/card.css";
export default function Login() {
  document.body.style.backgroundColor = "#333";
  return (
    <main className="login-container">
      <section className="card card-form">
        <div className="card-form-banner">
          <img src="./assets/banner/banner_login.webp" />
        </div>
        <form className="card form-login">
          <h1 className="form-heading">Welcome!</h1>

          <input
            type="text"
            className="input input-box-form"
            placeholder="Username"
            autoFocus
          />
          <input
            type="password"
            className="input input-box-form"
            placeholder="Password"
          />

          <button
            type="submit"
            className="btn btn--small btn--primary btn-signup"
          >
            Sign in
          </button>

          <div className="link-group">
            <span>
              Don't have an account yet?
              <span className="li-signup"> Sign up</span>
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
