import {
  FETCH_ALL_USERS_ERROR,
  FETCH_ALL_USERS_REQUEST,
  FETCH_ALL_USERS_SUCCESS,
  FETCH_FRIENDS_ERROR,
  FETCH_FRIENDS_REQUEST,
  FETCH_FRIENDS_SUCCESS,
  FETCH_SUBSCRIBERS_ERROR,
  FETCH_SUBSCRIBERS_REQUEST,
  FETCH_SUBSCRIBERS_SUCCESS,
  FETCH_SUBSCRIPTIONS_ERROR,
  FETCH_SUBSCRIPTIONS_REQUEST,
  FETCH_SUBSCRIPTIONS_SUCCESS,
} from "../action-types";
import { get } from "../../core/services/http.service";

const state = () => ({
  users: [],
  friends: [],
  subscribers: [],
  subscriptions: [],
});

const getters = {
  getAllUsers: (state) => {
    return state.users;
  },
  getFriends: (state) => {
    return state.friends;
  },
  getSubscribers: (state) => {
    return state.subscribers;
  },
  getSubscriptions: (state) => {
    return state.subscriptions;
  },
};

const mutations = {
  [FETCH_ALL_USERS_SUCCESS]: (state, payload) => {
    state.users = payload;
  },
  [FETCH_ALL_USERS_ERROR]: (state) => {
    state.users = [];
  },
  [FETCH_FRIENDS_SUCCESS]: (state, payload) => {
    state.friends = payload;
  },
  [FETCH_FRIENDS_ERROR]: (state) => {
    state.friends = [];
  },
  [FETCH_SUBSCRIPTIONS_SUCCESS]: (state, payload) => {
    state.subscriptions = payload;
  },
  [FETCH_SUBSCRIPTIONS_ERROR]: (state) => {
    state.subscriptions = [];
  },
  [FETCH_SUBSCRIBERS_SUCCESS]: (state, payload) => {
    state.subscribers = payload;
  },
  [FETCH_SUBSCRIBERS_ERROR]: (state) => {
    state.subscribers = [];
  },
};

const actions = {
  [FETCH_ALL_USERS_REQUEST]: async (context) => {
    return await get(`users`)
      .then((response) => {
        const payload = response && response.data;
        context.commit(FETCH_ALL_USERS_SUCCESS, payload);
      })
      .catch(() => {
        context.commit(FETCH_ALL_USERS_ERROR);
      });
  },
  [FETCH_FRIENDS_REQUEST]: async (context, payload) => {
    return await get(`users/${payload}/friends`)
      .then((response) => {
        const payload = response && response.data;
        context.commit(FETCH_FRIENDS_SUCCESS, payload);
      })
      .catch(() => {
        context.commit(FETCH_FRIENDS_ERROR);
      });
  },
  [FETCH_SUBSCRIBERS_REQUEST]: async (context, payload) => {
    return await get(`users/${payload}/subscribers`)
      .then((response) => {
        const payload = response && response.data;
        context.commit(FETCH_SUBSCRIBERS_SUCCESS, payload);
      })
      .catch(() => {
        context.commit(FETCH_SUBSCRIBERS_ERROR);
      });
  },
  [FETCH_SUBSCRIPTIONS_REQUEST]: async (context, payload) => {
    return await get(`users/${payload}/subscriptions`)
      .then((response) => {
        const payload = response && response.data;
        context.commit(FETCH_SUBSCRIPTIONS_SUCCESS, payload);
      })
      .catch(() => {
        context.commit(FETCH_SUBSCRIPTIONS_ERROR);
      });
  },
};

export default {
  state,
  getters,
  actions,
  mutations,
};
