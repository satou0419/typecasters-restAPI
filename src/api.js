// Define your base URL
const BASE_URL = "http://localhost:8080";

// Define your API endpoints
export const LOGIN_ENDPOINT = `${BASE_URL}/user/login`;
export const REGISTER_ENDPOINT = `${BASE_URL}/user/register`;
export const LOGOUT_ENDPOINT = `${BASE_URL}/user/logout`;
export const GET_ALL_ITEMS_ENDPOINT = `${BASE_URL}/item/get_all_items`;
export const GET_USER_DETAILS_ENDPOINT = `${BASE_URL}/user_details/get_user_detail?user_id=`;

// Function to fetch user details
export const fetchUserData = async (userID) => {
  try {
    const response = await fetch(`${GET_USER_DETAILS_ENDPOINT}${userID}`);
    if (!response.ok) {
      throw new Error("Failed to fetch user data");
    }
    return response.json();
  } catch (error) {
    console.error("Error fetching user data:", error);
    throw error;
  }
};
