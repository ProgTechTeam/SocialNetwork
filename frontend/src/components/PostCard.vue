<template>
  <v-card>
    <v-container fluid class="d-flex justify-start align-center">
      <v-avatar size="56" color="accent" class="mr-2">
        <img
          v-if="!!postData.author.avatar"
          :src="postData.author.avatar"
          alt="avatar"
        />
        <v-icon v-else> mdi-account-circle</v-icon>
      </v-avatar>

      <div class="d-flex flex-column">
        <div class="text-subtitle-1 font-weight-medium">
          {{ postData.author.name }}
        </div>

        <div class="text-subtitle-2 text--secondary">
          {{ new Date(postData.createdAt).toDateString() }}
        </div>
      </div>
    </v-container>

    <v-container fluid>
      {{ postData.payload }}
    </v-container>
  </v-card>
</template>

<script>
export default {
  name: "PostCard",
  props: {
    postData: {},
  },
};
</script>

<style scoped></style>
