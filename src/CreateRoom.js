import "./createroom.css";
import { Link } from "react-router-dom";
export default function CreateRoom() {
  return (
    <main className="createroom-container">
      <section className="card createroom-card">
        <h1 className="createroom-card__heading">Create Room</h1>
        <input
          type="text"
          className="input input-line input-line--dark"
          placeholder="Enter Room Name"
        />
        <Link to="/simulation_mode">
        <button className="btn btn--large btn--primary">CREATE</button>
        </Link>
        <Link to="/simulation_mode">
        <button className=">btn btn--large btn--danger--large">CANCEL</button>
         </Link>
      </section>
    </main>
  );
}