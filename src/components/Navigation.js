import React, { useState } from "react";
import { Link } from "react-router-dom";
import "./navigation.css";

export default function Navigation({ onLogout }) {
  const [menuOpen, setMenuOpen] = useState(false);
  const [profileOpen, setProfileOpen] = useState(false);

  const toggleMenu = () => {
    // setMenuOpen(!menuOpen);
    setMenuOpen(prevMenuOpen => !prevMenuOpen);
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
        <div className={`menu-icon ${menuOpen ? "active" : ""}`} onClick={toggleMenu}>
          <span className="bar"></span>
          <span className="bar"></span>
          <span className="bar"></span>
        </div>
        <div className={`menu ${menuOpen ? "open" : ""}`}>
          <Link to="/adventure_mode">Adventure Mode</Link>
          <Link to="/simulation_mode">Simulation Mode</Link>
          <Link to="/archive">Archive</Link>
          <Link to="/about">About</Link>
          <Link to="/inventory_shop">Inventory</Link>
          <Link to="/Settings">Settings</Link>
        </div>
        <div className="profile-container">
          <div className="profile-icon" onClick={toggleProfile}>
            <span className="circle"></span>
          </div>
          <div className={`profile ${profileOpen ? "open" : ""}`}>
            <Link onClick={onLogout}>Logout</Link>
          </div>
        </div>
      </nav>
    </section>
  );
}
