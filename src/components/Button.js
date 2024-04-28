import "./button.css";

export default function Button() {
  return (
    <section className="btn-container">
      <div className="btn-group">
        <label>btn btn--small btn--danger</label>
        <button className="btn btn--small btn--danger">Delete</button>
      </div>

      <div className="btn-group">
        <label>btn btn--small btn--primary</label>
        <button className="btn btn--small btn--primary">Confirm</button>
      </div>

      <div className="btn-group">
        <label>btn btn--medium btn--primary</label>
        <button className="btn btn--medium btn--primary">Save Changes</button>
      </div>

      <div className="btn-group">
        <label>btn btn--large btn--danger--large</label>
        <button className="btn btn--large btn--danger--large">Cancel</button>
      </div>

      <div className="btn-group">
        <label>btn btn--large btn--primary</label>
        <button className="btn btn--large btn--primary">Join Now</button>
      </div>

      <div className="btn-group">
        <label>btn btn--large btn--primary</label>
        <button className="btn btn-item">150</button>
      </div>
    </section>
  );
}
