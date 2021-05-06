<template>
  <v-card>
    <v-card-title>{{ user.firstName }} {{ user.lastName }}</v-card-title>
    <v-divider class="mx-3" />
    <v-card-subtitle>{{ user.email }}</v-card-subtitle>
    <v-divider />
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn text link :to="{ name: 'Friends', params: { id: user.id } }">
        Друзья {{ user.friends }}
      </v-btn>
      <v-spacer></v-spacer>
      <v-btn text link :to="{ name: 'Subscribers', params: { id: user.id } }">
        Подписчики {{ user.subscribers }}
      </v-btn>
      <v-spacer></v-spacer>
      <v-btn text link :to="{ name: 'Subscriptions', params: { id: user.id } }">
        Подписки {{ user.subscriptions }}
      </v-btn>
      <v-spacer></v-spacer>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  name: "ProfileInfoCard",
  props: {
    user: {},
  },
};
</script>

<style scoped></style>
