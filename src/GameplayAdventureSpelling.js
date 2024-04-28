import React, { useState } from "react";
import "./gameplay.css";
import "./components/animation.css";

export default function GameplayAdventureSpelling() {
  const [enemyState, setEnemyState] = useState({
    className: "idle",
    style: {},
  });

  const [isHit, setIsHit] = useState(false); // State to manage if character is hit

  const handleGoClick = () => {
    // Move the enemy forward and switch to attack animation
    setEnemyState({
      className: "attack",
      style: { transform: "translateX(-550px)" },
    });

    // After the attack animation starts (around 1300), simulate the main character being hit
    setTimeout(() => {
      setIsHit(true);
      setTimeout(() => {
        setIsHit(false);
      }, 500); // Duration of blink animation
    }, 1300);

    // After the attack animation completes, move back and switch to idle
    setTimeout(() => {
      setEnemyState({
        className: "idle",
        style: { transform: "translateX(0px)" },
      });
    }, 1500); // duration of the attack animation
  };

  return (
    <main className="gameplay-container">
      <div className="floor_indicator">
        <span>FLOOR 1</span>
      </div>

      <section className="gameplay-platform">
        <div className={`character character-main ${isHit ? "hit" : ""}`}>
          <img
            src="./assets/sprites/character_main.webp"
            alt="Main Character"
          />
        </div>
        <div
          className={`${enemyState.className} character character-enemy`}
          style={enemyState.style}
        ></div>
      </section>
      <section className="gameplay-control">
        <img
          className="border"
          src="./assets/misc/border_control.webp"
          alt="Decorative border"
        />
        <div className="control-item">
          <div className="control-item-container">
            <div className="item-wrapper">
              {/* Multiple items omitted for brevity */}
              <div className="item-container">
                <img src="./assets/items/item_medical_kit.webp" />
                <div className="item-qty">12x</div>
              </div>

              <div className="item-container">
                <img src="./assets/items/item_medical_kit.webp" />
                <div className="item-qty">12x</div>
              </div>
              <div className="item-container">
                <img src="./assets/items/item_medical_kit.webp" />
                <div className="item-qty">12x</div>
              </div>
              <div className="item-container">
                <img src="./assets/items/item_medical_kit.webp" />
                <div className="item-qty">12x</div>
              </div>
              <div className="item-container">
                <img src="./assets/items/item_medical_kit.webp" />
                <div className="item-qty">12x</div>
              </div>
              <div className="item-container">
                <img src="./assets/items/item_medical_kit.webp" />
                <div className="item-qty">12x</div>
              </div>
            </div>
          </div>
        </div>
        <div className="control-input">
          <div className="input-wrapper">
            <button className="btn btn--small btn--primary"></button>
            <input type="textbox" />
            <button
              className="btn btn--medium btn--primary"
              onClick={handleGoClick}
            >
              GO!
            </button>

            <div className="lives-container">
              <img src="./assets/icon/ic_heart.png" />
              <img src="./assets/icon/ic_heart.png" />
              <img src="./assets/icon/ic_heart.png" />
              <img src="./assets/icon/ic_heart.png" />
              <img src="./assets/icon/ic_heart.png" />
              <img src="./assets/icon/ic_heart.png" />
            </div>
          </div>
        </div>

        <div className="control-clue">
          <div className="control-clue-container">
            <div className="clue-wrapper">
              <h3>Word Info's</h3>
              <span className="lbl_pronunciation">Pronunciation</span>
              <div className="text-pronunciation">Happy</div>
              <span className="lbl_definition">Definition</span>
              <div className="text-definition">
                enjoying or characterized by well-being and contentment.
              </div>
            </div>
          </div>
        </div>
      </section>
    </main>
  );
}
