import React from "react";

import { Link } from "react-router-dom";
import { ArchiveCard, GameCard } from "./Card"; // Import only the CardCreate component

import "./navigation.css";

export default function Navigation() {
  return (
    <section>
      <main className="nav-container">
        <nav className="nav-bar">
          <div className="nav-bar__logo">
            <img src="./assets/logo/logo_simple.webp" />
          </div>

          <Link to="/login">About</Link>
          <Link to="/login">Logout</Link>
        </nav>
      </main>
    </section>
  );
}
