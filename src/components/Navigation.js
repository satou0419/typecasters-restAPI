import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./navigation.css";


export default function Navigation({ onLogout }) {
  const [menuOpen, setMenuOpen] = useState(false);
  const [profileOpen, setProfileOpen] = useState(false);
  const navigate = useNavigate();

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
          <Link to="/inventory_shop">Inventory</Link>
        </div>
        <div className="profile-icon" onClick={toggleProfile}>
          <span className="circle"></span>
        </div>
        <div className={`profile ${profileOpen ? "open" : "close"}`}>
          <Link onClick={onLogout}>Logout</Link>
        </div>
      </nav>
    </section>
  );
}
