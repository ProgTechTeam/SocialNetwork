<template>
  <v-container fluid>
    <UserRow
      v-for="item in users"
      :key="item.name"
      :user-data="item"
      class="mx-auto my-3"
    ></UserRow>
  </v-container>
</template>

<script>
import UserRow from "@/components/UserRow";
import { FETCH_ALL_USERS_REQUEST } from "../../store/action-types";

export default {
  name: "AllUsers",
  components: {
    UserRow,
  },
  computed: {
    users() {
      return this.$store.getters.getAllUsers;
    },
  },
  mounted() {
    this.$store.dispatch(FETCH_ALL_USERS_REQUEST);
  },
};
</script>

<style scoped></style>
