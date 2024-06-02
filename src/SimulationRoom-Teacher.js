import { Link } from "react-router-dom";
import "./simulationroom.css";
import "./components/button.css";
import { CardSimulation, CardArchive, CardCreate } from "./components/Card";

import { ROOM_ID, ROOM_CODE, ROOM_NAME } from "./SimulationMode-Teacher";
import { VIEW_GAME_BY_ROOM } from "./api";
import { useEffect, useState } from "react";

export default function SimulationRoomTeacher() {
  const roomID = sessionStorage.getItem(ROOM_ID);
  const roomCode = sessionStorage.getItem(ROOM_CODE);
  const roomName = sessionStorage.getItem(ROOM_NAME);
  const [gameData, setGameData] = useState([]);

  useEffect(() => {
    fetch(`${VIEW_GAME_BY_ROOM}${roomID}`)
      .then((response) => response.json())
      .then((data) => {
        setGameData(data);
        console.log("ROOOOOM:::::", data);
      })
      .catch((error) => console.error("Error fetching floors:", error));
  }, []);

  return (
    <main className="simulation-wrapper">
      <Link to="/teacher/room_info">
        <button className="btn btn--small btn--primary btn-info">
          Room Information
        </button>
      </Link>

      <div className="txt-Rooms">{`Simulation - =${roomName}==${roomCode}`}</div>

      <div className="teacher-card-wrapper">
        <section className="simulation-card-wrapper">
          {gameData.map((game, index) => (
            <Link to="/teacher/room_settings">
              <CardSimulation
                key={index}
                className="card card-simulation"
                title={game.name}
                bannerSrc={
                  game.bannerSrc || "/assets/banner/banner_adventure.webp"
                }
                progressTitle="Students Done"
                progressValue={game.participants ? game.participants.length : 0}
              />
            </Link>
          ))}
          <Link to="/teacher/create_game">
            <CardCreate title="+Create Game" />
          </Link>
        </section>
      </div>
    </main>
  );
}