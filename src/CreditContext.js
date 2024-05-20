import React, { createContext, useState, useContext } from "react";

const CreditContext = createContext();

export const useCredit = () => useContext(CreditContext);

export const CreditProvider = ({ children }) => {
  const [credit, setCredit] = useState();

  const updateCredit = (amount) => {
    setCredit((prevCredit) => prevCredit + amount);
  };

  const setInitialCredit = (amount) => {
    setCredit(amount);
  };

  return (
    <CreditContext.Provider value={{ credit, updateCredit, setInitialCredit }}>
      {children}
    </CreditContext.Provider>
  );
};
