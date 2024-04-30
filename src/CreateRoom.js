import "./createroom.css";

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

        <button className="btn btn--large btn--primary">CREATE</button>
        <button className=">btn btn--large btn--danger--large">CANCEL</button>
      </section>
    </main>
  );
}
