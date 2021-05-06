<template>
  <v-container fluid>
    <v-row>
      <v-col cols="4">
        <ProfileAvatarCard :user="user" :is-my-profile="isMyProfile" />
      </v-col>
      <v-col cols="8">
        <ProfileInfoCard :user="user" class="mb-6" />
        <PostCreationCard
          :user="user"
          :is-my-profile="isMyProfile"
          class="mb-6"
        />
        <PostCard
          v-for="post in posts"
          :key="post.id"
          :post-data="post"
          class="mb-3"
        />
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import {
  FETCH_PROFILE_REQUEST,
  FETCH_USER_POSTS_REQUEST,
} from "../store/action-types";
import ProfileAvatarCard from "../components/ProfileAvatarCard";
import ProfileInfoCard from "../components/ProfileInfoCard";
import PostCreationCard from "../components/PostCreationCard";
import PostCard from "../components/PostCard";

export default {
  name: "Profile",
  props: {
    id: Number,
  },
  components: {
    ProfileAvatarCard,
    ProfileInfoCard,
    PostCreationCard,
    PostCard,
  },
  computed: {
    user() {
      return this.$store.state.profile.userData;
    },
    posts() {
      return this.$store.getters.posts;
    },
    isMyProfile() {
      return this.user.id === this.$store.state.auth.currentUser.id;
    },
  },
  mounted() {
    this.$store.dispatch(FETCH_PROFILE_REQUEST, this.id);
    this.$store.dispatch(FETCH_USER_POSTS_REQUEST, this.id);
  },
  beforeRouteUpdate(to, from, next) {
    this.$store.dispatch(FETCH_PROFILE_REQUEST, to.params.id);
    this.$store.dispatch(FETCH_USER_POSTS_REQUEST, to.params.id);
    next();
  },
};
</script>

<style scoped></style>
