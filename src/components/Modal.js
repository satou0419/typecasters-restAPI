import React, {useState} from "react";
import"./modal.css";

export default function Modal(){

    const [modal, setModal] = useState(false);

    const toggleModal = () => {
        setModal(!modal)
    }

    return(
        <>
        
        <button 
        onClick={toggleModal}
        className="btn-modal">
            Open
        </button>

        {modal && (
            <div className="modal">
            <div className="overlay"></div>
            <div className="modal-content">
                <h1>Hello Modal</h1>
                <p>
                 Lorem ipsum dolor sit amet conssectetur 
                 adispicng.                        
                </p>
                <div className="btn-container">
                <button 
                onClick={toggleModal}
                className="btn btn--small btn--danger close-modal">
                    Cancel
                </button>
                <button 
                onClick={toggleModal}
                className="btn btn--small btn--primary close-modal">
                    Confirm
                </button>
                </div>
            </div>      
        </div>
        )}
        

        </>

    );
}