import React, { createContext, useState, useContext, useEffect } from "react";
import { fetchUserData } from "./api";

const CreditContext = createContext();

export const useCredit = () => useContext(CreditContext);

export const CreditProvider = ({ children }) => {
  const [credit, setCredit] = useState(0);
  const [userData, setUserData] = useState(null);

  useEffect(() => {
    const storedUserDetails = JSON.parse(sessionStorage.getItem("userDetails"));
    if (storedUserDetails) {
      fetchUserData(storedUserDetails.userID)
        .then((data) => {
          setUserData(data);
          setCredit(data.credit_amount); // Set the initial credit amount from user data
        })
        .catch((error) => console.error("Error fetching user data:", error));
    }
  }, []);

  const updateCredit = (amount) => {
    setCredit((prevCredit) => prevCredit - amount);
  };

  return (
    <CreditContext.Provider value={{ credit, updateCredit }}>
      {children}
    </CreditContext.Provider>
  );
};
