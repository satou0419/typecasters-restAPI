import { Link } from "react-router-dom";
import "./simulationmode.css";
import { CardSimulation, CardCreate } from "./components/Card"; // Removed CardArchive since it's not used
import { useState, useEffect } from "react";
import { USER_ID } from "./Login";
import { VIEW_ROOM } from "./api";

export default function SimulationModeTeacher() {
  const [creatorID] = useState(sessionStorage.getItem(USER_ID));
  const [rooms, setRooms] = useState([]);

  useEffect(() => {
    if (creatorID) {
      fetch(
        `${VIEW_ROOM}/${creatorID}`
      )
        .then((response) => response.json())
        .then((data) => setRooms(data))
        .catch((error) => console.error("Error fetching room data:", error));
    }
  }, [creatorID]); // Fixed the dependency array

  return (
    <main className="simulation-wrapper">
      <div className="txt-Rooms">Rooms</div>
      <section className="game-card-wrapper">
        {rooms.map((room) => (
          <Link to="/teacher/simulation_room" key={room.roomID}>
            <CardSimulation
              className="card card-simulation"
              title={room.roomName}
              content="Teacher" // Assuming this is static as not provided in JSON
              bannerSrc="/assets/banner/banner_adventure.webp" // Assuming this is static as not provided in JSON
              progressTitle="Students"
              progressValue={room.members.length.toString()} // Convert number of members to string
              // onClick={handleCardSimulationClick} // Uncomment and implement if needed
            />
          </Link>
        ))}
        <Link to="/teacher/create_room">
          <CardCreate title="+Create Room" />
        </Link>
      </section>
    </main>
  );
}
