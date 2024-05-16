import { Link } from "react-router-dom";
import "./simulationmode.css";
import { CardSimulation, CardArchive, CardCreate } from "./components/Card";
export default function SimulationModeTeacher() {
  return (
    <main className="Simulation-wrapper">
      <div className="txt-Rooms">Rooms</div>
      <section className="game-card-wrapper">
        <Link to="/teacher/simulation_room">
          <CardSimulation
            className="card card-simulation"
            title="Class Name"
            content="Teacher"
            bannerSrc="/assets/banner/banner_adventure.webp"
            progressTitle="Students"
            progressValue="4"
            // onClick={handleCardSimulationClick}
          />
        </Link>
        <Link to="/teacher/simulation_room">
          <CardSimulation
            className="card card-simulation game"
            title="Class Name"
            content="Teacher"
            bannerSrc="/assets/banner/banner_adventure.webp"
            progressTitle="Students"
            progressValue="4"
            // onClick={handleCardSimulationClick}
          />
        </Link>
        <Link to="/teacher/create_room">
          <CardCreate title="+Create Room" />
        </Link>
      </section>
    </main>
  );
}
