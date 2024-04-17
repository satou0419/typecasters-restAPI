import "./components/card.css";
import "./dashboard.css";

import { CardGame, CardArchive } from "./components/Card";
export default function Dashboard() {
  return (
    <main className="dashboard-wrapper">
      <section className="game-card-wrapper">
        <CardGame
          title="Adventure"
          content="Lorem ipsum dolor sit amet, consectetur adipiscing"
          progressTitle="Floor Completed"
          progressValue={4}
          imageSrc="./assets/banner/banner_adventure.webp"
        />

        <CardGame
          title="Room"
          content="Lorem ipsum dolor sit amet, consectetur adipiscing"
          progressTitle="Room"
          progressValue={4}
          imageSrc="./assets/banner/banner_simulation.png"
        />
      </section>

      <CardArchive
        title="Archive"
        content="Conquer the Towers! Collect Words and Badges in Adventure Mode"
        bannerSrc="./assets/banner/banner_adventure.webp"
        badgeProgress="4"
        wordProgress="4"
      />
    </main>
  );
}
