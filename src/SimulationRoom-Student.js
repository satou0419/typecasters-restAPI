import { Link } from "react-router-dom";
import "./simulationroom.css";
import "./components/button.css";
import { CardSimulation, CardArchive, CardCreate } from "./components/Card";

export default function AdventureMode() {
  return (
    <main className="Simulation-wrapper">
      <div className="txt-Rooms">Simulation - =RoomName==RoomCode=</div>
      <section className="game-card-wrapper">
        <CardSimulation
          className="card card-simulation"
          title="Game Name"
          // content="Teacher"
          bannerSrc="./assets/banner/banner_adventure.webp"
          progressTitle="Students Done"
          progressValue="1"
          // onClick={handleCardSimulationClick}
        />
        <CardSimulation
          className="card card-simulation game"
          title="Game Name"
          // content="Teacher"
          bannerSrc="./assets/banner/banner_adventure.webp"
          progressTitle="Students Done"
          progressValue="4"
          // onClick={handleCardSimulationClick}
        />
      </section>
    </main>
  );
}
