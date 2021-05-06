const CURR_USER_KEY = "CURR_USER_KEY";

export const getCurrUser = () => {
  try {
    return JSON.parse(window.localStorage.getItem(CURR_USER_KEY));
  } catch (e) {
    console.log("Failed to retrieve current user from local storage");
    return null;
  }
};

export const saveCurrUser = (currUser) => {
  try {
    window.localStorage.setItem(CURR_USER_KEY, JSON.stringify(currUser));
  } catch (e) {
    console.log(e);
  }
};

export const setDefaultCurrUser = () => {
  try {
    window.localStorage.setItem(
      CURR_USER_KEY,
      JSON.stringify({
        id: Number,
        email: "",
        role: null,
        avatar: "",
      })
    );
  } catch (e) {
    console.log(e);
  }
};

export const destroyCurrUser = () => {
  window.localStorage.removeItem(CURR_USER_KEY);
};

export default {
  getCurrUser,
  saveCurrUser,
  setDefaultCurrUser,
  destroyCurrUser,
};
