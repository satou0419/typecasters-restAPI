export default function Registration() {
  return (
    <main className="registration">
      <section className="registration__container">
        <aside className="registration__banner"></aside>
        <form className="registration__form">
          <h1 className="registration__heading">Create Account</h1>
          <section className="registration__input-field">
            <input
              type="text"
              className="registration__input"
              placeholder="Firstname"
              autoFocus
            />
            <input
              type="text"
              className="registration__input"
              placeholder="Lastname"
            />
            <input
              type="text"
              className="registration__input"
              placeholder="Username"
            />
            <input
              type="password"
              className="registration__input"
              placeholder="Password"
            />
            <input
              type="password"
              className="registration__input"
              placeholder="Confirm Password"
            />
            <input
              type="email"
              className="registration__input"
              placeholder="Email"
            />
            <label htmlFor="usertype" className="visually-hidden">
              User Type
            </label>
            <select
              id="usertype"
              className="registration__input registration__input--select"
              placeholder="Usertype"
            >
              <option value="default" disabled hidden>
                Select User Type
              </option>
              <option value="student">Student</option>
              <option value="teacher">Teacher</option>
            </select>
          </section>
          <button type="submit" className="registration__button">
            Sign Up
          </button>
        </form>
      </section>
    </main>
  );
}
