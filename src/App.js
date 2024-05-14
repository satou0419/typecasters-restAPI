import React, { useState, useEffect } from "react";
import {
  Route,
  Routes,
  Navigate,
  useLocation,
  useNavigate,
} from "react-router-dom";
import Login from "./Login";
import Navigation from "./components/Navigation";
import Dashboard from "./Dashboard";
import Registration from "./Registration";
import StudentRoom from "./StudentRoom";
import Archive from "./Archive";
import InventoryShop from "./InventoryShop";
import GameplayAdventureSpelling from "./GameplayAdventureSpelling";
import { LOGOUT_ENDPOINT } from "./api";
import CreateRoom from "./CreateRoom";
import AdventureMode from "./AdventureMode";
import SimulationMode from "./SimulationMode-Teacher";
import SimulationRoom from "./SimulationRoom-Teacher";
import CreateGame from "./CreateGame";
import SimulationModeStudent from "./SimulationMode-Student";
import SimulationRoomStudent from "./SimulationRoom-Student";
import JoinRoom from "./JoinRoom";
import TeacherRoomSettings from "./TeacherRoomSettings";
import TeacherRoomInfo from "./TeacherRoomInfo";
import Settings from "./Settings";
import { CreditProvider } from "./CreditContext";

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [userDetails, setUserDetails] = useState(null); // State to store user details
  const location = useLocation();
  const navigate = useNavigate();

  useEffect(() => {
    const storedIsLoggedIn = sessionStorage.getItem("isLoggedIn");
    if (storedIsLoggedIn === "true") {
      setIsLoggedIn(true);
    }
  }, []);

  const handleLogout = async () => {
    try {
      const response = await fetch(LOGOUT_ENDPOINT, {
        method: "POST",
        credentials: "same-origin",
      });

      if (response.ok) {
        setIsLoggedIn(false);
        setUserDetails(null); // Clear user details on logout
        sessionStorage.removeItem("isLoggedIn");
        navigate("/login");
      } else {
        console.error("Logout failed");
      }
    } catch (error) {
      console.error("Error:", error);
    }
  };

  const showNavigation =
    isLoggedIn && location.pathname !== "/adventure_spelling";
  const showAdventureSpelling =
    isLoggedIn || location.pathname !== "/adventure_spelling";

  return (
    <CreditProvider>
      <>
        {showNavigation && <Navigation onLogout={handleLogout} />}

        <Routes>
          {isLoggedIn ? (
            <Route path="/" element={<Navigate to="/dashboard" />} />
          ) : (
            <Route path="/" element={<Navigate to="/login" />} />
          )}
          {isLoggedIn ? (
            <Route path="/login" element={<Navigate to="/dashboard" />} />
          ) : (
            <Route
              path="/login"
              element={
                <Login
                  setIsLoggedIn={setIsLoggedIn}
                  setUserDetails={setUserDetails}
                />
              }
            />
          )}
          {isLoggedIn ? (
            <Route
              path="/registration"
              element={<Navigate to="/dashboard" />}
            />
          ) : (
            <Route
              path="/registration"
              element={
                <Registration
                  setIsLoggedIn={setIsLoggedIn}
                  setUserDetails={setUserDetails}
                />
              }
            />
          )}
          {showAdventureSpelling && (
            <Route
              path="/adventure_spelling"
              element={<GameplayAdventureSpelling />}
            />
          )}
          {isLoggedIn && (
            <>
              <Route
                path="/dashboard"
                element={<Dashboard userDetails={userDetails} />}
              />
              <Route path="/student_room" element={<StudentRoom />} />
              <Route path="/archive" element={<Archive />} />
              <Route path="/inventory_shop" element={<InventoryShop />} />
              <Route path="/create_room" element={<CreateRoom />} />
              <Route path="/adventure_mode" element={<AdventureMode />} />
              <Route path="/simulation_mode" element={<SimulationMode />} />
              <Route path="/simulation_room" element={<SimulationRoom />} />
              <Route path="/create_game" element={<CreateGame />} />
              <Route
                path="/simulation_mode_student"
                element={<SimulationModeStudent />}
              />
              <Route
                path="/simulation_room_student"
                element={<SimulationRoomStudent />}
              />
              <Route path="/join_room" element={<JoinRoom />} />
              <Route
                path="/teacher_room_settings"
                element={<TeacherRoomSettings />}
              />
              <Route path="/teacher_room_info" element={<TeacherRoomInfo />} />
              <Route path="/Settings" element={<Settings />} />
            </>
          )}
        </Routes>
      </>
    </CreditProvider>
  );
}

export default App;
