// Define your base URL
// const BASE_URL = "http://localhost:8080";
const BASE_URL = "https://api-typecasters.azurewebsites.net";
// Define your API endpoints
export const LOGIN_ENDPOINT = `${BASE_URL}/user/login`;
export const REGISTER_ENDPOINT = `${BASE_URL}/user/register`;
export const LOGOUT_ENDPOINT = `${BASE_URL}/user/logout`;
export const GET_ALL_ITEMS_ENDPOINT = `${BASE_URL}/item/get_all_items`;
export const GET_ALL_FLOORS_ENDPOINT = `${BASE_URL}/floor/get_all_floors`;
export const GET_ENEMIES_BY_FLOOR_ID_ENDPOINT = `${BASE_URL}/adventure_enemy/get_enemy_by_floor_id`;
export const GET_USER_ITEMS_ENDPOINT = `${BASE_URL}/user_item/get_user_items_by/`;
export const GET_USER_DETAILS_ENDPOINT = `${BASE_URL}/user_details/get_user_detail?user_id=`;
export const INSERT_WORD_ARCHIVE = `${BASE_URL}/archive_words/insert`;
export const UPDATE_USER_ENDPOINT = `${BASE_URL}/user/update_user?uid=`;
export const UPDATE_USER_PROGRESS_ENDPOINT = `${BASE_URL}/tower_progress/update_user_progress?user_prog_id=`;
export const BUY_ITEM_ENDPOINT = `${BASE_URL}/user_item/buy_item_single`;
export const GET_ALL_ITEMS = `${BASE_URL}/item/get_all_items`;
export const GET_ARCHIVE_WORD_ENDPOINT = `${BASE_URL}/archive_words/view_by_id/`;
export const CREATE_ROOM = `${BASE_URL}/room/insert`;

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
