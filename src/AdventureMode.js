import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import {
  GET_ALL_FLOORS_ENDPOINT,
  GET_USER_DETAILS_ENDPOINT,
  fetchUserData,
} from "./api";
import "./adventuremode.css";

export const FLOOR_STORAGE_KEY = "selectedFloor"; // Define sessionStorage key for floor ID

export default function AdventureMode() {
  const [FLOOR_DATA, setFloorData] = useState([]);
  const [selectedFloor, setSelectedFloor] = useState(null);
  const [userDetails, setUserDetails] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    try {
      const storedUserDetailsString = sessionStorage.getItem("userDetails");
      if (storedUserDetailsString) {
        const storedUserDetails = JSON.parse(storedUserDetailsString);
        console.log("User ID: ", storedUserDetails.userID);

        // Fetch user details using userID
        fetch(`${GET_USER_DETAILS_ENDPOINT}${storedUserDetails.userID}`)
          .then((response) => {
            if (!response.ok) {
              throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json();
          })
          .then((data) => {
            console.log("User:", data);
            setUserDetails(data); // Store the fetched user details in the state variable

            // Set the selected floor and progress section if user progress data exists
            if (data.userProgress) {
              setSelectedFloor(data.userProgress.floorId);
              // Assuming towerSecProg corresponds to section in progress
              handleProgressClick(data.userProgress.towerSecProg);
            }
          })
          .catch((error) =>
            console.error("Error fetching user details:", error)
          );
      }
    } catch (error) {
      console.error("Error parsing user details:", error);
    }
  }, []);

  useEffect(() => {
    fetch(GET_ALL_FLOORS_ENDPOINT)
      .then((response) => response.json())
      .then((data) => {
        console.log("Fetched floors data:", data); // Log fetched data
        setFloorData(data);
      })
      .catch((error) => console.error("Error fetching floors:", error));
  }, []);

  console.log(FLOOR_DATA);

  const handleFloorClick = (floor) => {
    setSelectedFloor(floor);
    sessionStorage.setItem(FLOOR_STORAGE_KEY, JSON.stringify(floor));
  };

  const handleProgressClick = (section) => {
    // Calculate the start floor of the section
    const sectionFloors = FLOOR_DATA.filter(
      (floor) => floor.towerSection === section
    );
    if (sectionFloors.length > 0) {
      const startFloor = sectionFloors[0].sectionFloor;
      setSelectedFloor(startFloor);
      sessionStorage.setItem(FLOOR_STORAGE_KEY, JSON.stringify(startFloor));
    }
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
      new Set(FLOOR_DATA.map((floor) => floor.towerSection))
    );
    return uniqueSections.map((section) => (
      <span key={section} onClick={() => handleProgressClick(section)}>
        Section {section}
      </span>
    ));
  };

  const renderFloorCards = () => {
    const filteredFloors = FLOOR_DATA.filter(
      (floor) => floor.towerSection === Math.ceil(selectedFloor / 5)
    );
    const startFloor = (Math.ceil(selectedFloor / 5) - 1) * 5 + 1;
    const endFloor = startFloor + 4;
    const reversedFloors = filteredFloors.reverse();

    return reversedFloors.map((floor, index) => {
      const currentFloorIndex = startFloor + index;
      const isSelected = selectedFloor === currentFloorIndex;
      return (
        <div
          key={floor.towerFloorId}
          className={`floor-card ${isSelected ? "selected" : ""}`}
          onClick={() => handleFloorClick(currentFloorIndex)}
        >
          {isSelected ? (
            <div className="floor-card-active">Floor {currentFloorIndex}</div>
          ) : (
            `Floor ${currentFloorIndex}`
          )}
        </div>
      );
    });
  };

  return (
    <main className="adventure-wrapper">
      <section className="upper-adventure">
        <Link to="/dashboard">
          <button className="btn btn--large btn--danger--large btnBack">
            Back
          </button>
        </Link>
        <section className="reward-box">
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
        </section>
        <section className="tower-container">{renderFloorCards()}</section>
      </section>

      <section className="lower-adventure">
        <div className="progress">
          PROGRESS:
          {renderProgressSections()}
        </div>
      </section>
    </main>
  );
}
