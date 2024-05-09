import "./components/root.css";
import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import { BrowserRouter } from "react-router-dom";
import App from "./App";
import Button from "./components/Button";
import Card from "./components/Card";
import Login from "./Login";
import StudentRoom from "./StudentRoom";
import Tab from "./components/Tab";
import InventoryShop from "./InventoryShop";
import GameplayAdventureSpelling from "./GameplayAdventureSpelling";
import Animation from "./components/Animation";
import AdventureMode from "./AdventureMode";
import CreateRoom from "./CreateRoom";
import Input from "./components/Input";
import DialogBox from "./components/DialogBox";
import Navigation from "./components/Navigation";

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </React.StrictMode>
);
