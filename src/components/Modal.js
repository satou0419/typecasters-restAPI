import React, { useState } from "react";
import "./modal.css";

export default function Modal({
  cancelButtonLabel = "Cancel",
  confirmButtonLabel = "Confirm",
  modalTitle = "Hello Modal",
  modalContent = "Lorem ipsum dolor sit amet consectetur adipiscing.",
}) {
  const [modal, setModal] = useState(false);

  return (
    <>
      {modal && (
        <div className="modal">
          <div className="overlay"></div>
          <div className="modal-content">
            <h1>{modalTitle}</h1>
            <p>{modalContent}</p>
            <div className="btn-container">
              <button
                onClick={toggleModal}
                className="btn btn--small btn--danger close-modal"
              >
                {cancelButtonLabel}
              </button>
              <button
                onClick={toggleModal}
                className="btn btn--small btn--primary close-modal"
              >
                {confirmButtonLabel}
              </button>
            </div>
          </div>
        </div>
      )}
    </>
  );
}
