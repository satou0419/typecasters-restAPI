import "./card.css";
import "./button.css";

export default function Card() {
  return (
    <main className="card-container">
      {/* CardSetting1F */}
      <section className="card card-setting card-setting--1f">
        <h2 className="card-setting__heading">Settings</h2>

        <div className="card-setting__input-container">
          <input
            type="text"
            className="input input-box"
            placeholder="Update Room Name"
          />

          <div className="card-setting__btn-group">
            <button className="btn btn--small btn--danger">Delete</button>
            <button className="btn btn--small btn--primary">Save</button>
          </div>
        </div>
      </section>

      {/* CardSettingF2 */}
      <section className="card card-setting card-setting--2f">
        <h2 className="card-setting__heading">Settings</h2>

        <div className="card-setting__input-container">
          <input
            type="text"
            className="input input-box"
            placeholder="Update Simulation Name"
          />

          <input
            type="text"
            className="input input-box"
            placeholder="Update Time"
          />

          <div className="card-setting__btn-group">
            <button className="btn btn--small btn--danger">Delete</button>
            <button className="btn btn--small btn--primary">Save</button>
          </div>
        </div>
      </section>

      {/* CardGame */}
      <section className="card card-game">
        <div className="card-game__banner">
          <img src="./assets/banner/banner_simulation.png" />
          <img src="./assets/banner/banner_adventure.webp" />
        </div>

        <div className="card__progress">
          <h2 className="card__progress-title">Floors Completed</h2>
          <span className="card__progress-value">1</span>
        </div>

        <div className="card__description">
          <h1 className="card__title">Adventure</h1>
          <span className="card__content">
            Lorem ipsum dolor sit amet, consectetur adipiscing
          </span>
        </div>
      </section>

      {/* CardSimulation */}
      <section className="card card-simulation">
        <div className="card-simulation__banner">
          {/* <img src="./assets/banner/banner_simulation.png" /> */}
          <img src="./assets/banner/banner_adventure.webp" />
        </div>

        <div className="card__description">
          <h1 className="card__title">Class Name</h1>
          <span className="card__content">Teacher</span>
        </div>

        <div className="card__progress push-top">
          <h2 className="card__progress-title">Students</h2>
          <span className="card__progress-value">4</span>
        </div>
      </section>

      {/* CardCreate */}
      <section className="card card-simulation card-create">
        <h1 className="card__title">+Create</h1>
      </section>

      {/* CardStatus */}
      <section className="card card-status">
        <div className="card-status__info">
          <div className="card-status__banner">
            <img src="./assets/banner/banner_adventure.webp" />
          </div>

          <div className="card-status__description">
            <h1 className="card__title">Username</h1>
            <span className="card__content">Time</span>
          </div>
        </div>
        <div className="card__progress status-progress">
          <h2 className="card__progress-title">Score</h2>
          <span className="card__progress-value">4</span>
        </div>
      </section>

      {/* CardArchive */}
      <section className="card card-archive">
        <div className="card-status__info push-down">
          <div className="card-archive__banner-container">
            <div className="card-archive__banner">
              <img src="./assets/banner/banner_adventure.webp" />
            </div>
          </div>
          <div className="card-status__description">
            <h1 className="card__title">Archive</h1>
            <span className="card__content">
              Conquer the Towers! Collect Words and Badges in Adventure Mode
            </span>
          </div>
        </div>

        <div className="card__progress--container">
          <div className="card__progress">
            <h2 className="card__progress-title push-top ">Badges</h2>
            <span className="card__progress-value">4</span>
          </div>

          <div className="card__progress">
            <h2 className="card__progress-title push-top ">Words</h2>
            <span className="card__progress-value">4</span>
          </div>
        </div>
      </section>
    </main>
  );
}
