import React, { useState } from "react";
import "./createroom.css";
<<<<<<< HEAD
// import { Link } from "react-router-dom";
import { Modal } from './components/Modal';
import "./components/animation.css";


  export default function CreateRoom() {
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [isRoomNameEmpty, setIsRoomNameEmpty] = useState(true);

    const handleRoomNameEmpty = () => {
      if(setIsRoomNameEmpty(true)){
        
      }
    };
  
    const handleCancelCreate = () => {
      setIsModalOpen(false);
      console.log("Cancelled Room Creation")
    };
  
    const handleCreate = () => {
      setIsModalOpen(true); // Show the modal
    };
  
    const handleConfirmCreate = () => {
      setIsModalOpen(false); // Hide the modal
      console.log("Room Created!") // Navigate to the specified route
    };
=======
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
>>>>>>> main

  return (
    <main className="createroom-container">
      <section className="card createroom-card">
        <h1 className="createroom-card__heading">Create Room</h1>
        <input
          type="text"
          className="input input-line input-line--dark animate-shake"
          placeholder="Enter Room Name"
          id="roomName"
        />
<<<<<<< HEAD
        {/* <Link to="/teacher/simulation_mode"> */}
          <button 
          onClick={handleCreate}
          className="btn btn--large btn--primary">
            CREATE
          </button>
        {/* </Link>
        <Link to="/teacher/simulation_mode"> */}
          <button className=">btn btn--large btn--danger--large">CANCEL</button>
        {/* </Link> */}
=======
          <Link to="/teacher/simulation_mode">
          <button className="btn btn--large btn--primary" onClick={handleCreateRoom}>CREATE</button>
          </Link>
          <button className=">btn btn--large btn--danger--large">CANCEL</button>
        
    
>>>>>>> main
      </section>
      {isModalOpen && (
        <Modal
          cancelButtonLabel="Cancel"
          confirmButtonLabel="Confirm"
          modalTitle="Create Room"
          modalContent="Are you sure you want to create the room?"
          confirmClick={handleConfirmCreate}
          cancelClick={handleCancelCreate}
        />
      )}
    </main>
  );

}
