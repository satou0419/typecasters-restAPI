import React, { useState } from "react";
import "./createroom.css";
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

  return (
    <main className="createroom-container">
      <section className="card createroom-card">
        <h1 className="createroom-card__heading">Create Room</h1>
        <input
          type="text"
          className="input input-line input-line--dark animate-shake"
          placeholder="Enter Room Name"
        />
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
