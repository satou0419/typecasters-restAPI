import React from "react";

export default function GameOverModal({ isVisible, score, onConfirm }) {
  if (!isVisible) {
    return null;
  }

  return (
    <div className="gameover-modal">
      <div className="gameover-modal-content">
        <h2>Game Over</h2>
        <p>Your Score: {score}</p>
        <button onClick={onConfirm}>Confirm</button>
      </div>
    </div>
  );
}
