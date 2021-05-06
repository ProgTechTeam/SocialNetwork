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
import { FETCH_SUBSCRIPTIONS_REQUEST } from "../../store/action-types";

export default {
  name: "Subscriptions",
  props: {
    id: Number,
  },
  components: {
    UserRow,
  },
  computed: {
    users() {
      return this.$store.getters.getSubscriptions;
    },
  },
  mounted() {
    this.$store.dispatch(FETCH_SUBSCRIPTIONS_REQUEST, this.id);
  },
};
</script>

<style scoped></style>
