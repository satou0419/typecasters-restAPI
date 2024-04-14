import "./input.css";

export default function Input() {
  return (
    <section className="input-container">
      <label>input input-box</label>
      <input type="text" className="input input-box" placeholder="Username" />

      <label>input input-line input-line--dark</label>
      <input
        type="text"
        className="input input-line input-line--dark"
        placeholder="Enter Room Name"
      />

      <label>input input-line input-line--light</label>
      <input
        type="text"
        className="input input-line input-line--light"
        placeholder="New Password"
      />
    </section>
  );
}
