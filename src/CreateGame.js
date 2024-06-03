import React, { useState } from "react";
import "./components/tab.css";
import "./creategame.css";
import "./components/input.css";
import { Link } from "react-router-dom";

export default function CreateGame() {
  const [activeTab, setActiveTab] = useState("Spelling");
  const [selectedEnemyWords, setEnemyWords] = useState("");
  const [selectedStudentLife, setStudentlife] = useState("");
  const [isItemOn, setIsItemOn] = useState(false);
  const [isDescriptionOn, setIsDescriptionOn] = useState(false);
  const [isPronunciationOn, setIsPronunciationOn] = useState(false);
  const [isReplayOn, setIsReplayOn] = useState(false);

  // Function to toggle the switch state
  const toggleSwitchItem = () => {
    setIsItemOn(!isItemOn);
  };
  const toggleSwitchDesciption = () => {
    setIsDescriptionOn(!isDescriptionOn);
  };
  const toggleSwitchPronunciation = () => {
    setIsPronunciationOn(!isPronunciationOn);
  };
  const toggleSwitchReplay = () => {
    setIsReplayOn(!isReplayOn);
  };
  const handleEnemyWords = (event) => {
    setEnemyWords(event.target.value);
  };

  const handleStudentLife = (event) => {
    setStudentlife(event.target.value);
  };

  const handleTabClick = (tab) => {
    setActiveTab(tab);
  };

  const renderContent = () => {
    switch (activeTab) {
      case "Syllable":
        return <div className="tab-content create">This is a tab</div>;
      case "Silent":
        return <div className="tab-content create">Hello</div>;
      default:
        return (
          <div className="tab-content create">
            <div className="textfield-left-container">
              <input
                type="text"
                className="input input-box search-box"
                placeholder="Search Word"
              />
              <p className="txt-words-added">Words Added:</p>
              <section className="book-container added-word">
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
                    </div>
                  </div>
                </div>
              </section>
            </div>
            <div className="textfield-right-container">
              <input
                type="text"
                className="input input-box search-box"
                placeholder="Enter Simulation Name"
              />
              <input
                type="datetime-local"
                id="dateTimeInput"
                name="dateTime"
                className="input input-box-form input-select search-box"
                placeholder="Set Deadline"
              />

              <input
                type="text"
                className="input input-box search-box"
                placeholder="Set Words duration"
              />
              <select
                id="dropdown"
                value={selectedEnemyWords}
                onChange={handleEnemyWords}
                className="input input-box-form input-select search-box"
              >
                <option value="" disabled hidden>
                  {" "}
                  Select Number of words per Enemy
                </option>
                <option value="option1">1</option>
                <option value="option2">2</option>
              </select>
              <select
                id="dropdown"
                value={selectedStudentLife}
                onChange={handleStudentLife}
                className="input input-box-form input-select search-box"
              >
                <option value="" disabled hidden>
                  {" "}
                  Student Life
                </option>
                <option value="option1">1</option>
                <option value="option2">2</option>
                <option value="option3">3</option>
                <option value="option4">4</option>
                <option value="option5">5</option>
                <option value="option6">6</option>
                <option value="option7">7</option>
                <option value="option8">8</option>
              </select>
              <div className="checkmark">
                <div
                  className="switch"
                  onClick={toggleSwitchItem}
                  style={{ cursor: "pointer" }}
                >
                  {isItemOn ? (
                    <img src="/assets/misc/Switch_on.svg" alt="Switch On" />
                  ) : (
                    <img src="/assets/misc/Switch_off.svg" alt="Switch Off" />
                  )}
                </div>
                <p className="placeholder">Set Item Usage</p>
              </div>
              <div className="checkmark">
                <div
                  className="switch"
                  onClick={toggleSwitchDesciption}
                  style={{ cursor: "pointer" }}
                >
                  {isDescriptionOn ? (
                    <img src="/assets/misc/Switch_on.svg" alt="Switch On" />
                  ) : (
                    <img src="/assets/misc/Switch_off.svg" alt="Switch Off" />
                  )}
                </div>
                <p className="placeholder">Set Description Visibility</p>
              </div>
              <div className="checkmark">
                <div
                  className="switch"
                  onClick={toggleSwitchPronunciation}
                  style={{ cursor: "pointer" }}
                >
                  {isPronunciationOn ? (
                    <img src="/assets/misc/Switch_on.svg" alt="Switch On" />
                  ) : (
                    <img src="/assets/misc/Switch_off.svg" alt="Switch Off" />
                  )}
                </div>
                <p className="placeholder">Set Pronunciation</p>
              </div>
              <div className="checkmark">
                <div
                  className="switch"
                  onClick={toggleSwitchReplay}
                  style={{ cursor: "pointer" }}
                >
                  {isReplayOn ? (
                    <img src="/assets/misc/Switch_on.svg" alt="Switch On" />
                  ) : (
                    <img src="/assets/misc/Switch_off.svg" alt="Switch Off" />
                  )}
                </div>
                <p className="placeholder">Set Allow Replay</p>
              </div>
              <Link to={"/teacher/simulation_room"}>
                <div className="btn-container">
                  <button className="btn btn--large btn--danger--large">
                    Cancel
                  </button>
                  <button className="btn btn--small btn--primary">
                    Confirm
                  </button>
                </div>
              </Link>
            </div>
          </div>
        );
    }
  };
  return (
    <main className="tab-container">
      <p className="txt-creategame">Create Game</p>
      <section className="tab-section">
        <div className="tab-btn-container">
          <div
            className={`tab-btn ${activeTab === "Spelling" ? "active" : ""}`}
            onClick={() => handleTabClick("Spelling")}
          >
            {" "}
            Spelling
          </div>
          <div
            className={`tab-btn ${activeTab === "Syllable" ? "active" : ""}`}
            onClick={() => handleTabClick("Syllable")}
          >
            Syllable
          </div>
          <div
            className={`tab-btn ${activeTab === "Silent" ? "active" : ""}`}
            onClick={() => handleTabClick("Silent")}
          >
            Silent
          </div>
        </div>
        {renderContent()}
      </section>
    </main>
  );
}
