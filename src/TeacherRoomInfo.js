import React, { useState, useEffect } from "react";
import "./components/tab.css";
import "./teacherroominfo.css";
import { CardStatus, CardSettingF2, CardSetting1F } from "./components/Card";

export default function TeacherRoomInfo(){
    return(
    <div className="main-container">    
        <div className="leftbox"> 
            <h3>Room Settings</h3>
                <section className="cardstatus">
                <CardStatus
                title="Username"
                content="Time"
                bannerSrc="./assets/banner/banner_adventure.webp"
                // onClick={handleCardStatusClick}
                />
                </section>
                <section className="cardstatus">
                <CardStatus
                title="Username"
                content="Time"
                bannerSrc="./assets/banner/banner_adventure.webp"
                // onClick={handleCardStatusClick}
                />
                </section>
                <section className="cardstatus">
                <CardStatus
                title="Username"
                content="Time"
                bannerSrc="./assets/banner/banner_adventure.webp"
                // onClick={handleCardStatusClick}
                />
                </section>
      </div>

      <div className="rightbox-info ">
                <section className="info-settings">
                <CardSetting1F
                    title="Settings"
                    placeholder="Update Room Name"
                    btnText1="Delete"
                    btnText2="Save"
                    // onBtn1Click={handleBtn1Click}
                    // onBtn2Click={handleBtn2Click}
                />
                </section>
                <section className="card card-setting card-setting--1f lower-box">
                    <h2 className="card-setting__heading">Add Students</h2>
                    <div className="card-setting__input-container">
                    <input
                        type="text"
                        className="input input-box"
                        placeholder="Add Students"
                    />
                    <div className="card-setting__btn-group">
                        <button className="btn btn--small btn--primary add-btn">
                        Add
                        </button>
                    </div>
                    <div className="btn-group-out">
                    <button className="btn btn--small btn--danger">
                    Remove
                    </button>
                   <button className="btn btn--small btn--primary save-btn">
                    Save
                    </button>
                    </div>
                    </div>
                </section>
      </div>
    </div>  
    );
}