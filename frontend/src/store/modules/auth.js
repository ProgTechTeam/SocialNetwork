import {
  LOGIN_ERROR,
  LOGIN_REQUEST,
  LOGIN_SUCCESS,
  LOGOUT_REQUEST,
  REGISTRATION_ERROR,
  REGISTRATION_REQUEST,
  REGISTRATION_SUCCESS,
} from "../action-types";
import {
  destroyCurrUser,
  getCurrUser,
  saveCurrUser,
} from "../../core/services/auth.service";
import { auth, post } from "../../core/services/http.service";

const state = () => ({
  currentUser: getCurrUser(),
});

const getters = {
  isAuth: (state) => {
    return state.currentUser !== null;
  },
};

const mutations = {
  [LOGIN_SUCCESS]: (state, payload) => {
    state.currentUser = payload.currentUser;
    saveCurrUser(payload.currentUser);
  },
  [LOGIN_ERROR]: (state) => {
    state.currentUser = null;
    destroyCurrUser();
  },
  [LOGOUT_REQUEST]: (state) => {
    state.currentUser = null;
    destroyCurrUser();
  },
  [REGISTRATION_SUCCESS]: (state, payload) => {
    state.currentUser = payload;
    saveCurrUser(payload);
  },
  [REGISTRATION_ERROR]: (state) => {
    state.currentUser = null;
    destroyCurrUser();
  },
};

const actions = {
  [LOGIN_REQUEST]: async (context, user) => {
    return await auth("auth/login", user)
      .then((response) => {
        const payload = response && response.data;
        context.commit(LOGIN_SUCCESS, payload);
      })
      .catch(() => {
        context.commit(LOGIN_ERROR);
      });
  },
  [LOGOUT_REQUEST]: async (context) => {
    return await post("auth/logout").then(() => {
      context.commit(LOGOUT_REQUEST);
    });
  },
  [REGISTRATION_REQUEST]: async (context, user) => {
    return await post("public/register", user)
      .then((response) => {
        const payload = response && response.data;
        context.commit(REGISTRATION_SUCCESS, payload);
      })
      .catch(() => {
        context.commit(REGISTRATION_ERROR);
      });
  },
};

export default {
  state,
  getters,
  actions,
  mutations,
};
