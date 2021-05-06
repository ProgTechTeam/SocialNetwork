<template>
  <v-container fluid>
    <v-tabs>
      <v-tab :to="{ name: 'AllUsers' }">Все пользователи</v-tab>
      <v-tab :to="{ name: 'Friends', params: { id: this.currentUser.id } }">
        Мои друзья
      </v-tab>
      <v-tab :to="{ name: 'Subscribers', params: { id: this.currentUser.id } }">
        Мои подписчики
      </v-tab>
      <v-tab
        :to="{ name: 'Subscriptions', params: { id: this.currentUser.id } }"
      >
        Мои подписки
      </v-tab>
    </v-tabs>
    <router-view></router-view>
  </v-container>
</template>

<script>
export default {
  name: "Users",
  computed: {
    currentUser() {
      return this.$store.state.auth.currentUser;
    },
  },
};
</script>

<style scoped></style>
