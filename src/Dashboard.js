import "./components/card.css";
import "./dashboard.css";

import { Link } from "react-router-dom";
import { CardGame, CardArchive } from "./components/Card";
export default function Dashboard() {
  return (
    <main className="dashboard-wrapper">
      <section className="game-card-wrapper">
        <Link to="/adventure_mode">
          <CardGame
            title="Adventure"
            content="Lorem ipsum dolor sit amet, consectetur adipiscing"
            progressTitle="Floor Completed"
            progressValue={4}
            imageSrc="./assets/banner/banner_adventure.webp"
          />
        </Link>
        <Link to="/simulation_mode">
        <CardGame
          title="Room"
          content="Lorem ipsum dolor sit amet, consectetur adipiscing"
          progressTitle="Room"
          progressValue={4}
          imageSrc="./assets/banner/banner_simulation.png"
        />
        </Link>
      </section>

      <Link to="/archive">
        <CardArchive
          title="Archive"
          content="Conquer the Towers! Collect Words and Badges in Adventure Mode"
          bannerSrc="./assets/banner/banner_adventure.webp"
          badgeProgress="4"
          wordProgress="4"
        />
      </Link>
    </main>
  );
}
