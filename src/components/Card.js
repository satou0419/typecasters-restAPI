import React from "react";
import "./card.css";

// GameCard component
export function GameCard({
  title,
  description,
  bannerSrc,
  counterHeader,
  counterCount,
  imageSize, // New prop for image size
}) {
  return (
    <>
      <section className="card card-game">
        <div className="card-h__banner-container">
          <img
            src={bannerSrc}
            className="card-h__banner"
            alt={title}
            style={{ width: imageSize }}
          />{" "}
          {/* Apply width style based on the imageSize prop */}
        </div>
        <div className="card-h__counter-banner">
          <h3 className="card-h__counter-banner-header">{counterHeader}</h3>
          <span className="card-h__counter-banner-count">{counterCount}</span>
        </div>

        <div className="card-h__info-container">
          <h2 className="card-h__title">{title}</h2>
          <span className="card-h__description">{description}</span>
        </div>
      </section>
    </>
  );
}

// RoomCard component
export function RoomCard({
  title,
  description,
  bannerSrc,
  counterHeader,
  counterCount,
}) {
  return (
    <section className="card card-h">
      <div className="card-h__banner-container card-h__banner-container--square">
        <img
          src={bannerSrc}
          className="card-h__banner card-h__banner--square"
          alt={title}
        />
      </div>

      <div className="card-h__info-container">
        <h2 className="card-h__title">{title}</h2>
        <span className="card-h__description">{description}</span>
      </div>
      <div className="card-h__counter-banner card-h__counter-banner--square">
        <h3 className="card-h__counter-banner-header">{counterHeader}</h3>
        <span className="card-h__counter-banner-count">{counterCount}</span>
      </div>
    </section>
  );
}

// TeacherStatusCard component
export function TeacherStatusCard({ username, time, bannerSrc }) {
  return (
    <section className="card card-v">
      <div className="card-v__group">
        <div className="card-v__banner-container">
          <img
            src={bannerSrc}
            className="card-v__banner"
            alt="Teacher Status Banner"
          />
        </div>
        <div className="card-v__info-container">
          <h2 className="card-v__title">{username}</h2>
          <span className="card-v__description">{time}</span>
        </div>
      </div>

      <div className="card-v__counter-banner"></div>
    </section>
  );
}

// StudentStatusCard component
export function StudentStatusCard({ username, time, bannerSrc, score }) {
  return (
    <section className="card card-v">
      <div className="card-v__group">
        <div className="card-v__banner-container">
          <img
            src={bannerSrc}
            className="card-v__banner"
            alt="Student Status Banner"
          />
        </div>
        <div className="card-v__info-container">
          <h2 className="card-v__title">{username}</h2>
          <span className="card-v__description">{time}</span>
        </div>
      </div>

      <div className="card-v__counter-banner">
        <h3 className="card-v__counter-banner-header">Score</h3>
        <span className="card-v__counter-banner-count">{score}</span>
      </div>
    </section>
  );
}

// ArchiveCard component
export function ArchiveCard({
  archive,
  description,
  total_badge,
  total_words,
  bannerSrc,
}) {
  return (
    <section className="card card-v card-archive">
      <div className="card-v__group">
        <div className="card-v__banner-container">
          <img
            src={bannerSrc}
            className="card-v__banner"
            alt="Student Status Banner"
          />
        </div>
        <div className="card-v__info-container">
          <h2 className="card-v__title">{archive}</h2>
          <span className="card-v__description">{description}</span>
        </div>
      </div>

      <div className="card-v__counter-banner">
        <h3 className="card-v__counter-banner-header">Badges</h3>
        <span className="card-v__counter-banner-count">{total_badge}</span>
      </div>

      <div className="card-v__counter-banner">
        <h3 className="card-v__counter-banner-header">Words</h3>
        <span className="card-v__counter-banner-count">{total_words}</span>
      </div>
    </section>
  );
}

// CardCreate component
export function CardCreate({ title }) {
  return (
    <div className="card card-h card--dotted">
      <h1 className="card--dotted-title">{title}</h1>
    </div>
  );
}

// Main Card component
export default function Card() {
  return (
    <main className="card-container">
      {/* You can use these components in other parts of your application */}
      <GameCard
        title="Adventure"
        description="Lorem ipsum"
        bannerSrc="./assets/banner/banner_adventure.webp"
        counterHeader="Floors Completed"
        counterCount={1}
      />
      <RoomCard
        title="Game Name"
        description="Teacher"
        bannerSrc="./assets/banner/banner_adventure.webp"
        counterHeader="Words Completed"
        counterCount={1}
      />
      <TeacherStatusCard
        username="Teacher1"
        time="10:00 AM"
        bannerSrc="./assets/banner/banner_adventure.webp"
      />
      <StudentStatusCard
        username="Student1"
        time="10:30 AM"
        bannerSrc="./assets/banner/banner_adventure.webp"
        score={90}
      />

      <ArchiveCard
        archive="Archive"
        description="Conquer the Towers! Collect Words and Badges in Adventure Mode"
        total_badge={4}
        total_words={4}
        bannerSrc="./assets/banner/banner_adventure.webp"
      />
      <CardCreate title="+Create" />

      <section className="card card-h card-settings">
        <h1 className="card-settings__header">Settings</h1>

        <input
          className="card-settings__input input input-box "
          placeholder="Update Room Name"
        />

        <div className="card-settings__button">
          <button className="btn btn--small btn--danger">Delete</button>
          <button className="btn btn--small btn--primary">Save</button>
        </div>
      </section>
    </main>
  );
}
