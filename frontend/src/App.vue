<template>
  <v-app>
    <v-app-bar app class="d-flex">
      <v-app-bar-nav-icon @click.stop="drawer = !drawer" />
      <v-btn text link :to="{ name: 'Feed' }">Dovecote</v-btn>
      <div v-if="isAuth">
        <v-btn text class="mx-1" rounded link :to="myProfileLink">
          <v-avatar color="accent" size="36" class="mr-2">
            <v-img
              v-if="!!currentUser.avatar"
              :src="currentUser.avatar"
              alt="avatar"
            />
            <v-icon v-else> mdi-account-circle</v-icon>
          </v-avatar>
          {{ currentUser.email }}
        </v-btn>
        <v-btn @click="logout" class="mx-1" outlined>
          <v-icon left> mdi-logout</v-icon>
          Выйти
        </v-btn>
      </div>
      <div v-else>
        <v-btn link :to="{ name: 'Login' }" color="accent" class="mx-1">
          Вход
        </v-btn>
        <v-btn link :to="{ name: 'Registration' }" class="mx-1">
          Регистрация
        </v-btn>
      </div>
      <v-switch
        v-model="$vuetify.theme.dark"
        inset
        hide-details
        prepend-icon="mdi-weather-night"
        label="Темная тема"
      />
    </v-app-bar>

    <v-navigation-drawer app v-model="drawer" temporary>
      <v-list rounded>
        <v-list-item
          v-for="item in items"
          :key="item.title"
          link
          :to="item.link"
        >
          <v-list-item-icon>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>

    <v-main>
      <v-container fluid>
        <router-view></router-view>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import { LOGOUT_REQUEST } from "./store/action-types";

export default {
  name: "App",
  data() {
    return {
      drawer: false,
      myProfileLink: {
        name: "Profile",
        params: { id: this.$store.state.auth.currentUser.id },
      },
      items: [
        {
          title: "Мой профиль",
          icon: "mdi-account-circle",
          link: {
            name: "Profile",
            params: { id: this.$store.state.auth.currentUser.id },
          },
        },
        {
          title: "Новости",
          icon: "mdi-view-dashboard",
          link: {
            name: "Feed",
          },
        },
        {
          title: "Друзья",
          icon: "mdi-account-multiple",
          link: {
            name: "Friends",
            params: { id: this.$store.state.auth.currentUser.id },
          },
        },
      ],
    };
  },
  computed: {
    isAuth() {
      return this.$store.getters.isAuth;
    },
    currentUser() {
      return this.$store.state.auth.currentUser;
    },
  },
  methods: {
    logout: function () {
      this.$store.dispatch(LOGOUT_REQUEST).then(() => {
        this.$router.push({ name: "Login" });
      });
    },
  },
};
</script>
