import React from "react";
import "./card.css";
import "./button.css";

export const CardSetting1F = ({ title, placeholder, btnText1, btnText2 }) => (
  <section className="card card-setting card-setting--1f">
    <h2 className="card-setting__heading">{title}</h2>
    <div className="card-setting__input-container">
      <input
        type="text"
        className="input input-box"
        placeholder={placeholder}
      />
      <div className="card-setting__btn-group">
        <button className="btn btn--small btn--danger">{btnText1}</button>
        <button className="btn btn--small btn--primary">{btnText2}</button>
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
        <button className="btn btn--small btn--danger">{btnText1}</button>
        <button className="btn btn--small btn--primary">{btnText2}</button>
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
}) => (
  <section className="card card-simulation">
    <div className="card-simulation__banner">
      <img src={bannerSrc} alt="Simulation Banner" />
    </div>
    <div className="card__description">
      <h1 className="card__title">{title}</h1>
      <span className="card__content">{content}</span>
    </div>
    <div className="card__progress push-top">
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
}) => (
  <section className="card card-status">
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
}) => (
  <section className="card card-archive">
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

const Card = () => {
  return (
    <main className="card-container">
      {/* Render all card components */}
      <CardSetting1F
        title="Settings"
        placeholder="Update Room Name"
        btnText1="Delete"
        btnText2="Save"
      />
      <CardSettingF2
        title="Settings"
        placeholder1="Update Simulation Name"
        placeholder2="Update Time"
        btnText1="Delete"
        btnText2="Save"
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
      />
      <CardCreate title="+Create" />
      <CardStatus
        title="Username"
        content="Time"
        bannerSrc="./assets/banner/banner_adventure.webp"
        progressTitle="Score"
        progressValue="4"
      />
      <CardArchive
        title="Archive"
        content="Conquer the Towers! Collect Words and Badges in Adventure Mode"
        bannerSrc="./assets/banner/banner_adventure.webp"
        badgeProgress="4"
        wordProgress="4"
      />
    </main>
  );
};

export default Card;
