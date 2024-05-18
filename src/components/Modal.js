import React, { useState } from "react";
import "./modal.css";

export const Modal = ({
  cancelButtonLabel,
  confirmButtonLabel,
  modalTitle,
  modalContent,
  cancelClick,
  confirmClick // Pass the toggleModal function as a prop
}) => (
  <section>
    <div className="modal">
      <div className="overlay"></div>
      <div className="modal-content">
        <h1>{modalTitle}</h1>
        <p>{modalContent}</p>
        <div className="btn-container">
          <button
            onClick={cancelClick}
            className="btn btn--small btn--danger close-modal"
          >
            {cancelButtonLabel}
          </button>
          <button
            onClick={confirmClick}
            className="btn btn--small btn--primary close-modal"
          >
            {confirmButtonLabel}
          </button>
        </div>
      </div>
    </div>
  </section>
);

const App = () => {
  const [modal, setModal] = useState(false);

  // // Define function to toggle modal visibility
  const toggleModal = () => {
    setModal(!modal);
  };


  const buttonClick = () => {
    console.log("Clicked")
  }

  return (
    <div>
      {/* Render other components */}
      <Modal
        cancelButtonLabel="Cancel"
        confirmButtonLabel="Confirm"
        modalTitle="Hello Modal"
        modalContent="Lorem ipsum dolor sit amet"
        confirmClick={buttonClick}
        cancelClick={buttonClick}
        
      />
    </div>
  );
};

export default App;
