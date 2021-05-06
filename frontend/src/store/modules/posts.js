import {
  CREATE_USER_POST_ERROR,
  CREATE_USER_POST_REQUEST,
  CREATE_USER_POST_SUCCESS,
  FETCH_USER_POSTS_ERROR,
  FETCH_USER_POSTS_REQUEST,
  FETCH_USER_POSTS_SUCCESS,
} from "../action-types";
import { get, post } from "../../core/services/http.service";

const state = () => ({
  posts: [],
});

const getters = {
  posts: (state) => {
    return state.posts.slice().sort((a, b) => b.id - a.id);
  },
};

const mutations = {
  [FETCH_USER_POSTS_SUCCESS]: (state, payload) => {
    state.posts = payload;
  },
  [FETCH_USER_POSTS_ERROR]: (state) => {
    state.posts = [];
  },
  [CREATE_USER_POST_SUCCESS]: (state, payload) => {
    state.posts.push(payload);
  },
  [CREATE_USER_POST_ERROR]: () => {},
};

const actions = {
  [FETCH_USER_POSTS_REQUEST]: async (context, id) => {
    return await get(`users/${id}/posts`)
      .then((response) => {
        const payload = response && response.data;
        context.commit(FETCH_USER_POSTS_SUCCESS, payload);
      })
      .catch(() => {
        context.commit(FETCH_USER_POSTS_ERROR);
      });
  },
  [CREATE_USER_POST_REQUEST]: async (context, postData) => {
    return await post("posts", postData)
      .then((response) => {
        const payload = response && response.data;
        context.commit(CREATE_USER_POST_SUCCESS, payload);
      })
      .catch(() => {
        context.commit(CREATE_USER_POST_ERROR);
      });
  },
};

export default {
  state,
  getters,
  actions,
  mutations,
};
