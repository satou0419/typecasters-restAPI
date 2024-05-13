// Import necessary dependencies
import React, { useState, useEffect } from "react";
import "./components/tab.css";
import "./inventory_shop.css";
import { CardItem } from "./components/Card";
import { GET_ALL_ITEMS_ENDPOINT, BUY_ITEM_ENDPOINT } from "./api";

// Define InventoryShop component
export default function InventoryShop() {
  // Define state variables
  const [activeTab, setActiveTab] = useState("inventory");
  const [items, setItems] = useState([]);
  const [selectedItem, setSelectedItem] = useState(null); // Change initial state to null
  const [userID, setUserID] = useState();
  const [isBuying, setIsBuying] = useState(false); // State to track if an item is being bought

  // Fetch items and set user ID on component mount
  useEffect(() => {
    const storedUserDetails = JSON.parse(sessionStorage.getItem("userDetails"));
    setUserID(storedUserDetails.userID);

    const fetchItems = async () => {
      try {
        const response = await fetch(GET_ALL_ITEMS_ENDPOINT);
        if (response.ok) {
          const data = await response.json();
          setItems(data);
          // Set the first item as the selected item
          if (data.length > 0) {
            setSelectedItem(data[0]);
          }
        } else {
          console.error("Failed to fetch items");
        }
      } catch (error) {
        console.error("Error:", error);
      }
    };

    fetchItems();
  }, []);

  // Define click handler for tabs
  const handleTabClick = (tab) => {
    setActiveTab(tab);
  };

  // Define click handler for inventory items
  const handleItemClick = (item) => {
    setSelectedItem(item); // Set the selected item
  };

  // Define function to handle item purchase
  const buyItem = async (itemName) => {
    if (isBuying) return; // If already buying, prevent multiple clicks

    setIsBuying(true); // Set isBuying to true to indicate the buying process has started

    try {
      const selectedItem = items.find((item) => item.item_name === itemName);

      if (selectedItem) {
        const response = await fetch(
          `${BUY_ITEM_ENDPOINT}/${userID}/${selectedItem.itemId}`,
          {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
            },
          }
        );

        if (response.ok) {
          console.log("Item bought successfully!");
          // You might want to update the inventory or do something else upon success
        } else {
          console.error("Failed to buy item");
        }
      } else {
        console.error("Item not found");
      }
    } catch (error) {
      console.error("Error:", error);
    } finally {
      setIsBuying(false); // Reset isBuying to false after the buying process is completed
    }
  };

  // Render content based on active tab
  const renderContent = () => {
    switch (activeTab) {
      case "inventory":
        return (
          <div className="tab-content">
            <section className="inventory-section">
              <div className="inventory-item-selection-box">
                {items.map((item) => (
                  <div
                    key={item.item_id}
                    className={`inventory-item-box ${
                      selectedItem === item ? "active" : ""
                    }`}
                    onClick={() => handleItemClick(item)}
                  >
                    <div className="inventory-item-sub-box">
                      <img
                        src={`./assets/items/${item.image_path}`}
                        alt={item.item_name}
                      />
                    </div>
                    <div className="item-description-box">
                      <div className="item-title">{item.item_name}</div>
                      <div className="item-description">
                        {item.item_description}
                      </div>
                    </div>
                  </div>
                ))}
              </div>
              <div className="inventory-item-display-box">
                {selectedItem && (
                  <>
                    <div className="inventory-item-picture">
                      <img
                        src={`./assets/items/${selectedItem.image_path}`}
                        alt={selectedItem.item_name}
                      />
                    </div>
                    <div className="inventory-item-title">
                      {selectedItem.item_name}
                    </div>
                    <div className="inventory-item-description">
                      {selectedItem.item_description}
                    </div>
                  </>
                )}
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
                  onClick={() => buyItem(item.item_name)}
                  disabled={isBuying}
                />
              ))}
            </section>
          </div>
        );
      default:
        return <div className="tab-content">Select a tab</div>;
    }
  };

  // Render InventoryShop component
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
