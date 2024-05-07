import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import { GET_ALL_FLOORS_ENDPOINT } from "./api";
import "./adventuremode.css";

export const FLOOR_STORAGE_KEY = "selectedFloor"; // Define sessionStorage key for floor ID

export default function AdventureMode() {
  const [floors, setFloors] = useState([]);
  const [selectedFloor, setSelectedFloor] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    fetch(GET_ALL_FLOORS_ENDPOINT)
      .then((response) => response.json())
      .then((data) => setFloors(data))
      .catch((error) => console.error("Error fetching floors:", error));
  }, []);

  const handleFloorClick = (floor) => {
    setSelectedFloor(floor);
    sessionStorage.setItem(FLOOR_STORAGE_KEY, JSON.stringify(floor));
  };

  const handleEnterClick = () => {
    const selectedFloorData = JSON.parse(
      sessionStorage.getItem(FLOOR_STORAGE_KEY)
    );
    if (selectedFloorData) {
      console.log("Pinili ka:", selectedFloorData);
      navigate("/adventure_spelling", {});
    } else {
      console.error("Please select a floor before entering.");
    }
  };

  const renderProgressSections = () => {
    const uniqueSections = Array.from(
      new Set(floors.map((floor) => floor.towerSection))
    );
    return uniqueSections.map((section) => (
      <span
        key={section}
        onClick={() => handleFloorClick((section - 1) * 5 + 1)}
      >
        Section {section}
      </span>
    ));
  };

  const renderFloorCards = () => {
    const filteredFloors = floors.filter(
      (floor) => floor.towerSection === Math.ceil(selectedFloor / 5)
    );
    const startFloor = (Math.ceil(selectedFloor / 5) - 1) * 5 + 1;
    const endFloor = startFloor + 4;
    const reversedFloors = filteredFloors.reverse();
    return reversedFloors.map((floor, index) => (
      <div
        key={floor.towerFloorId}
        className={`floor-card ${
          selectedFloor === startFloor + index ? "selected" : ""
        }`}
        onClick={() => handleFloorClick(startFloor + index)}
      >
        Floor {startFloor + index}
      </div>
    ));
  };

  return (
    <main className="adventure-wrapper">
      <Link to="/dashboard">
        <button className="btn btn--large btn--danger--large btnBack">
          Back
        </button>
      </Link>
      <section className="floor-description">
        <div className="inner-box">
          <div className="desc-container">
            <p className="txt-floor">
              {selectedFloor ? `Floor ${selectedFloor}` : "Select a Floor"}
            </p>
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
            <button
              className="btn btn--small btn--primary btn-enter"
              onClick={handleEnterClick}
            >
              Enter
            </button>
          </div>
        </div>
      </section>
      <section className="tower-container">{renderFloorCards()}</section>
      <div className="progress">
        PROGRESS:
        {renderProgressSections()}
      </div>
    </main>
  );
}
