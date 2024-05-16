import "./joinroom.css";
import { Link } from "react-router-dom";
export default function CreateRoom() {
  return (
    <main className="joinroom-container">
      <section className="card createroom-card joinroom-card">
        <img src="/assets/background/join_room_background.webp" />
        <h1 className="createroom-card__heading">Join Room</h1>
        <input
          type="text"
          className="input input-line input-line--dark join-input"
          placeholder="Enter Room Name"
        />
        <Link to="/student/simulation_mode">
          <button className="btn btn--large btn--primary join-btn">
            JOIN NOW
          </button>
        </Link>
        <Link to="/student/simulation_mode">
          <button className=">btn btn--large btn--danger--large cancel-btn">
            CANCEL
          </button>
        </Link>
      </section>
    </main>
  );
}
