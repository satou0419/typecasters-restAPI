import React, { useState } from "react";
import "./tab.css";

export default function Tab() {
  const [activeTab, setActiveTab] = useState("archive"); // Default to "archive"
  const [selectedWord, setSelectedWord] = useState(""); // State to store the selected word

  // Function to handle tab click
  const handleTabClick = (tab) => {
    setActiveTab(tab);
  };

  // Function to handle word click
  const handleWordClick = (word) => {
    setSelectedWord(word); // Update the selected word state
  };

  // Conditional rendering based on activeTab and selectedWord
  const renderContent = () => {
    switch (activeTab) {
      case "archive":
        return <div className="tab-content">This is a tab</div>;
      case "shop":
        return <div className="tab-content">Hello</div>;
      default:
        return <div className="tab-content">Select a tab</div>;
    }
  };

  return (
    <main className="tab-container">
      <section className="tab-section">
        <div className="tab-btn-container">
          <div
            className={`tab-btn ${activeTab === "inventory" ? "active" : ""}`}
            onClick={() => handleTabClick("inventory")}
          >
            Archive
          </div>
          <div
            className={`tab-btn ${activeTab === "shop" ? "active" : ""}`}
            onClick={() => handleTabClick("shop")}
          >
            Shop
          </div>
        </div>

        {renderContent()}
      </section>
    </main>
  );
}
