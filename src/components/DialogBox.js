import React, { useState } from "react";
import "./styles.css";

const DialogBox = ({ title, content, buttons }) => {
  const handleButtonClick = (buttonCallback) => {
    // Execute the button-specific logic
    if (buttonCallback) {
      buttonCallback();
    }

    // Close the dialog after handling the button click
  };
  return (
    <div className="outlined-container">
      <svg
        xmlns="http://www.w3.org/2000/svg"
        xlinkHref="http://www.w3.org/1999/xlink"
        width="528"
        height="278"
        xmlSpace="preserve"
        overflow="hidden"
      >
        <defs>
          <clipPath id="clip0">
            <rect x="1400" y="184" width="528" height="278" />
          </clipPath>
        </defs>
        <g clipPath="url(#clip0)" transform="translate(-1400 -184)">
          <path
            id="dialog-screen"
            d="M1434.06 198 1537.54 198 1541.85 202.91 1609.62 202.91 1613.92 198 1638.86 198 1638.08 200.106 1658.9 200.106 1659.68 198 1739.78 198 1763.74 221.568 1850.61 221.568 1867.24 237.919 1920 237.919 1920 301.927 1919.24 301.927 1919.24 353.238 1920 353.238 1920 407.007 1874.88 453 1818.69 453 1808.78 443.252 1805.03 443.252 1804.55 442.879 1729.99 442.879 1717.15 453 1639.62 453 1614.19 427.995 1531.6 427.995 1506.17 453 1408 453 1408 355.043 1420.66 343.487 1420.66 268.735 1410.58 259.533 1410.58 221.602 1434.06 198Z"
            fill="#d9d9d9"
            stroke="#D9D9D9"
            strokeWidth="1.14583"
            fillRule="evenodd"
          />
          <path />

          <path
            id="yourPathId"
            className="glitch-path"
            d="M1434.17 199.232 1410.7 222.788 1410.7 260.645 1420.78 269.83 1420.78 344.437 1408.12 355.971 1408.12 453.739 1506.24 453.739 1531.66 428.782 1614.21 428.782 1639.63 453.739 1717.13 453.739 1729.96 443.638 1804.49 443.638 1804.96 444.009 1808.72 444.009 1818.62 453.739 1874.79 453.739 1919.88 407.834 1919.88 354.169 1919.12 354.169 1919.12 302.957 1919.88 302.957 1919.88 239.074 1867.15 239.074 1850.53 222.754 1763.7 222.754 1739.74 199.232 1659.69 199.232 1658.9 201.333 1638.09 201.333 1638.87 199.232 1613.95 199.232 1609.64 204.132 1541.9 204.132 1537.6 199.232ZM1409.36 198.128 1409.36 207.856 1418.95 198.128ZM1514.39 184.5 1559.48 184.5 1564.38 191.739 1663.58 191.739 1663.91 192.421 1743.1 192.421 1766.96 215.915 1854.64 215.915 1870.57 231.597 1926.8 231.597 1926.8 295.427 1927.5 295.626 1927.5 328.975 1927.13 329.08 1927.13 410.32 1875.94 461.5 1638.19 461.5 1612.4 436.115 1534.43 436.115 1508.65 461.5 1400.5 461.5 1400.5 352.579 1413.38 339.505 1413.38 274.453 1403.48 264.405 1403.48 219.923 1404.18 219.215 1404.18 193.321 1430.99 193.321 1431.53 192.523 1508.96 192.523Z"
            stroke="#4A2FFC"
            strokeWidth="3"
            strokeMiterlimit="8"
            fill="#FFFFFF"
            fillRule="evenodd"
            // style={{ animation: "runToBorder 2s infinite linear" }}
          />
          <rect
            x="1405.5"
            y="279.5"
            width="2.99976"
            height="51.9999"
            stroke="#4A2FFC"
            strokeWidth="1.14583"
            strokeMiterlimit="8"
            fill="#FFFFFF"
          />
          <rect
            x="1534.5"
            y="439.5"
            width="79.0001"
            height="5.99985"
            stroke="#4A2FFC"
            strokeWidth="1.14583"
            strokeMiterlimit="8"
            fill="#FFFFFF"
          />
          <rect
            x="1524.5"
            y="450.5"
            width="93.9999"
            height="5.99985"
            stroke="#4A2FFC"
            strokeWidth="1.14583"
            strokeMiterlimit="8"
            fill="#FFFFFF"
          />
          <path
            d="M0 19.2173 18.9445 0 44.4459 0 63.3904 19.2173Z"
            stroke="#4A2FFC"
            strokeWidth="1.14583"
            strokeMiterlimit="8"
            fill="#FFFFFF"
            fillRule="evenodd"
            transform="matrix(0.708507 -0.705703 -0.705703 -0.708507 1892.75 472.281)"
          />
          <path
            d="M0 5.99984 5.85828 0 43 0 37.1417 5.99984Z"
            stroke="#4A2FFC"
            strokeWidth="1.14583"
            strokeMiterlimit="8"
            fill="#FFFFFF"
            fillRule="evenodd"
            transform="matrix(-1 0 0 1 1911.5 219.5)"
          />
        </g>
      </svg>

      <div className="component-holder">
        <h2 className="dialog-title">{title}</h2>
        <p className="dialog-text">{content}</p>
        <div className="dialog-buttons">
          {buttons.map((button, index) => (
            <button
              key={index}
              className="dialog-button btn btn--medium btn--primary"
              onClick={() => handleButtonClick(button.onClick)}
            >
              {button.label}
            </button>
          ))}
        </div>
      </div>
    </div>
  );
};

export default DialogBox;
