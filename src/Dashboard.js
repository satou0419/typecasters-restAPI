import { GameCard, ArchiveCard } from "./components/Card";
import "./components/card.css";
import "./dashboard.css";

export default function Dashboard() {
  return (
    <main className="dashboard-wrapper">
      <section className="game-card-wrapper">
        <GameCard
          title="Adventure"
          description="Lorem ipsum"
          bannerSrc="./assets/banner/banner_adventure.webp"
          counterHeader="Floors Completed"
          counterCount={1}
        />
        <GameCard
          title="Room"
          description="Lorem ipsum"
          bannerSrc="./assets/banner/banner_simulation.png"
          counterHeader="Rooms"
          counterCount={4}
          imageSize="90%" // Adjust the size here
        />
      </section>

      <ArchiveCard
        archive="Archive"
        description="Conquer the Towers! Collect Words and Badges in Adventure Mode"
        total_badge={4}
        total_words={4}
        bannerSrc="./assets/banner/banner_adventure.webp"
      />
    </main>
  );
}
