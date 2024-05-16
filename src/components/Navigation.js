import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./navigation.css";
import { fetchUserData } from "../api";
import { useCredit } from "../CreditContext"; // Import useCredit hook to access credit
import { USER_TYPE } from "../Login";

export default function Navigation({ onLogout }) {
  const [menuOpen, setMenuOpen] = useState(false);
  const [profileOpen, setProfileOpen] = useState(false);
  const [userData, setUserData] = useState(null);
  const navigate = useNavigate();
  const storedUserDetails = JSON.parse(sessionStorage.getItem("userDetails"));
  const { credit } = useCredit(); // Access credit from the context
  const [userType, setUserType] = useState(sessionStorage.getItem(USER_TYPE));

  console.log(userType);

  useEffect(() => {
    const storedUserDetails = JSON.parse(sessionStorage.getItem("userDetails"));
    if (storedUserDetails) {
      fetchUserData(storedUserDetails.userID)
        .then((data) => {
          setUserData(data);
        })
        .catch((error) => console.error("Error fetching user data:", error));
    }
  }, []);

  const toggleMenu = () => {
    setMenuOpen((prevMenuOpen) => !prevMenuOpen);
  };

  const toggleProfile = () => {
    setProfileOpen(!profileOpen);
  };

  return (
    <section>
      <nav className={`nav-bar ${menuOpen ? "menu-open" : ""}`}>
        <div className="nav-bar__logo">
          <img src="/assets/logo/logo_simple.webp" alt="Logo" />
        </div>
        <div
          className={`menu-icon ${menuOpen ? "active" : ""}`}
          onClick={toggleMenu}
        >
          <span className="bar"></span>
          <span className="bar"></span>
          <span className="bar"></span>
        </div>
        <div className={`menu ${menuOpen ? "open" : ""}`}>
          <Link to="/adventure_mode">Adventure Mode</Link>
          <Link to={`${userType}/simulation_mode`}>Simulation Mode</Link>
          <Link to="/archive">Archive</Link>
          <Link to="/about">About</Link>
          <Link to="/inventory_shop">Inventory</Link>
          <Link to="/Settings">Settings</Link>
          <Link onClick={onLogout}>Logout</Link>
        </div>
        <div className="profile-container">
          <div className="profile-icon" onClick={toggleProfile}>
            <span className="circle"></span>
          </div>
          <div className={`profile ${profileOpen ? "open" : ""}`}></div>
        </div>
        {userData && (
          <>
            <p>Username: {storedUserDetails.username}</p>
            <p>Credit: {credit}</p> {/* Display credit from the context */}
          </>
        )}
      </nav>
    </section>
  );
}
