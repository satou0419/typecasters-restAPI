import React, { useState } from "react";
import "./components/tab.css";
import "./creategame.css";
import "./components/input.css";


export default function Archive() {
  const [activeTab, setActiveTab] = useState("Spelling");
  const [selectedEnemyWords, setEnemyWords] = useState('');
  const [selectedStudentLife, setStudentlife] = useState('');
  const [selectedItemUsage, setItemUsage] = useState('');
  const [selectedDescription, setDescription] = useState('');
  const [selectedPronunciation, setPronunciation] = useState('');
  const [selectedReplay, setReplay] = useState('');
  
  const handleEnemyWords = (event) => {
    setEnemyWords(event.target.value);
  };
   const handleStudentLife = (event) => {
    setStudentlife(event.target.value);
  };
  const handleItemUsage = (event) => {
    setItemUsage(event.target.value);
  };
  const handleDescription = (event) => {
    setDescription(event.target.value);
  };
  const handlePronunciation = (event) => {
    setPronunciation(event.target.value);
  };
  const handleReplay = (event) => {
    setReplay(event.target.value);
  };

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
          <div className="textfield-left-container">
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

                  </div>
                </div>
              </div>

              </section>
              </div>
            <div className="textfield-right-container">
                    <input type="text" className="input input-box search-box" placeholder="Enter Simulation Name" />
                    <input 
                      type="time" 
                      id="timeInput" 
                      name="time"
                      className="search-box"
                    />
                     <input type="text" className="input input-box search-box" placeholder="Set Duration" />
                    <select id="dropdown" value={selectedEnemyWords} onChange={handleEnemyWords} className="input input-box-form input-select search-box">
                      <option value="" disabled hidden> Select Number of words per Enemy</option>
                      <option value="option1">1</option>
                      <option value="option2">2</option>
                    </select>
                    <select id="dropdown" value={selectedStudentLife} onChange={handleStudentLife} className="input input-box-form input-select search-box">
                      <option value="" disabled hidden> Student Life</option>
                      <option value="option1">1</option>
                      <option value="option2">2</option>
                      <option value="option3">3</option>
                      <option value="option4">4</option>
                      <option value="option5">5</option>
                      <option value="option6">6</option>
                      <option value="option7">7</option>
                      <option value="option8">8</option>
                    </select>
                    <select id="dropdown" value={selectedItemUsage} onChange={handleItemUsage} className="input input-box-form input-select search-box">
                      <option value="" disabled hidden> Set Item Usage</option>
                      <option value="option1">Enabled</option>
                      <option value="option2">Disabled</option>
                    </select>
                    <select id="dropdown" value={selectedDescription} onChange={handleDescription} className="input input-box-form input-select search-box">
                      <option value="" disabled hidden> Set Description Visibility</option>
                      <option value="option1">Enabled</option>
                      <option value="option2">Disabled</option>
                    </select>
                    <select id="dropdown" value={selectedPronunciation} onChange={handlePronunciation} className="input input-box-form input-select search-box">
                      <option value="" disabled hidden> Set Pronunciation Visibility</option>
                      <option value="option1">Enabled</option>
                      <option value="option2">Disabled</option>
                    </select>
                    
                    <select id="dropdown" value={selectedReplay} onChange={handleReplay} className="input input-box-form input-select search-box">
                      <option value="" disabled hidden> Set Allow Replay</option>
                      <option value="option1">Enabled</option>
                      <option value="option2">Disabled</option>
                    </select>
                    
            </div>
                     
          </div>
           
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
