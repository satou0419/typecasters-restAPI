import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import { GET_ALL_FLOORS_ENDPOINT, GET_USER_DETAILS_ENDPOINT } from "./api";
import "./adventuremode.css";

export const FLOOR_ID = "selectedFloor"; // Define sessionStorage key for floor ID
export const SECTION_PROGRESS = "sectionProgress";
export const PROGRESS_ID = "progressID";
export const NEXT_FLOOR = "nextFloor";
export const NEXT_SECTION = "nextSection";
export const CURRENT_FLOOR = "currentFloor";
export const CURRENT_SECTION = "currentSection";

export default function AdventureMode() {
  const [FLOOR_DATA, setFloorData] = useState([]);
  const [selectedFloor, setSelectedFloor] = useState(null);
  const [selectedSection, setSelectedSection] = useState(null);
  const [progressID, setProgressID] = useState(null); // Changed from array to null
  const [userDetails, setUserDetails] = useState(null);
  const [nextFloor, setNextFloor] = useState();
  const [nextSection, setNextSection] = useState();
  const [currentFloor, setCurrentFloor] = useState();
  const [currentSection, setCurrentSection] = useState();
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

            // Set the selected floor if user progress data exists
            if (data.userProgress) {
              setSelectedFloor(data.userProgress.floorId);
              setCurrentFloor(data.userProgress.floorId);
              sessionStorage.setItem(CURRENT_FLOOR, data.userProgress.floorId);

              setSelectedSection(data.userProgress.towerSecProg);

              setCurrentSection(data.userProgress.towerSecProg);
              sessionStorage.setItem(
                CURRENT_SECTION,
                data.userProgress.towerSecProg
              );

              setProgressID(data.userProgress.userProgId); // Set the progressID state
              sessionStorage.setItem(PROGRESS_ID, data.userProgress.userProgId); // Save progressID in sessionStorage
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

  useEffect(() => {
    getNextFloorAndSection(); // Log the next floor and section when the component mounts
  }); // This effect runs only once when the component mounts

  const getNextFloorAndSection = () => {
    if (!userDetails || !FLOOR_DATA.length) {
      console.error("User details or floor data not available.");
      return;
    }

    const userFloorId = userDetails.userProgress?.floorId;
    const userSection = userDetails.userProgress?.towerSecProg;

    if (!userFloorId || !userSection) {
      console.error("User progress data incomplete.");
      return;
    }

    const userFloorIndex = FLOOR_DATA.findIndex(
      (floor) => floor.towerFloorId === userFloorId
    );
    const nextFloorIndex = userFloorIndex + 1;
    let nextFloor, nextSection;

    if (nextFloorIndex < FLOOR_DATA.length) {
      nextFloor = FLOOR_DATA[nextFloorIndex].towerFloorId;
      nextSection = FLOOR_DATA[nextFloorIndex].towerSection;
    } else {
      console.log("User has completed all floors.");
      return;
    }

    setNextFloor(nextFloor);
    setNextSection(nextSection);

    sessionStorage.setItem(NEXT_FLOOR, nextFloor);
    sessionStorage.setItem(NEXT_SECTION, nextSection);
  };

  console.log("Next Floor :::::> ", nextFloor);
  console.log("Next Section :::::> ", nextSection);

  const handleFloorClick = (floor) => {
    setSelectedFloor(floor);
    sessionStorage.setItem(FLOOR_ID, JSON.stringify(floor));
    console.log(`Selected floor: ${floor}`);
  };

  console.log("Current: ", selectedFloor);
  const handleSectionClick = (section) => {
    setSelectedSection(section);
    sessionStorage.setItem(SECTION_PROGRESS, JSON.stringify(section));
    // Find the first floor of the clicked section and set it as the selected floor
    const firstFloorInSection = FLOOR_DATA.find(
      (floor) => floor.towerSection === section
    );
    if (firstFloorInSection) {
      setSelectedFloor(firstFloorInSection.towerFloorId);
      sessionStorage.setItem(
        FLOOR_ID,
        JSON.stringify(firstFloorInSection.towerFloorId)
      );
    }
  };

  const handleEnterClick = () => {
    // const selectedFloorData = JSON.parse(sessionStorage.getItem(FLOOR_ID));
    // const selectedSectionData = JSON.parse(
    //   sessionStorage.getItem(SECTION_PROGRESS)
    // );
    // const progressIDData = sessionStorage.getItem(PROGRESS_ID);

    // if (selectedFloorData && selectedSectionData && progressIDData) {
    //   console.log("Selected floor:", selectedFloorData);
    navigate("/adventure_spelling", {});
    // } else {
    //   console.error("Please select a floor before entering.");
    // }
  };

  const renderProgressSections = () => {
    const uniqueSections = Array.from(
      new Set(FLOOR_DATA.map((floor) => floor.towerSection))
    );

    return uniqueSections.map((section) => (
      <span key={section} onClick={() => handleSectionClick(section)}>
        Section {section}
      </span>
    ));
  };

  const renderFloorCards = () => {
    if (selectedSection === null) {
      return null;
    }

    const sectionFloors = FLOOR_DATA.filter(
      (floor) => floor.towerSection === selectedSection
    );

    return sectionFloors.map((floor) => (
      <div
        key={floor.towerFloorId}
        className={`floor-card ${selectedFloor ? "selected" : ""}`}
        onClick={() => handleFloorClick(floor.towerFloorId)}
      >
        <div
          className={
            selectedFloor === floor.towerFloorId
              ? "floor-card-active"
              : "floor-card"
          }
        >
          Floor {floor.towerFloorId}
        </div>
      </div>
    ));
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
