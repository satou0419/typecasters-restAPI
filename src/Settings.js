import React, { useState, useEffect } from "react";
import "./settings.css";
import { fetchUserData, UPDATE_USER_ENDPOINT } from "./api";

export default function Settings() {
  const [activeTab, setActiveTab] = useState("Account Information");
  const [userData, setUserData] = useState(null);
  const [error, setError] = useState(null);
  const [storedUserDetails, setStoredUserDetails] = useState(null);
  const [password, setPassword] = useState("");
  const [username, setUserName] = useState("");
  const [lastname, setLastName] = useState("");
  const [firstname, setFirstName] = useState("");

  useEffect(() => {
    const fetchLoggedInUserDetails = async () => {
      try {
        const userDetailsFromSessionStorage = JSON.parse(
          sessionStorage.getItem("userDetails")
        );
        setStoredUserDetails(userDetailsFromSessionStorage);
        if (userDetailsFromSessionStorage) {
          const loggedInUserData = await fetchUserData(
            userDetailsFromSessionStorage.userID
          );
          setUserData(loggedInUserData);
          setFirstName(userDetailsFromSessionStorage.firstname || "");
          setLastName(userDetailsFromSessionStorage.lastname || "");
          setUserName(userDetailsFromSessionStorage.username || "");
          setPassword(userDetailsFromSessionStorage.password || "");
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
  // console.log(firstname);
  

  const handleSaveChanges = async () => {
    try {
      if (storedUserDetails) {
        const updatedUserData = {
          username: storedUserDetails.username,
          firstname: firstname,
          lastname: lastname,
          password: password
        };

        const updatedFirstName = document.getElementById("firstname").value;
        const updatedLastName = document.getElementById("lastname").value;
        const updatedPassword = password;
        console.log(password);

        if (updatedFirstName.trim() !== "") {
          updatedUserData.firstname = updatedFirstName;
        }

        if (updatedLastName.trim() !== "") {
          updatedUserData.lastname = updatedLastName;
        }
        updatedUserData.password = updatedPassword;

        const updateUserURL = `${UPDATE_USER_ENDPOINT}${storedUserDetails.userID}`;

        const response = await fetch(updateUserURL, {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(updatedUserData),
        });

        if (!response.ok) {
          throw new Error("Failed to update user details");
        }

        console.log("User details updated successfully!");

        // Update session storage with new user details
        const newStoredDetails = {
          ...storedUserDetails,
          firstname: updatedUserData.firstname,
          lastname: updatedUserData.lastname,
          password: updatedUserData.password
        };
        sessionStorage.setItem("userDetails", JSON.stringify(newStoredDetails));

        // Update the local state to reflect the changes
        setStoredUserDetails(newStoredDetails);
        setFirstName(updatedUserData.firstname);
        setLastName(updatedUserData.lastname);
        setPassword(updatedUserData.password);
      } else {
        console.error("Stored user details not available.");
      }
    } catch (error) {
      console.error("Error updating user details:", error);
    }
  };
  //handle to check the change password
  console.log(firstname, lastname, password);
  const handleChangePassword = async () => {
    try {
      const currentPassword = document.getElementById("currentPassword").value;
      const newPassword = document.getElementById("newPassword").value;
      const confirmPassword = document.getElementById("confirmPassword").value;
      const getfirstname = firstname;
      const getlastname = lastname;
      const getusername = username;
  
      // error if textfields are blank
      if (!currentPassword || !newPassword || !confirmPassword) {
        console.error("Please fill in all fields");
        return;
      }
      //error for matching new passwords
      if (newPassword !== confirmPassword) {
        console.error("New password and confirm password do not match");
        return;
      }
      //error for checking current password
      if(password !== currentPassword){
        console.error("Current password is invalid");
        return;
      }
  
      // Update password
      const updateUserURL = `${UPDATE_USER_ENDPOINT}${storedUserDetails.userID}`;
  
      const response = await fetch(updateUserURL, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          username: getusername,
          firstname: getfirstname,
          lastname: getlastname,
          password: newPassword
        }),
      });
  
      if (!response.ok) {
        throw new Error("Failed to update password");
      }
  
      console.log("Password updated successfully!");
  
      // Reset password fields
      document.getElementById("currentPassword").value = "";
      document.getElementById("newPassword").value = "";
      document.getElementById("confirmPassword").value = "";
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
                    value={username}
                    readOnly
                  />
                  <input
                    type="text"
                    className="input input-line input-line--light textfield"
                    value={firstname}
                    onChange={(e) => setFirstName(e.target.value)}
                    id="firstname"
                  />
                  <input
                    type="text"
                    className="input input-line input-line--light textfield"
                    value={lastname}
                    onChange={(e) => setLastName(e.target.value)}
                    id="lastname"
                  />
                  <input
                    type="password"
                    className="input input-line input-line--light textfield"
                    placeholder="Enter New password"
                    value={password}
                    id="password"
                    onChange={(e) => setPassword(e.target.value)}
                  />
                </div>
              )}
              <button
                className="btn btn--medium btn--primary btn-save"
                onClick={handleSaveChanges}
              >
                Save Changes
              </button>
            </div>
          </div>
        );
      case "Change Password":
        return (
          <div className="tab-content">
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
                  id="confirmPassword"
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
