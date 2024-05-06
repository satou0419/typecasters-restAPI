import React, { useState, useEffect } from "react";
import "./components/tab.css";
import "./teacherroomsettings.css";
import { CardStatus, CardSettingF2 } from "./components/Card";

export default function TeacherRoomSettings(){
    return(
    <div className="main-container">    
        <div className="leftbox"> 
            <h3>Students Progress</h3>
                <section className="cardstatus">
                <CardStatus
                title="Username"
                content="Time"
                bannerSrc="./assets/banner/banner_adventure.webp"
                progressTitle="Score"
                progressValue="4"
                // onClick={handleCardStatusClick}
                />
                </section>
                <section className="cardstatus">
                <CardStatus
                title="Username"
                content="Time"
                bannerSrc="./assets/banner/banner_adventure.webp"
                progressTitle="Score"
                progressValue="4"
                // onClick={handleCardStatusClick}
                />
                </section>
                <section className="cardstatus">
                <CardStatus
                title="Username"
                content="Time"
                bannerSrc="./assets/banner/banner_adventure.webp"
                progressTitle="Score"
                progressValue="4"
                // onClick={handleCardStatusClick}
                />
                </section>
      </div>

      <div className="rightbox">
                <section className="card card-setting--2f words">
                <h1 className="words-added"> Words Added</h1>
                <span>Sample</span>
                <span>Sample</span>
                </section>
                <section className="cardsetting">
                <CardSettingF2
                title="Settings"
                placeholder1="Update Simulation Name"
                placeholder2="Update Time"
                btnText1="Delete"
                btnText2="Save"
                // onBtn1Click={handleBtn1Click}
                // onBtn2Click={handleBtn2Click}
                />
                </section>
      </div>
    </div>  
    );
}