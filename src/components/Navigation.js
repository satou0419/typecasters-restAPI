import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./navigation.css";
import { fetchUserData } from "../api";


export default function Navigation({ onLogout }) {
  const [menuOpen, setMenuOpen] = useState(false);
  const [profileOpen, setProfileOpen] = useState(false);
  const [userData, setUserData] = useState(null); // State to store user data
  const navigate = useNavigate();
  const storedUserDetails = JSON.parse(sessionStorage.getItem("userDetails")); // Define storedUserDetails

  useEffect(() => {
    // Fetch user data when component mounts
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
    setMenuOpen(!menuOpen);
  };

  const toggleProfile = () => {
    setProfileOpen(!profileOpen);
  };

  return (
    <section>
      <nav className={`nav-bar ${menuOpen ? "menu-open" : ""}`}>
      
        <div className="nav-bar__logo">
          <img src="./assets/logo/logo_simple.webp" alt="Logo" />
        </div>
      
        <div className="menu-icon" onClick={toggleMenu}>
          <span className="bar"></span>
          <span className="bar"></span>
          <span className="bar"></span>
        </div>
        <div className={`menu ${menuOpen ? "open" : "close"}`}>
          <Link to="/adventure_mode">Adventure Mode</Link>
          <Link to="/simulation_mode">Simulation Mode</Link>
          <Link to="/arhive">Archive</Link>
          <Link to="/about">About</Link>
          <Link to="/inventory_shop">
            <img src="./assets/icon/ic_currency.webp" className="ic-img" />
          </Link>

          <Link to="/inventory_shop">
            <img src="./assets/icon/ic_currency.webp" className="ic-img" />
          </Link>
        </div>
        <div className="profile-icon" onClick={toggleProfile}>
          <span className="circle"></span>
        </div>
        <div className={`profile ${profileOpen ? "open" : "close"}`}>
          <Link onClick={onLogout}>Logout</Link>
        </div>
        {userData && (
          <>
            <p>Username: {storedUserDetails.username}</p>
            <p>Credit: {userData.credit_amount}</p>
          </>
        )}
      </nav>
    </section>
  );
}
