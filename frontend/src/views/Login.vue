<template>
  <v-card>
    <v-card-title>Вход</v-card-title>
    <v-card-text>
      <v-form>
        <v-text-field
          v-model.trim="email"
          label="E-mail"
          prepend-inner-icon="mdi-email"
          type="email"
          required
        />
        <v-text-field
          v-model.trim="password"
          label="Пароль"
          prepend-inner-icon="mdi-form-textbox-password"
          :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
          :type="showPassword ? 'text' : 'password'"
          @click:append="showPassword = !showPassword"
          counter
          required
        />
      </v-form>
    </v-card-text>
    <v-card-actions>
      <v-btn @click="login" color="primary">Войти</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import { LOGIN_REQUEST } from "../store/action-types";

export default {
  name: "Login",
  data() {
    return {
      showPassword: false,
      email: "",
      password: "",
    };
  },
  methods: {
    login: function () {
      const { email, password } = this;
      this.$store.dispatch(LOGIN_REQUEST, { email, password }).then(() => {
        this.$router.push({ name: "Feed" });
      });
    },
  },
};
</script>

<style scoped></style>
