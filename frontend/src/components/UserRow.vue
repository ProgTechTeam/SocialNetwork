<template>
  <v-card>
    <v-list-item link :to="link">
      <v-list-item-avatar>
        <v-avatar color="accent" size="36px">
          <v-img v-if="!!userData.avatar" :src="userData.avatar" alt="avatar" />
          <v-icon v-else> mdi-account-circle </v-icon>
        </v-avatar>
      </v-list-item-avatar>

      <v-list-item-content>
        <v-list-item-title class="headline">
          {{ userData.name }}
        </v-list-item-title>
      </v-list-item-content>
    </v-list-item>
  </v-card>
</template>

<script>
export default {
  name: "UserRow",
  props: {
    userData: {},
  },
  computed: {
    link() {
      return { name: "Profile", params: { id: this.userData.id } };
    },
  },
};
</script>

<style scoped></style>
