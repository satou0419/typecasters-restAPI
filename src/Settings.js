import React, { useState } from "react";
import "./settings.css";

export default function Settings() {
  const [activeTab, setActiveTab] = useState("Account Information"); // Default to "archive"
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
      case "Account Information":
        return <div className="tab-content">
            <div className="account-container">
                <p>Account Information</p>
                <div className="text-container">
                    <input type="text" className="input input-line input-line--light textfield" placeholder="Username"/>
                    <input type="text" className="input input-line input-line--light textfield" placeholder="Firstname"/>
                    <input type="text" className="input input-line input-line--light textfield" placeholder="Lastname"/>
                    
                </div>
                <button className="btn btn--medium btn--primary btn-save">Save Changes</button>
            </div>
        </div>;
      case "Change Password":
        return <div className="tab-content">
            <div className="account-container">
                <p>Change Password</p>
                <div className="text-container password">
                    <input type="text" className="input input-line input-line--light textfield" placeholder="Current Password"/>
                    <input type="text" className="input input-line input-line--light textfield" placeholder="New Password"/>
                    <input type="text" className="input input-line input-line--light textfield" placeholder="Confirm New Password"/>
                    
                </div>
                <button className="btn btn--medium btn--primary btn-save">Save Changes</button>
            </div>
        </div>;
      default:
        return <div className="tab-content">Select a tab</div>;
    }
  };

  return (
    <main className="tab-container">
      <section className="tab-section">
        <div className="tab-btn-container">
          <div
            className={`tab-btn ${activeTab === "Account Information" ? "active" : ""}`}
            onClick={() => handleTabClick("Account Information")}
          >
            Account Information
          </div>
          <div
            className={`tab-btn ${activeTab === "Change Password" ? "active" : ""}`}
            onClick={() => handleTabClick("Change Password")}
          >
            Change Password
          </div>
        </div>

        {renderContent()}
      </section>
    </main>
  );
}
