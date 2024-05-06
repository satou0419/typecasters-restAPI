import React, { useState, useEffect } from "react";
import "./components/tab.css";
import "./inventory_shop.css";
import { CardItem } from "./components/Card";
import { GET_ALL_ITEMS_ENDPOINT } from "./api"; // Import the endpoint for fetching all items

export default function InventoryShop() {
  const [activeTab, setActiveTab] = useState("inventory"); // Default to "archive"
  const [items, setItems] = useState([]); // State to store items
  const [selectedItem, setSelectedItem] = useState(""); 

  useEffect(() => {
    // Fetch all items when component mounts
    const fetchItems = async () => {
      try {
        const response = await fetch(GET_ALL_ITEMS_ENDPOINT);
        if (response.ok) {
          const data = await response.json();
          setItems(data);
        } else {
          console.error("Failed to fetch items");
        }
      } catch (error) {
        console.error("Error:", error);
      }
    };

    fetchItems();
  }, []);

  // Function to handle tab click
  const handleTabClick = (tab) => {
    setActiveTab(tab);
  };

  const handleItemClick = (itemName) => {
    setSelectedItem(itemName);
  };  

  // Conditional rendering based on activeTab
  const renderContent = () => {
    switch (activeTab) {
      case "inventory":
        return (
          <div className="tab-content">
            <section className="inventory-section">
              <div className="inventory-item-selection-box">
              <div
                className={`inventory-item-box ${selectedItem === "Medkit" ? "active" : ""}`}
                onClick={() => handleItemClick("Medkit")}
              >
                <div className="inventory-item-sub-box"></div>
                <div className="item-description-box">
                  <div className="item-title">Medkit</div>
                  <div className="item-description">Lorem ipsum dolor sit amet</div>
                </div>
              </div>
              <div
                className={`inventory-item-box ${selectedItem === "Bandage" ? "active" : ""}`}
                onClick={() => handleItemClick("Bandage")}
              >
                <div className="inventory-item-sub-box"></div>
                <div className="item-description-box">
                  <div className="item-title">Bandage</div>
                  <div className="item-description">Lorem ipsum dolor sit amet</div>
                </div>
              </div>
              <div
                className={`inventory-item-box ${selectedItem === "Unusual Battery" ? "active" : ""}`}
                onClick={() => handleItemClick("Unusual Battery")}
              > 
                <div className="inventory-item-sub-box"></div>
                <div className="item-description-box">
                  <div className="item-title">Unusual Battery</div>
                  <div className="item-description">Lorem ipsum dolor sit amet</div>
                </div>
              </div>

              </div>
              <div className="inventory-item-display-box">
                <div className="inventory-item-picture">
                  
                </div>
                <div className="inventory-item-title">
                    MEDKIT
                  </div>
                  <div className="inventory-item-description">
                  "Lorem ipsum dolor sit amet, consectetur adipiscing elit, 
                  sed do eiusmod tempor incididunt ut labore et dolore 
                  magna aliqua.
                  </div>
              </div>
            </section>
          </div>
        );
      case "shop":
        return (
          <div className="tab-content shop-wrapper">
            <section className="shop-section">
              {items.map((item) => (
                <CardItem
                  key={item.item_id}
                  bannerSrc={`./assets/items/${item.image_path}`}
                  itemName={item.item_name}
                  itemBtnPrice={item.item_price}
                />
              ))}
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
