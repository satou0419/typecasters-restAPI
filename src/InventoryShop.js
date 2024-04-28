import React, { useState } from "react";
import "./components/tab.css";
import "./inventory_shop.css";
import { CardItemm } from "./components/Card";

export default function InventoryShop() {
  const [activeTab, setActiveTab] = useState("inventory"); // Default to "archive"
  const [selectedWord, setSelectedWord] = useState(""); // State to store the selected word

  // Function to handle tab click
  const handleTabClick = (tab) => {
    setActiveTab(tab);
  };

  // Function to handle word click
  const handleWordClick = (word) => {
    setSelectedWord(word); // Update the selected word state
  };

  // Conditional rendering based on activeTab and selectedWord
  const renderContent = () => {
    switch (activeTab) {
      case "inventory":
        return (
          <div className="tab-content">
            <section></section>
          </div>
        );
      case "shop":
        return (
          <div className="tab-content shop-wrapper">
            <section className="shop-section">
              <CardItemm
                bannerSrc="./assets/items/item_medical_kit.webp"
                itemName="Medkit"
                itemBtnPrice={150}
              />
              <CardItemm
                bannerSrc="./assets/items/item_medical_kit.webp"
                itemName="Medkit"
                itemBtnPrice={150}
              />
              <CardItemm
                bannerSrc="./assets/items/item_medical_kit.webp"
                itemName="Medkit"
                itemBtnPrice={150}
              />

              <CardItemm
                bannerSrc="./assets/items/item_medical_kit.webp"
                itemName="Medkit"
                itemBtnPrice={150}
              />

              <CardItemm
                bannerSrc="./assets/items/item_medical_kit.webp"
                itemName="Medkit"
                itemBtnPrice={150}
              />

              <CardItemm
                bannerSrc="./assets/items/item_medical_kit.webp"
                itemName="Medkit"
                itemBtnPrice={150}
              />
            </section>
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
            className={`tab-btn ${activeTab === "inventory" ? "active" : ""}`}
            onClick={() => handleTabClick("inventory")}
          >
            Inventory
          </div>
          <div
            className={`tab-btn ${activeTab === "shop" ? "active" : ""}`}
            onClick={() => handleTabClick("shop")}
          >
            Shop
          </div>
        </div>

        {renderContent()}
      </section>
    </main>
  );
}
