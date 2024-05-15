import React, { useState, useEffect } from "react";
import "./settings.css";
import { fetchUserData, UPDATE_USER_ENDPOINT } from "./api";

export default function Settings() {
  const [activeTab, setActiveTab] = useState("Account Information");
  const [userData, setUserData] = useState(null);
  const [error, setError] = useState(null);
  const [storedUserDetails, setStoredUserDetails] = useState(null);

  useEffect(() => {
    const fetchLoggedInUserDetails = async () => {
      try {
        const userDetailsFromSessionStorage = JSON.parse(sessionStorage.getItem("userDetails"));
        setStoredUserDetails(userDetailsFromSessionStorage);
        if (userDetailsFromSessionStorage) {
          const loggedInUserData = await fetchUserData(userDetailsFromSessionStorage.userID);
          setUserData(loggedInUserData);
        } else {
          // Handle case where user details are not available
        }
      } catch (error) {
        console.error("Error fetching user data:", error);
        setError("Failed to fetch user data");
      }
    };

    fetchLoggedInUserDetails();
  }, []);

  const handleTabClick = (tab) => {
    setActiveTab(tab);
  };

  const handleSaveChanges = async () => {
    try {
      if (storedUserDetails) {
        const updatedUserData = {
          username: storedUserDetails.username,
          firstname: storedUserDetails.firstname,
          lastname: storedUserDetails.lastname
        };
  
        // Check if first name and last name fields are not empty
        const updatedFirstName = document.getElementById("firstname").value;
        const updatedLastName = document.getElementById("lastname").value;
  
        if (updatedFirstName.trim() !== "" && updatedLastName.trim() !== "") {
          updatedUserData.firstname = updatedFirstName;
          updatedUserData.lastname = updatedLastName;
  
          // Call UPDATE_USER_ENDPOINT with PUT method
          await UPDATE_USER_ENDPOINT(storedUserDetails.userID, updatedUserData, "PUT");
  
          console.log("User details updated successfully!");
        } else {
          console.error("First name and last name cannot be empty.");
        }
      } else {
        console.error("Stored user details not available.");
      }
    } catch (error) {
      console.error("Error updating user details:", error);
    }
  };
  
  

  const renderContent = () => {
    switch (activeTab) {
      case "Account Information":
        return (
          <div className="tab-content">
            <div className="account-container">
              <p>Account Information</p>
              {userData && storedUserDetails && (
                <div className="text-container">
                  <input
                    type="text"
                    className="input input-line input-line--light textfield"
                    // placeholder={`Username: ${storedUserDetails.username || ""}`}
                    value={storedUserDetails.username || ""}
                    readOnly
                  />
                  <input
                    type="text"
                    className="input input-line input-line--light textfield"
                    placeholder={`Firstname: ${storedUserDetails.firstname}`}
                    
                  />
                  <input
                    type="text"
                    className="input input-line input-line--light textfield"
                    placeholder={`Lastname: ${storedUserDetails.lastname}`}
      
                  />
                </div>
              )}
              <button className="btn btn--medium btn--primary btn-save" onClick={handleSaveChanges}>
                Save Changes
              </button>
            </div>
          </div>
        );
      case "Change Password":
        return (
          <div className="tab-content ">
            <div className="password-container">
              <p>Change Password</p>
              <div className="text-container-password">
                <input
                  type="password"
                  className="input input-line input-line--light textfield"
                  placeholder="Current Password"
                />
                <input
                  type="password"
                  className="input input-line input-line--light textfield"
                  placeholder="New Password"
                />
                <input
                  type="password"
                  className="input input-line input-line--light textfield"
                  placeholder="Confirm New Password"
                />
              </div>
              <button className="btn btn--medium btn--primary btn-save">
                Save Changes
              </button>
            </div>
          </div>
        );
      default:
        return <div className="tab-content">Select a tab</div>;
    }
  };

  return (
    <main className="tab-container">
      <section className="tab-section">
        <div className="tab-btn-container">
          <div
            className={`tab-btn ${
              activeTab === "Account Information" ? "active" : ""
            }`}
            onClick={() => handleTabClick("Account Information")}
          >
            Account Information
          </div>
          <div
            className={`tab-btn ${
              activeTab === "Change Password" ? "active" : ""
            }`}
            onClick={() => handleTabClick("Change Password")}
          >
            Change Password
          </div>
        </div>

        {error && <div>Error: {error}</div>}
        {renderContent()}
      </section>
    </main>
  );
}
