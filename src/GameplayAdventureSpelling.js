import React, { useState, useEffect } from "react";
import { GET_ENEMIES_BY_FLOOR_ID_ENDPOINT } from "./api";
import "./gameplay.css";
import "./components/animation.css";
import { FLOOR_STORAGE_KEY } from "./AdventureMode";

export default function GameplayAdventureSpelling() {
  const storedFloor = sessionStorage.getItem(FLOOR_STORAGE_KEY);

  const [enemyState, setEnemyState] = useState({
    className: "idle",
    style: {},
  });

  const [isHit, setIsHit] = useState(false); // State to manage if character is hit

  const [enemies, setEnemies] = useState([]);
  const [currentEnemyIndex, setCurrentEnemyIndex] = useState(0);
  const [currentWordIndex, setCurrentWordIndex] = useState(0);
  const [currentWordData, setCurrentWordData] = useState(null);
  const [audioUrl, setAudioUrl] = useState("");
  const [audioElement, setAudioElement] = useState(null);

  useEffect(() => {
    if (storedFloor) {
      fetchEnemies(storedFloor);
    }
  }, [storedFloor]);

  const fetchEnemies = (floorId) => {
    const endpoint = `${GET_ENEMIES_BY_FLOOR_ID_ENDPOINT}?floor_id=${floorId}`;
    fetch(endpoint)
      .then((response) => response.json())
      .then((data) => {
        setEnemies(data);
      })
      .catch((error) => console.error("Error fetching enemies:", error));
  };

  const fetchWordDefinition = async (word) => {
    const apiKey = "95454221-2935-4778-b4e6-be2ca5ede0cb";
    const url = `https://www.dictionaryapi.com/api/v3/references/sd2/json/${word}?key=${apiKey}`;

    try {
      const response = await fetch(url);
      const data = await response.json();

      if (data && data.length > 0) {
        const firstResult = data[0];
        const pronunciation = firstResult.hwi?.prs[0]?.mw || "";
        const definition =
          firstResult.def?.[0]?.sseq?.[0]?.[0]?.[1]?.dt?.[0]?.[1] ||
          "No definition found";
        const audio = firstResult.hwi?.prs[0]?.sound?.audio;

        const subdirectory = audio?.startsWith("bix")
          ? "bix"
          : audio?.startsWith("gg")
          ? "gg"
          : audio?.match(/^[^a-zA-Z]/)
          ? "number"
          : audio?.charAt(0);

        const audioUrl = audio
          ? `https://media.merriam-webster.com/audio/prons/en/us/mp3/${subdirectory}/${audio}.mp3`
          : "";

        setCurrentWordData({ pronunciation, definition });
        setAudioUrl(audioUrl);
      } else {
        setCurrentWordData({
          pronunciation: "",
          definition: "Word is not available.",
        });
        setAudioUrl("");
        console.log(`Current Word: ${word} is not available`);
      }
    } catch (error) {
      console.error("Error fetching definition:", error);
      setCurrentWordData({
        pronunciation: "",
        definition: "Error fetching the definition.",
      });
      setAudioUrl("");
    }
  };

  useEffect(() => {
    const audio = new Audio();
    setAudioElement(audio);
    return () => {
      audio.pause();
    };
  }, []);

  useEffect(() => {
    if (enemies.length > 0 && currentEnemyIndex < enemies.length) {
      const words = enemies[currentEnemyIndex].words;
      if (words.length > 0) {
        setCurrentWordIndex(0);
        fetchWordDefinition(words[0]);
      }
    }
  }, [enemies, currentEnemyIndex]);

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

    const words = enemies[currentEnemyIndex].words;
    if (currentWordIndex < words.length - 1) {
      setCurrentWordIndex(currentWordIndex + 1);
      fetchWordDefinition(words[currentWordIndex + 1]);
    } else if (currentEnemyIndex < enemies.length - 1) {
      setCurrentEnemyIndex(currentEnemyIndex + 1);
    } else {
      alert("All enemies are defeated!");
    }

    // Log the current word after updating the currentWordIndex
    console.log("Current Word:", words[currentWordIndex]);
  };

  const handleAudioClick = () => {
    if (audioUrl && audioElement) {
      audioElement.src = audioUrl;
      audioElement.play();
    }
  };

  return (
    <main className="gameplay-container">
      <div className="floor_indicator">
        <span>FLOOR {storedFloor}</span>
      </div>

      <section className="gameplay-platform">
        <div className={`character character-main ${isHit ? "hit" : ""}`}>
          <img
            src="./assets/sprites/character_main.webp" //ths is the path
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
            <button
              className="btn btn--small btn--primary"
              onClick={handleAudioClick}
            >
              Audio
            </button>
            <input type="textbox" />
            <button
              className="btn btn--medium btn--primary"
              onClick={handleGoClick}
            >
              GO!
            </button>
            <div className="lives-container">
              <img src="./assets/icon/ic_heart.png" alt="Heart Icon" />
              <img src="./assets/icon/ic_heart.png" alt="Heart Icon" />
              <img src="./assets/icon/ic_heart.png" alt="Heart Icon" />
              <img src="./assets/icon/ic_heart.png" alt="Heart Icon" />
              <img src="./assets/icon/ic_heart.png" alt="Heart Icon" />
              <img src="./assets/icon/ic_heart.png" alt="Heart Icon" />
            </div>
          </div>
        </div>
        <div className="control-clue">
          <div className="control-clue-container">
            <div className="clue-wrapper">
              <h3>Word Info's</h3>
              <span className="lbl_pronunciation">Pronunciation</span>
              <div className="text-pronunciation">
                {currentWordData?.pronunciation.toString()}
              </div>
              <span className="lbl_definition">Definition</span>
              <div className="text-definition">
                {currentWordData?.definition.toString()}
              </div>
            </div>
          </div>
        </div>
      </section>
    </main>
  );
}
