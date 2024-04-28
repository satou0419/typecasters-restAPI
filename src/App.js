import React, { useState, useEffect } from "react";
import { Route, Routes, Navigate, useLocation } from "react-router-dom";
import Login from "./Login";
import Navigation from "./components/Navigation";
import Dashboard from "./Dashboard";
import Registration from "./Registration";
import StudentRoom from "./StudentRoom";
import Archive from "./Archive";
import InventoryShop from "./InventoryShop";
import GameplayAdventureSpelling from "./GameplayAdventureSpelling";

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(true);
  const location = useLocation(); // Get the current location object

  useEffect(() => {
    // Check authentication status from sessionStorage when component mounts
    const storedIsLoggedIn = sessionStorage.getItem("isLoggedIn");
    if (storedIsLoggedIn === "true") {
      setIsLoggedIn(true);
    }
  }, []);

  // Conditionally render Navigation based on the current route and login status
  const showNavigation =
    isLoggedIn && location.pathname !== "/adventure_spelling";

  return (
    <>
      {showNavigation && <Navigation />}

      <Routes>
        {isLoggedIn ? (
          <Route path="/login" element={<Navigate to="/dashboard" />} />
        ) : (
          <Route path="/" element={<Login setIsLoggedIn={setIsLoggedIn} />} />
        )}
        {isLoggedIn ? (
          <Route path="/registration" element={<Navigate to="/dashboard" />} />
        ) : (
          <Route
            path="/registration"
            element={<Registration setIsLoggedIn={setIsLoggedIn} />}
          />
        )}
        <Route
          path="/adventure_spelling"
          element={<GameplayAdventureSpelling />}
        />

        {isLoggedIn && (
          <>
            <Route path="/dashboard" element={<Dashboard />} />
            <Route path="/student_room" element={<StudentRoom />} />
            <Route path="/archive" element={<Archive />} />
            <Route path="/inventory_shop" element={<InventoryShop />} />
          </>
        )}
      </Routes>
    </>
  );
}

export default App;
