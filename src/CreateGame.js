import React, { useState } from "react";
import "./components/tab.css";
import "./creategame.css";
import "./components/input.css";

export default function Archive() {
  const [activeTab, setActiveTab] = useState("Spelling");

  const handleTabClick = (tab) => {
    setActiveTab(tab);
  };
  // Conditional rendering based on activeTab and selectedWord
  const renderContent = () => {
    switch (activeTab) {
      case "Syllable":
        return <div className="tab-content">This is a tab</div>;
      case "Silent":
        return <div className="tab-content">Hello</div>;
      default:
        return <div className="tab-content">
            <input type="text" className="input input-box search-box" placeholder="Search Word" />
            <p className="txt-words-added">Words Added:</p>
            <section className="book-container">
              <div className="left-page page-container words-container">
                <div className="book-page">
                  <div className="left-content">
                    <div className="word-search">
                      <input
                        type="text"
                        className="input input-line input-line--light"
                        placeholder="Search words"
                      />
                    </div>
                    {/* <div className="word-list">{renderWordList()}</div> */}
                  </div>
                </div>
              </div>
              </section>
        </div>;
    }
  };
  return (
    <main className="tab-container">
     <p className="txt-creategame">Create Game</p>
      <section className="tab-section">
        <div className="tab-btn-container">
          <div
            className={`tab-btn ${activeTab === "Spelling" ? "active" : ""}`}
            onClick={() => handleTabClick("Spelling")}> Spelling
          </div>
          <div
            className={`tab-btn ${activeTab === "Syllable" ? "active" : ""}`}
            onClick={() => handleTabClick("Syllable")}>Syllable
          </div>
          <div
            className={`tab-btn ${activeTab === "Silent" ? "active" : ""}`}
            onClick={() => handleTabClick("Silent")}>Silent
          </div>
        </div>
        {renderContent()}
      </section>
    </main>
  );
}
