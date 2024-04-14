import React from "react";
import { ArchiveCard } from "./Card"; // Import only the CardCreate component

import "./navigation.css";

export default function Navigation() {
  return (
    <section>
      <main className="nav-container">
        <nav className="nav-bar">
          <div className="nav-bar__logo">
            <img src="./assets/logo/logo_simple.webp" />
          </div>

          <a>About</a>
        </nav>
      </main>

      <ArchiveCard className="custom-archive-card" />
    </section>
  );
}
