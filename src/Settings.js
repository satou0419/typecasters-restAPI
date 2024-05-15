import React, { useState, useEffect } from "react";
import "./settings.css";
import { fetchUserData, UPDATE_USER_ENDPOINT } from './api';

export default function Settings() {
  const [activeTab, setActiveTab] = useState("Account Information");
  const [userData, setUserData] = useState(null);
  const [error, setError] = useState(null);
  const [storedUserDetails, setStoredUserDetails] = useState(null);
  // const [CurrentUsername, setCurrentUsername] = useState();
  // const [CurrentFirstname, setCurrentFirstname] = useState();
  // const [CurrentLastname, setCurrentLastname] = useState();
  // const [currentPassword, setCurrentPassword] = useState();

  useEffect(() => {
    const fetchLoggedInUserDetails = async () => {
      try {
        const userDetailsFromSessionStorage = JSON.parse(sessionStorage.getItem("userDetails"));
        setStoredUserDetails(userDetailsFromSessionStorage);
        if (userDetailsFromSessionStorage) {
          const loggedInUserData = await fetchUserData(userDetailsFromSessionStorage.userID);
          setUserData(loggedInUserData); 
          // console.log(userData);
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
          lastname: storedUserDetails.lastname,
        };
  
        const updatedFirstName = document.getElementById("firstname").value;
        const updatedLastName = document.getElementById("lastname").value;
        // Update the fields in updatedUserData only if they are not empty
        if (updatedFirstName.trim() !== "") {
          updatedUserData.firstname = updatedFirstName;
        }
        
        if (updatedLastName.trim() !== "") {
          updatedUserData.lastname = updatedLastName;
        }
        
        console.log("FIRSTNAME:", updatedUserData.firstname,"LASTNAME:", updatedUserData.lastname,);

        const updateUserURL = `${UPDATE_USER_ENDPOINT}${storedUserDetails.userID}`;
        // Call UPDATE_USER_ENDPOINT with PUT method
        const response = await fetch(updateUserURL, {
          method: "PUT",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify(updatedUserData)
        });
  
        if (!response.ok) {
          throw new Error("Failed to update user details");
        }
  
        console.log("User details updated successfully!");
      } else {
        console.error("Stored user details not available.");
      }
    } catch (error) {
      console.error("Error updating user details:", error);
    }
  };
  console.log(storedUserDetails);
  
  const handleChangePassword = async () => {
    try {
      const currentPassword = document.getElementById("currentPassword").value;
      const newPassword = document.getElementById("newPassword").value;
      const confirmNewPassword = document.getElementById("confirmNewPassword").value;
  
      // Check if the new password matches the confirm password
      if (newPassword !== confirmNewPassword) {
        throw new Error("New password and confirm password do not match");
      }
  
      // Prepare the request body
      const updatedPasswordData = {
        currentPassword: currentPassword,
        newPassword: newPassword
      };
      // Make sure all fields are filled
      if (!currentPassword || !newPassword || !confirmNewPassword) {
        throw new Error("Please fill in all fields");
      }
  
      // Call the backend endpoint to update the password
      const updateUserPasswordURL = `${UPDATE_USER_ENDPOINT}${storedUserDetails.userID}`;
      const response = await fetch(updateUserPasswordURL, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(updatedPasswordData)
      });
      console.log(updatedPasswordData);
      if (!response.ok) {
        throw new Error("Failed to update password");
      }
  
      console.log("Password updated successfully!");
    } catch (error) {
      console.error("Error updating password:", error);
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
                <div className="text-container" key={activeTab}>
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
                    placeholder={`Firstname: ${storedUserDetails.firstname }`}
                    id="firstname"
                  />
                  <input
                    type="text"
                    className="input input-line input-line--light textfield"
                    placeholder={`Lastname: ${storedUserDetails.lastname}`}
                    id="lastname"
                  />
                  {/* <input
                    type="password"
                    className="input input-line input-line--light textfield"
                    placeholder="Enter New password"
                    id="password"
                  /> */}
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
                  id="currentPassword"
                />
                <input
                  type="password"
                  className="input input-line input-line--light textfield"
                  placeholder="New Password"
                  id="newPassword"
                />
                <input
                  type="password"
                  className="input input-line input-line--light textfield"
                  placeholder="Confirm New Password"
                  id="confirmNewPassword"
                />
              </div>
              <button className="btn btn--medium btn--primary btn-save" onClick={handleChangePassword}>
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
