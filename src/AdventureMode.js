import { Link } from "react-router-dom";
import "./adventuremode.css";
export default function AdventureMode() {
  return (
    
    <main className="adventure-wrapper">
      <Link to="/dashboard">
      <button className="btn btn--large btn--danger--large btnBack">Back</button>
       </Link>
     {/* below is for the floor description */}
      <section className="floor-description"> 

        <div className="inner-box"></div>
        <div className="desc-container">
          <p className="txt-floor">Floor 1</p>
          <p className="txt-rewards">Rewards:</p>

          <div className="items-container">
            <div className="reward-item">
             <div className="reward-item-container"></div>
            </div>
            <div className="reward-item">
              <div className="reward-item-container"></div>
            </div>
            <div className="reward-item">
              <div className="reward-item-container"></div>
            </div>
          </div>  
          <Link to="/adventure_spelling">
          <button className="btn btn--small btn--primary btn-enter">Enter</button>
          </Link>
        </div>
      </section>
      {/* end of floor description */}
      <section className="tower-container">

        <div className="floor-card">Floor 5</div>
        <div className="floor-card">Floor 4</div>
        <div className="floor-card">Floor 3</div>
        <div className="floor-card">Floor 2</div>

        <Link to="/adventure_spelling">
        <div className="floor-card">Floor 1</div>
        </Link>

      </section>
      
      <div className="border-progress"></div>
      <div className="progress">PROGRESS: </div>
      <div className="progress-bar"></div>
    </main>
  );
}
