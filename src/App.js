import "./App.css";

import { Route, Routes } from "react-router-dom";
import Login from "./Login";
import Animation from "./components/Animation";
import Button from "./components/Button";
import Card from "./components/Card";
import Input from "./components/Input";
import Navigation from "./components/Navigation";
import Dashboard from "./Dashboard";
import Registration from "./Registration";
import DialogBox from "./components/DialogBox";

function App() {
  return (
    <>
      {/* <Navigation />
      <Routes>
        <Route path="/dashboard" element={<Dashboard />} />
      </Routes> */}
      <Login />
    </>
  );
}

export default App;
