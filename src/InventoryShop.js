import React, { useState, useEffect } from "react";
import "./components/tab.css";
import "./inventory_shop.css";
import { CardItem } from "./components/Card";
import { GET_ALL_ITEMS_ENDPOINT, BUY_ITEM_ENDPOINT } from "./api";
import { useCredit } from "./CreditContext"; // Import useCredit hook to update credit
import { Modal, Message } from './components/Modal';

export default function InventoryShop() {
  const [activeTab, setActiveTab] = useState("inventory");
  const [items, setItems] = useState([]);
  const [selectedItem, setSelectedItem] = useState(null);
  const [userID, setUserID] = useState();
  const [isBuying, setIsBuying] = useState(false);
  const { updateCredit } = useCredit(); // Access updateCredit function from the context
  const [isConfirmingPurchase, setIsConfirmingPurchase] = useState(false);
  const [showMessage, setShowMessage] = useState("");
  const [isEnoughCred, setIsEnoughCred] = useState(false);

  const { credit } = useCredit(); // Access credit from the context
  


  const showModal = (item) =>{
    // setIsConfirmingPurchase(true);
    setShowMessage("Are you sure you want to purchase ");
    // console.log("Clickeddddd")
    // console.log(selectedItem.item_price)
    // console.log("Credit: ", credit)
    if (credit >= item.item_price) {
      setSelectedItem(item);
      setIsConfirmingPurchase(true);
    } else {
      console.log("Insufficient credit. You cannot purchase this item.");
      setIsEnoughCred(true);
    }
  }

  useEffect(() => {
    const storedUserDetails = JSON.parse(sessionStorage.getItem("userDetails"));
    setUserID(storedUserDetails.userID);

    const fetchItems = async () => {
      try {
        const response = await fetch(GET_ALL_ITEMS_ENDPOINT);
        if (response.ok) {
          const data = await response.json();
          setItems(data);
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

  const handleItemClick = (item) => {
    setSelectedItem(item);
  };

  const handleBuyItem = async (itemName) => {
    if (isBuying) return;

    setIsBuying(true);

    try {
      const selectedItem = items.find((item) => item.item_name === itemName);

      if (selectedItem) {
         // Fetch user's credit from wherever it's stored
      // Check if user's credit is sufficient for purchase
      if (credit >= selectedItem.item_price) {
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
          updateCredit(-selectedItem.item_price); // Update credit after successful purchase
        } else {
          console.error("Failed to buy item");
        }
      } else {
        // setShowMessage("Insufficient credit. You cannot purchase this item.");
        console.log("Not enough credit")
      }
      } else {
        console.error("Item not found");
      }
    } catch (error) {
      console.error("Error:", error);
    } finally {
      setIsBuying(false);
    }
    setIsConfirmingPurchase(false);
  };

  const cancelPurchase = () =>{
    setIsConfirmingPurchase(false);
  }

  return (
    <main className="tab-container">
      <section className="tab-section">
        <div className="tab-btn-container">
          <div
            className={`tab-btn ${activeTab === "inventory" ? "active" : ""}`}
            onClick={() => setActiveTab("inventory")}
          >
            Inventory
          </div>
          <div
            className={`tab-btn ${activeTab === "shop" ? "active" : ""}`}
            onClick={() => setActiveTab("shop")}
          >
            Shop
          </div>
        </div>
        {activeTab === "inventory" && (
          <div className="tab-content">
            <section className="inventory-section">
              {/* Inventory content */}
            </section>
          </div>
        )}
        {activeTab === "shop" && (
          <div className="tab-content shop-wrapper">
            <section className="shop-section">
              {items.map((item) => (
                <CardItem
                  key={item.item_id}
                  bannerSrc={`./assets/items/${item.image_path}`}
                  itemName={item.item_name} 
                  itemBtnPrice={item.item_price}
                  onClick={() => showModal(item)}
                  disabled={isBuying}
                />
              ))}
               {isConfirmingPurchase && (
                <Modal
                  cancelButtonLabel="Cancel"
                  confirmButtonLabel="Confirm"
                  modalTitle="Confirm Purchase"
                  modalContent={`${showMessage} ${selectedItem.item_name}?`}
                  confirmClick={() =>handleBuyItem(selectedItem.item_name)}
                  cancelClick={() => cancelPurchase()}         
                />
              )}
              {isEnoughCred && (
                <Message
                confirmButtonLabel="Okay"
                modalTitle="Insufficient Credit"
                modalContent="You do not have enough credit to purchase this item."
                confirmClick={() => setIsEnoughCred(false)}         
                />
              )}
            </section>
          </div>
        )}
      </section>
    </main>
  );
}
