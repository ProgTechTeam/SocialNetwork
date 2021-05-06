<template>
  <v-card>
    <v-card-title>Регистрация</v-card-title>
    <v-card-text>
      <v-form>
        <v-text-field
          v-model.trim="firstName"
          label="Имя"
          prepend-inner-icon="mdi-account"
          type="text"
          required
        />
        <v-text-field
          v-model.trim="lastName"
          label="Фамилия"
          prepend-inner-icon="mdi-account"
          type="text"
          required
        />

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
          :append-icon="showPassword1 ? 'mdi-eye' : 'mdi-eye-off'"
          :type="showPassword1 ? 'text' : 'password'"
          @click:append="showPassword1 = !showPassword1"
          counter
          required
        />
        <v-text-field
          v-model.trim="passwordCheck"
          label="Подтвердите пароль"
          prepend-inner-icon="mdi-form-textbox-password"
          :append-icon="showPassword2 ? 'mdi-eye' : 'mdi-eye-off'"
          :type="showPassword2 ? 'text' : 'password'"
          @click:append="showPassword2 = !showPassword2"
          counter
          required
        />
      </v-form>
    </v-card-text>
    <v-card-actions>
      <v-btn @click="register" color="primary">Зарегестрироваться</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import { REGISTRATION_REQUEST } from "../store/action-types";

export default {
  name: "Registration",
  data() {
    return {
      showPassword1: false,
      showPassword2: false,
      firstName: "",
      lastName: "",
      email: "",
      password: "",
      passwordCheck: "",
    };
  },
  methods: {
    register: function () {
      const { firstName, lastName, email, password } = this;
      this.$store
        .dispatch(REGISTRATION_REQUEST, {
          firstName,
          lastName,
          email,
          password,
        })
        .then(() => {
          this.$router.push({ name: "Feed" });
        });
    },
  },
};
</script>

<style scoped></style>
