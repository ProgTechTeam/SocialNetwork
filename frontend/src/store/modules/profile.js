import {
  FETCH_PROFILE_ERROR,
  FETCH_PROFILE_REQUEST,
  FETCH_PROFILE_SUCCESS,
  SUBSCRIBE_TO_USER_ERROR,
  SUBSCRIBE_TO_USER_REQUEST,
  SUBSCRIBE_TO_USER_SUCCESS,
  UNSUBSCRIBE_FROM_USER_ERROR,
  UNSUBSCRIBE_FROM_USER_REQUEST,
  UNSUBSCRIBE_FROM_USER_SUCCESS,
} from "../action-types";
import { get, put } from "../../core/services/http.service";

function getDefaultUserData() {
  return {
    id: Number,
    avatar: "",
    firstName: "",
    lastName: "",
    email: "",
    friends: 0,
    subscribers: 0,
    subscriptions: 0,
    subscribed: Boolean,
  };
}

const state = () => ({
  userData: getDefaultUserData(),
});

const getters = {};

const mutations = {
  [FETCH_PROFILE_SUCCESS]: (state, payload) => {
    state.userData = payload;
  },
  [FETCH_PROFILE_ERROR]: (state) => {
    state.userData = getDefaultUserData();
  },
  [SUBSCRIBE_TO_USER_SUCCESS]: (state, payload) => {
    state.userData = payload;
  },
  [SUBSCRIBE_TO_USER_ERROR]: () => {},
  [UNSUBSCRIBE_FROM_USER_SUCCESS]: (state, payload) => {
    state.userData = payload;
  },
  [UNSUBSCRIBE_FROM_USER_ERROR]: () => {},
};

const actions = {
  [FETCH_PROFILE_REQUEST]: async (context, id) => {
    return await get(`users/${id}`)
      .then((response) => {
        const payload = response && response.data;
        context.commit(FETCH_PROFILE_SUCCESS, payload);
      })
      .catch(() => {
        context.commit(FETCH_PROFILE_ERROR);
      });
  },
  [SUBSCRIBE_TO_USER_REQUEST]: async (context, payload) => {
    return await put(`users/${payload}/subscribe`)
      .then((response) => {
        const payload = response && response.data;
        context.commit(SUBSCRIBE_TO_USER_SUCCESS, payload);
      })
      .catch(() => {
        context.commit(SUBSCRIBE_TO_USER_ERROR);
      });
  },
  [UNSUBSCRIBE_FROM_USER_REQUEST]: async (context, payload) => {
    return await put(`users/${payload}/unsubscribe`, payload)
      .then((response) => {
        const payload = response && response.data;
        context.commit(UNSUBSCRIBE_FROM_USER_SUCCESS, payload);
      })
      .catch(() => {
        context.commit(UNSUBSCRIBE_FROM_USER_ERROR);
      });
  },
};

export default {
  state,
  getters,
  actions,
  mutations,
};
