<template>
  <v-card class="pa-3">
    <v-responsive :aspect-ratio="1">
      <v-avatar color="accent" size="100%" rounded>
        <v-img v-if="!!user.avatar" :src="user.avatar" />
        <v-icon v-else> mdi-account-circle </v-icon>
      </v-avatar>
    </v-responsive>
    <div v-if="isMyProfile">
      <v-btn class="mt-3" block>Редактировать</v-btn>
    </div>
    <div v-else>
      <v-btn
        class="mt-3"
        block
        color="primary"
        v-if="user.subscribed"
        @click="unsubscribe"
        >Отписаться</v-btn
      >
      <v-btn class="mt-3" block color="primary" v-else @click="subscribe"
        >Подписаться</v-btn
      >
    </div>
  </v-card>
</template>

<script>
import {
  SUBSCRIBE_TO_USER_REQUEST,
  UNSUBSCRIBE_FROM_USER_REQUEST,
} from "../store/action-types";

export default {
  name: "ProfileAvatarCard",
  props: {
    user: {},
    isMyProfile: Boolean,
  },
  methods: {
    subscribe: function () {
      this.$store.dispatch(SUBSCRIBE_TO_USER_REQUEST, this.id);
    },
    unsubscribe: function () {
      this.$store.dispatch(UNSUBSCRIBE_FROM_USER_REQUEST, this.id);
    },
  },
};
</script>

<style scoped></style>
