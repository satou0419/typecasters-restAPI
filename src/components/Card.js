import React from "react";
import "./card.css";
import "./button.css";

export const CardSetting1F = ({
  title,
  placeholder,
  btnText1,
  btnText2,
  onBtn1Click,
  onBtn2Click,
}) => (
  <section className="card card-setting card-setting--1f">
    <h2 className="card-setting__heading">{title}</h2>
    <div className="card-setting__input-container">
      <input
        type="text"
        className="input input-box"
        placeholder={placeholder}
      />
      <div className="card-setting__btn-group">
        <button className="btn btn--small btn--danger" onClick={onBtn1Click}>
          {btnText1}
        </button>
        <button className="btn btn--small btn--primary" onClick={onBtn2Click}>
          {btnText2}
        </button>
      </div>
    </div>
  </section>
);

export const CardSettingF2 = ({
  title,
  placeholder1,
  placeholder2,
  btnText1,
  btnText2,
  onBtn1Click,
  onBtn2Click,
}) => (
  <section className="card card-setting card-setting--2f">
    <h2 className="card-setting__heading">{title}</h2>
    <div className="card-setting__input-container">
      <input
        type="text"
        className="input input-box"
        placeholder={placeholder1}
      />
      <input
        type="text"
        className="input input-box"
        placeholder={placeholder2}
      />
      <div className="card-setting__btn-group">
        <button className="btn btn--small btn--danger" onClick={onBtn1Click}>
          {btnText1}
        </button>
        <button className="btn btn--small btn--primary" onClick={onBtn2Click}>
          {btnText2}
        </button>
      </div>
    </div>
  </section>
);

export const CardGame = ({
  title,
  content,
  progressTitle,
  progressValue,
  imageSrc,
}) => (
  <section className="card card-game">
    <div className="card-game__banner">
      <img src={imageSrc} alt="Game Banner" />
    </div>
    <div className="card__progress">
      <h2 className="card__progress-title">{progressTitle}</h2>
      <span className="card__progress-value">{progressValue}</span>
    </div>
    <div className="card__description">
      <h1 className="card__title">{title}</h1>
      <span className="card__content">{content}</span>
    </div>
  </section>
);

export const CardSimulation = ({
  title,
  content,
  bannerSrc,
  progressTitle,
  progressValue,
  onClick,
}) => (
  <section className="card card-simulation">
    <div className="card-simulation__banner">
      <img src={bannerSrc} alt="Simulation Banner" />
    </div>
    <div className="card__description">
      <h1 className="card__title">{title}</h1>
      <span className="card__content">{content}</span>
    </div>
    <div className="card__progress push-top" onClick={onClick}>
      <h2 className="card__progress-title">{progressTitle}</h2>
      <span className="card__progress-value">{progressValue}</span>
    </div>
  </section>
);

export const CardCreate = ({ title }) => (
  <section className="card card-simulation card-create">
    <h1 className="card__title">{title}</h1>
  </section>
);

export const CardStatus = ({
  title,
  content,
  bannerSrc,
  progressTitle,
  progressValue,
  onClick,
}) => (
  <section className="card card-status" onClick={onClick}>
    <div className="card-status__info">
      <div className="card-status__banner">
        <img src={bannerSrc} alt="Status Banner" />
      </div>
      <div className="card-status__description">
        <h1 className="card__title">{title}</h1>
        <span className="card__content">{content}</span>
      </div>
    </div>
    <div className="card__progress status-progress">
      <h2 className="card__progress-title">{progressTitle}</h2>
      <span className="card__progress-value">{progressValue}</span>
    </div>
  </section>
);

export const CardArchive = ({
  title,
  content,
  bannerSrc,
  badgeProgress,
  wordProgress,
  onClick,
}) => (
  <section className="card card-archive" onClick={onClick}>
    <div className="card-status__info push-down">
      <div className="card-archive__banner-container">
        <div className="card-archive__banner">
          <img src={bannerSrc} alt="Archive Banner" />
        </div>
      </div>
      <div className="card-status__description">
        <h1 className="card__title">{title}</h1>
        <span className="card__content">{content}</span>
      </div>
    </div>
    <div className="card__progress--container">
      <div className="card__progress">
        <h2 className="card__progress-title push-top">Badges</h2>
        <span className="card__progress-value">{badgeProgress}</span>
      </div>
      <div className="card__progress">
        <h2 className="card__progress-title push-top">Words</h2>
        <span className="card__progress-value">{wordProgress}</span>
      </div>
    </div>
  </section>
);

export const CardItem = ({ bannerSrc, itemName, itemBtnPrice, onClick }) => (
  <section className="card card-item">
    <div className="card-item__banner">
      <img src={bannerSrc} alt={itemName} />
    </div>
    <h3 className="item-name">{itemName}</h3>
    <button className="btn btn-item" onClick={onClick}>
      {itemBtnPrice}
    </button>
  </section>
);

const Card = () => {
  // Define onClick handler functions for buttons in card components
  const handleBtn1Click = () => {
    // Handle click event for button 1
    console.log("Button 1 clicked!");
  };

  const handleBtn2Click = () => {
    // Handle click event for button 2
    console.log("Button 2 clicked!");
  };

  const handleCardItemClick = () => {
    // Handle click event for card item
    console.log("Card item clicked!");
  };

  const handleCardSimulationClick = () => {
    // Handle click event for card simulation
    console.log("Card simulation clicked!");
  };

  const handleCardStatusClick = () => {
    // Handle click event for card status
    console.log("Card status clicked!");
  };

  const handleCardArchiveClick = () => {
    // Handle click event for card archive
    console.log("Card archive clicked!");
  };

  return (
    <main className="card-container">
      {/* Render all card components */}
      <CardSetting1F
        title="Settings"
        placeholder="Update Room Name"
        btnText1="Delete"
        btnText2="Save"
        onBtn1Click={handleBtn1Click}
        onBtn2Click={handleBtn2Click}
      />
      <CardSettingF2
        title="Settings"
        placeholder1="Update Simulation Name"
        placeholder2="Update Time"
        btnText1="Delete"
        btnText2="Save"
        onBtn1Click={handleBtn1Click}
        onBtn2Click={handleBtn2Click}
      />
      <CardGame
        title="Adventure"
        content="Lorem ipsum dolor sit amet, consectetur adipiscing"
        progressTitle="Floor Completed"
        progressValue={4}
        imageSrc="./assets/banner/banner_adventure.webp"
      />
      <CardSimulation
        title="Class Name"
        content="Teacher"
        bannerSrc="./assets/banner/banner_adventure.webp"
        progressTitle="Students"
        progressValue="4"
        onClick={handleCardSimulationClick}
      />
      <CardCreate title="+Create" />
      <CardStatus
        title="Username"
        content="Time"
        bannerSrc="./assets/banner/banner_adventure.webp"
        progressTitle="Score"
        progressValue="4"
        onClick={handleCardStatusClick}
      />
      <CardArchive
        title="Archive"
        content="Conquer the Towers! Collect Words and Badges in Adventure Mode"
        bannerSrc="./assets/banner/banner_adventure.webp"
        badgeProgress="4"
        wordProgress="4"
        onClick={handleCardArchiveClick}
      />
      {/* CardItem */}
      <CardItem
        bannerSrc="./assets/items/item_medical_kit.webp"
        itemName="Medkit"
        itemBtnPrice={150}
        onClick={handleCardItemClick}
      />
    </main>
  );
};

export default Card;
