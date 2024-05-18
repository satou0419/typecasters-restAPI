import React, { useState, useEffect, useRef } from "react";
import {
  GET_ENEMIES_BY_FLOOR_ID_ENDPOINT,
  GET_USER_ITEMS_ENDPOINT,
  INSERT_WORD_ARCHIVE,
  UPDATE_USER_PROGRESS_ENDPOINT,
} from "./api";
import "./gameplay.css";
import "./components/animation.css";

import {
  FLOOR_ID,
  PROGRESS_ID,
  SECTION_PROGRESS,
  NEXT_FLOOR,
  NEXT_SECTION,
  CURRENT_FLOOR,
  CURRENT_SECTION,
} from "./AdventureMode";
import { useNavigate } from "react-router-dom";

export default function GameplayAdventureSpelling() {
  const [isConquered, setIsConquered] = useState();
  const [storedFloor, setStoredFloor] = useState(
    sessionStorage.getItem(FLOOR_ID)
  );
  const [flag, setFlag] = useState(0);

  const [storedProgressID, setProgressID] = useState(
    sessionStorage.getItem(PROGRESS_ID)
  );

  const [storedSectionProgress, setSectionProgress] = useState(
    sessionStorage.getItem(SECTION_PROGRESS)
  );

  const storedNextFloor = parseInt(sessionStorage.getItem(NEXT_FLOOR), 10);

  const currentFloor = parseInt(sessionStorage.getItem(CURRENT_FLOOR), 10);

  const enteredFloor = parseInt(sessionStorage.getItem(FLOOR_ID), 10);

  const [storedNextSection, setStoredNextSection] = useState(
    sessionStorage.getItem(NEXT_SECTION)
  );

  const [storedCurrentFloor, setCurrentFloor] = useState(
    sessionStorage.getItem(CURRENT_FLOOR)
  );

  const [storedCurrentSection, setCurrentSection] = useState(
    sessionStorage.getItem(CURRENT_SECTION)
  );

  const isCleared = enteredFloor < storedCurrentFloor;
  console.log("Tower Cleared: ", isCleared);
  console.log("Enter Floor: ", enteredFloor);
  console.log("Current Floor: ", storedCurrentFloor);
  const navigate = useNavigate();

  useEffect(() => {
    console.log("Conquered Value::", isConquered);
  }, []);

  useEffect(() => {
    if (isConquered === true) {
      fetch(`${UPDATE_USER_PROGRESS_ENDPOINT}${storedProgressID}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          towerSecProg: storedNextSection,
          floorId: storedNextFloor,
        }),
      })
        .then((response) => {
          if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
          }
          return response.text(); // Read response as text
        })
        .then((data) => {
          console.log("Response:", data); // Log the response received from the server
          if (data === "User Progress Updated!") {
            console.log("Updated Progress: Updated Successful");
            // Navigate after 3 seconds
            setTimeout(() => {
              navigate("/adventure_mode");
            }, 3000);
          } else {
            console.error(
              "Error updating progress: Unexpected response -",
              data
            );
          }
        })
        .catch((error) => console.error("Error updating progress:", error));
    } else if (isCleared === true) {
      console.log("Floor already conquered!!!");
    }
  }, [isConquered]);

  const [animateShake, setAnimateShake] = useState("");
  const [autoFocusValue, setAutoFocusValue] = useState(true);
  const [userItems, setUserItems] = useState([]);
  const [enemyState, setEnemyState] = useState({
    className: "idle-spring",
    style: {},
  });
  const [mainState, setMainState] = useState({
    className: "idle-main",
    style: {},
  });
  const [isEnemyHit, setEnemyIsHit] = useState(""); // State to manage if character is hit
  const [isMainHit, setMainIsHit] = useState(false); // State to manage if character is hit
  const [userInput, setUserInput] = useState(""); // State to store user input
  const [hearts, setHearts] = useState(6); // State to store remaining heart count
  const [gameOver, setGameOver] = useState(false); // State to track game over
  const [enemies, setEnemies] = useState([]);
  const [currentEnemyIndex, setCurrentEnemyIndex] = useState(0);
  const [currentWordIndex, setCurrentWordIndex] = useState(0);
  const [currentWordData, setCurrentWordData] = useState(null);
  const [audioUrl, setAudioUrl] = useState("");
  const [audioElement, setAudioElement] = useState(null);
  const [inputDisabled, setInputDisabled] = useState(false); // State to disable the input
  const [goButtonDisabled, setGoButtonDisabled] = useState(false); // State to disable the GO! button
  const [audioButtonDisabled, setAudioButtonDisabled] = useState(false); // State to disable the audio button
  const inputRef = useRef(null); // Create a ref for the input element
  const [insertWord, setInsertWord] = useState(false);
  const [userID, setUserID] = useState();

  // useEffect(() => {
  //   console.log("INSERT WORD", insertWord);
  // }, [insertWord]);

  useEffect(() => {
    if (insertWord === true) {
      console.log("UserID", userID);
      console.log("Inserted Word", currentWord);

      fetch(INSERT_WORD_ARCHIVE, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          userID: 6,
          word: currentWord,
        }),
      })
        .then((response) => {
          if (!response.ok) {
            throw new Error("Network response was not ok");
          }
          return response.json(); // Parse the JSON from the response
        })
        .then((data) => {
          // Handle the response data here
          console.log(data);
          setInsertWord(false);
        })
        .catch((error) => {
          // Handle errors here
          console.error(
            "There was a problem with your fetch operation:",
            error
          );
        });
    }
  }, [insertWord]);
  const [currentWord, setCurrentWord] = useState();

  // useEffect(() => {
  //   console.log("This is the Current Word ::::::::::>", currentWord);
  // }, [currentWord]);

  const handleStartClick = () => {
    setFlag(1);
  };
  useEffect(() => {
    console.log("Flag value:", flag);
  }, [flag]);

  // Effect for beforeunload event
  useEffect(() => {
    const handleBeforeUnload = (event) => {
      event.preventDefault(); // Prevent the default browser unload behavior
      event.returnValue = ""; // A standard way to trigger the browser's built-in navigation confirmation dialog
      // You can try setting a custom message like "You have unsaved changes!" but modern browsers might not display it.
      return "You have unsaved changes!"; // This may not work in all browsers but is still recommended
    };

    // Adding the event listener
    window.addEventListener("beforeunload", handleBeforeUnload);

    // Cleanup the event listener when the component unmounts
    return () => {
      window.removeEventListener("beforeunload", handleBeforeUnload);
    };
  }, []); // Empty dependency array ensures this effect is only run on mount and unmount

  // Effect to fetch user items
  useEffect(() => {
    if (flag === 1) {
      const storedUserDetails = JSON.parse(
        sessionStorage.getItem("userDetails")
      );
      if (storedUserDetails) {
        console.log("User ID: ", storedUserDetails.userID);
        setUserID(storedUserDetails.userID);

        // Fetch user items using userID
        fetch(`${GET_USER_ITEMS_ENDPOINT}${storedUserDetails.userID}`)
          .then((response) => response.json())
          .then((data) => {
            console.log("User items:", data);
            // Store the fetched user items in state variable
            setUserItems(data);
          })
          .catch((error) => console.error("Error fetching user items:", error));
      }
    }
  }, [flag]);

  // Effect to fetch enemies when storedFloor changes
  useEffect(() => {
    if (flag === 1 && storedFloor) {
      setGoButtonDisabled(true); // Disable GO! button
      setAudioButtonDisabled(true); // Disable audio button
      fetchEnemies(storedFloor);
    }
  }, [storedFloor, flag]);

  // Effect to fetch word definition when enemies or currentEnemyIndex changes
  useEffect(() => {
    if (
      flag === 1 &&
      enemies.length > 0 &&
      currentEnemyIndex < enemies.length
    ) {
      const enemy = enemies[currentEnemyIndex];
      const words = enemy.words;
      if (words.length > 0) {
        setCurrentWordIndex(0);
        fetchWordDefinition(words[0]);
      }

      // Logging enemy and words
      console.log(`Enemy ${enemy.enemyId}:`);
      words.forEach((word, index) => {
        console.log(`${index + 1}. ${word}`);
        setCurrentWord(word);
      });
    }
  }, [enemies, currentEnemyIndex, flag]);

  // Effect to update storedFloor when FLOOR_ID changes
  useEffect(() => {
    setStoredFloor(sessionStorage.getItem(FLOOR_ID));
  }, [sessionStorage.getItem(FLOOR_ID)]);

  // Effect to handle audioUrl changes
  useEffect(() => {
    if (audioUrl && audioElement) {
      setTimeout(() => {
        audioElement.src = audioUrl;
        audioElement.play();
        setInputDisabled(false);
        inputRef.current.focus();

        setGoButtonDisabled(false);
        setAudioButtonDisabled(false);
        setUserInput("");

        setAutoFocusValue(true);
      }, 1500);
    }
  }, [audioUrl]);

  // Effect to create audio element
  useEffect(() => {
    const audio = new Audio();
    setAudioElement(audio);

    return () => {
      audio.pause();
    };
  }, []);

  useEffect(() => {
    console.log("Conquered: ", isConquered);
  }, []);

  // Function to fetch enemies
  const fetchEnemies = (floorId) => {
    const endpoint = `${GET_ENEMIES_BY_FLOOR_ID_ENDPOINT}?floor_id=${floorId}`;
    fetch(endpoint)
      .then((response) => response.json())
      .then((data) => {
        setEnemies(data);
        setGoButtonDisabled(false); // Enable GO! button
        setAudioButtonDisabled(false); // Enable audio button
      })
      .catch((error) => {
        console.error("Error fetching enemies:", error);
        setGoButtonDisabled(false); // Enable GO! button in case of error
        setAudioButtonDisabled(false); // Enable audio button in case of error
      });
  };

  // Function to fetch word definition
  const fetchWordDefinition = async (word) => {
    try {
      const apiKey = "95454221-2935-4778-b4e6-be2ca5ede0cb";
      const url = `https://www.dictionaryapi.com/api/v3/references/sd2/json/${word}?key=${apiKey}`;

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
      console.error("Fetch word definition error:", error);
    }
  };

  const handleGoClick = () => {
    if (userInput.trim() === "") {
      // Alert the user or handle the empty input case
      console.log("Please enter a word before submitting.");
      setAnimateShake("animate-shake");
      setTimeout(() => {
        setAnimateShake("");
      }, 500);
      setTimeout(() => {
        setInputDisabled(false);
        inputRef.current.focus(); // Focus on the input
      }, 500);

      return;
    }

    const words = enemies[currentEnemyIndex].words;
    const currentWord = words[currentWordIndex];

    if (userInput.trim().toLowerCase() === currentWord.toLowerCase()) {
      // Correct word handling
      // setInsertWord(true);
      setFlag(0);
      console.log(0);
      setMainState({
        className: "attack-main",
        style: {
          transform: "translateX(550px)",
          height: "369px",
          width: "calc(7004px/21)",
        },
      });

      setTimeout(() => {
        setEnemyIsHit("hit");
        setTimeout(() => {
          setEnemyIsHit("");
        }, 500);
      }, 1300);

      setTimeout(() => {
        setMainState({
          className: "idle-main",
          style: { transform: "translateX(0px)" },
        });

        // Enable input, set focus after animation completes
        setTimeout(() => {
          setInputDisabled(false);
          inputRef.current.focus(); // Focus on the input
        }, 1500);

        // Check if all words of the enemy are defeated
        if (currentWordIndex === words.length - 1) {
          console.log(
            `Enemy ${enemies[currentEnemyIndex].enemyId} defeated! Total words: ${words.length}`
          );
          if (currentEnemyIndex < enemies.length - 1) {
            setCurrentEnemyIndex(currentEnemyIndex + 1);
            setEnemyState({
              className: "explode",
              style: { height: "128px", width: "calc(1536px / 12)" },
            });

            setTimeout(() => {
              setEnemyState({
                className: "idle-spring",
              });
            }, 3500);
          } else {
            console.log("All enemies are defeated!");
            // Add logic for completing the game if needed
            setIsConquered(true);
          }
        }
      }, 1500);

      if (currentWordIndex < words.length - 1) {
        setCurrentWordIndex(currentWordIndex + 1);
        fetchWordDefinition(words[currentWordIndex + 1]);
      }

      // Disable input, GO! button, and audio button
      setInputDisabled(true);
      setGoButtonDisabled(true);
      setAudioButtonDisabled(true);
    } else {
      // Incorrect word handling

      // Disable inputs and buttons during the animation
      setInputDisabled(true);
      setGoButtonDisabled(true);
      setAudioButtonDisabled(true);

      setEnemyState({
        className: "attack",
        style: { transform: "translateX(-550px)" },
      });

      setTimeout(() => {
        setMainIsHit("hit");
        setTimeout(() => {
          setMainIsHit("");
        }, 500);
      }, 1300);

      setTimeout(() => {
        setEnemyState({
          className: "idle-spring",
          style: { transform: "translateX(0px)" },
        });

        setTimeout(() => {
          setHearts((prevHearts) => prevHearts - 1);
          console.log("Incorrect Word:", userInput);

          // Enable input and refocus after animation completes
          setInputDisabled(false);
          setTimeout(() => {
            inputRef.current && inputRef.current.focus(); // Focus on the input if it exists
          }, 100); // Adjust the delay as needed

          // Enable buttons after animation completes
          setGoButtonDisabled(false);
          setAudioButtonDisabled(false);

          if (hearts === 1) {
            setGameOver(true);
          }
        }, 500);
      }, 1500);
    }
  };

  const handleAudioClick = () => {
    if (audioUrl && audioElement) {
      audioElement.src = audioUrl;
      audioElement.play();
      setTimeout(() => {
        setInputDisabled(false);
        inputRef.current.focus(); // Focus on the input
      }, 500);
    }
  };

  // Render game over message if game over
  if (gameOver) {
    return <div className="game-over">Game Over</div>;
  }

  return (
    <main className="gameplay-container">
      <div className="floor_indicator">
        <span>FLOOR {storedFloor}</span>
      </div>

      <section className="gameplay-platform">
        <div
          className={`${mainState.className} ${isMainHit} character character-main`}
          style={mainState.style}
        ></div>

        <div
          className={`${enemyState.className} ${isEnemyHit} character  character-enemy`}
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
              {userItems.map((item) => (
                <div key={item.userItemId} className="item-container">
                  <img
                    src={`./assets/items/${item.itemId.image_path}`}
                    alt={item.item_name}
                  />
                  <div className="item-qty">{`${item.quantity}x`}</div>
                </div>
              ))}
              {[...Array(3 - userItems.length)].map((_, index) => (
                <div key={`default-${index}`} className="item-container"></div>
              ))}
            </div>
          </div>
        </div>

        <div className="control-input">
          <div className="input-wrapper">
            <button
              className="btn btn--small btn--primary btn-audio"
              onClick={handleStartClick}
            >
              Start
            </button>
            <button
              className="btn btn--small btn--primary btn-audio"
              onClick={handleAudioClick}
              disabled={audioButtonDisabled}
            >
              Audio
            </button>
            <input
              type="text"
              value={userInput}
              onChange={(e) => setUserInput(e.target.value)}
              autoFocus={autoFocusValue} // Use the variable as the value of autoFocus
              disabled={inputDisabled}
              className={`input-answer ${animateShake}`}
              ref={inputRef} // Assign the ref to the input element
            />
            <button
              className="btn btn--medium btn--primary btn-go"
              onClick={handleGoClick}
              disabled={goButtonDisabled}
            >
              GO!
            </button>
            <div className="lives-container">
              {[...Array(6 - hearts)].reverse().map((_, index) => (
                <img
                  key={index}
                  src="./assets/icon/ic_lost_heart.png"
                  alt="Lost Heart Icon"
                />
              ))}
              {[...Array(hearts)].map((_, index) => (
                <img
                  key={index}
                  src="./assets/icon/ic_heart.png"
                  alt="Heart Icon"
                />
              ))}
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
