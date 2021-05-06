<template>
  <v-card>
    <v-container fluid class="d-flex justify-space-between align-start">
      <v-avatar color="accent" size="56" class="mr-2">
        <v-img
          v-if="!!currentUser.avatar"
          :src="currentUser.avatar"
          alt="avatar"
        />
        <v-icon v-else> mdi-account-circle </v-icon>
      </v-avatar>
      <v-textarea
        v-model="postPayload"
        auto-grow
        rows="1"
        hide-details
        filled
        rounded
        :label="isMyProfile ? 'Что у вас нового?' : 'Напишите что нибудь...'"
        :append-outer-icon="postPayload ? 'mdi-content-save' : null"
        @click:append-outer="savePost"
      />
    </v-container>
  </v-card>
</template>

<script>
import { CREATE_USER_POST_REQUEST } from "../store/action-types";

export default {
  name: "PostCreationCard",
  props: {
    user: {},
    isMyProfile: Boolean,
  },
  data() {
    return {
      postPayload: "",
    };
  },
  computed: {
    currentUser() {
      return this.$store.state.auth.currentUser;
    },
  },
  methods: {
    savePost: function () {
      const newPost = {
        payload: this.postPayload,
        authorId: this.$store.state.auth.currentUser.id,
        ownerId: this.user.id,
      };
      this.$store.dispatch(CREATE_USER_POST_REQUEST, newPost);
      this.postPayload = "";
    },
  },
};
</script>

<style scoped></style>
