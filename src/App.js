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

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(true);
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
    <>
      {showNavigation && <Navigation onLogout={handleLogout} />}

      <Routes>
        {isLoggedIn ? (
          <Route path="/login" element={<Navigate to="/dashboard" />} />
        ) : (
          <Route
            path="/login"
            element={<Login setIsLoggedIn={setIsLoggedIn} />}
          />
        )}
        {isLoggedIn ? (
          <Route path="/registration" element={<Navigate to="/dashboard" />} />
        ) : (
          <Route
            path="/registration"
            element={<Registration setIsLoggedIn={setIsLoggedIn} />}
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
            <Route path="/dashboard" element={<Dashboard />} />
            <Route path="/student_room" element={<StudentRoom />} />
            <Route path="/archive" element={<Archive />} />
            <Route path="/inventory_shop" element={<InventoryShop />} />
            <Route path="/create_room" element={<CreateRoom />} />
            <Route path="/adventure_mode" element={<AdventureMode />} />
          </>
        )}
      </Routes>
    </>
  );
}

export default App;
