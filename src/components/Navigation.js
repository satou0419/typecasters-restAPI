import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./navigation.css";
import { fetchUserData } from "../api";
import { useCredit } from "../CreditContext";
import { USER_TYPE } from "../Login";
import { Modal } from "./Modal";

export default function Navigation() {
  const [menuOpen, setMenuOpen] = useState(false);
  const [profileOpen, setProfileOpen] = useState(false);
  const navigate = useNavigate();
  const storedUserDetails = JSON.parse(sessionStorage.getItem("userDetails"));
  const { credit, setInitialCredit } = useCredit();
  const [userType, setUserType] = useState(sessionStorage.getItem(USER_TYPE));
  const [isLoggingOut, setIsLoggingOut] = useState(false);

  useEffect(() => {
    if (storedUserDetails) {
      fetchUserData(storedUserDetails.userID)
        .then((data) => {
          setInitialCredit(data.credit_amount);
        })
        .catch((error) => console.error("Error fetching user data:", error));
    }
  }, [setInitialCredit, storedUserDetails]);

  const toggleMenu = () => {
    setMenuOpen((prevMenuOpen) => !prevMenuOpen);
  };

  const toggleProfile = () => {
    setProfileOpen(!profileOpen);
  };

  const handleLogoutClick = () => {
    setIsLoggingOut(true);
    // document.body.classList.add('active-modal'); // Add class to body when modal is active
  };

  const handleConfirmLogout = () => {
    setIsLoggingOut(false);
    // document.body.classList.remove('active-modal'); // Remove class from body when modal is inactive
    sessionStorage.clear();
    navigate("/Login");
  };

  const handleCancelLogout = () => {
    setIsLoggingOut(false);
    // document.body.classList.remove('active-modal'); // Remove class from body when modal is inactive
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
          <Link onClick={handleLogoutClick}>Logout</Link>
        </div>
        <div className="profile-container">
          <div className="profile-wrapper">
            <div className="profile-icon" onClick={toggleProfile}>
              <span className={`circle ${profileOpen ? "open" : ""}`}></span>
              <div className={`profile ${profileOpen ? "open" : ""}`}>
                <Link to="/inventory_shop">Inventory</Link>
                <Link to="/Settings">Settings</Link>
                <Link onClick={onLogout}>Logout</Link>
              </div>
            </div>
            <div className={`profile-side ${profileOpen ? "open" : ""}`}>
              {storedUserDetails && (
                <>
                  <p>{storedUserDetails.username}</p>
                </>
              )}
            </div>
          </div>
        </div>
      </nav>
      {isLoggingOut && (
        <Modal
          cancelButtonLabel="Cancel"
          confirmButtonLabel="Confirm"
          modalTitle="Confirm Logout"
          modalContent="Are you sure you want to logout?"
          confirmClick={handleConfirmLogout}
          cancelClick={handleCancelLogout}
        />
      )}
    </section>
  );
}
