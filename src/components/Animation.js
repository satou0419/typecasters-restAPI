import "./button.css";

export default function Animation() {
  return (
    <section className="animation-container">
      <label>idle</label>
      <div className="idle"></div>

      <label>attack</label>
      <div className="attack"></div>

      <label>explode</label>
      <div className="explode"></div>
    </section>
  );
}
