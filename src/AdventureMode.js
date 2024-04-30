import { Link } from "react-router-dom";
import "./adventuremode.css";

export default function AdventureMode() {
  return (
    <main className="adventure-wrapper">
      <section className="tower-container">
        <Link to="/adventure_spelling">
          <div className="floor-card">Floor 1</div>
        </Link>
        <div className="floor-card">Floor 2</div>
        <div className="floor-card">Floor 3</div>
        <div className="floor-card">Floor 4</div>
        <div className="floor-card">Floor 5</div>
      </section>
    </main>
  );
}
