import React, { useState, useEffect } from "react";
import "./components/tab.css";
import "./inventory_shop.css";
import { CardItem } from "./components/Card";
import { GET_ALL_ITEMS_ENDPOINT } from "./api"; // Import the endpoint for fetching all items

export default function InventoryShop() {
  const [activeTab, setActiveTab] = useState("inventory"); // Default to "archive"
  const [items, setItems] = useState([]); // State to store items

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

  // Conditional rendering based on activeTab
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
