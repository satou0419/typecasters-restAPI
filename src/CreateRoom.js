import "./createroom.css";
import { Link, Navigate, useNavigate } from "react-router-dom";
import { USER_ID } from "./Login";
import {CREATE_ROOM} from "./api";
import { useState } from "react";

export default function CreateRoom() {
  const [userID, setUserID] = useState(sessionStorage.getItem(USER_ID));
  const [isRoomCreated, setIsRoomCreated] = useState(false);
  console.log(userID);
  
  const handleCreateRoom = async () =>{
    try{
      const creatorID = userID;
      const roomName = document.getElementById("roomName").value;

      if(!roomName){
        console.error("Please fill all fields");
        return;
      }

      const updateUserURL = `${CREATE_ROOM}`;
  
      const response = await fetch(updateUserURL, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          creatorID:creatorID,
          roomName: roomName
        }),
      });
      
      if (!response.ok) {
        throw new Error("Failed to create room");
      }
  
      console.log("Room Created Successfully");
      // Navigate('/teacher/simulation_mode');
    }catch(error){
      console.error("Error creating room!");
    }
  };

  return (
    <main className="createroom-container">
      <section className="card createroom-card">
        <h1 className="createroom-card__heading">Create Room</h1>
        <input
          type="text"
          className="input input-line input-line--dark"
          placeholder="Enter Room Name"
          id="roomName"
        />
          <Link to="/teacher/simulation_mode">
          <button className="btn btn--large btn--primary" onClick={handleCreateRoom}>CREATE</button>
          </Link>
          <button className=">btn btn--large btn--danger--large">CANCEL</button>
        
    
      </section>
    </main>
  );

}
