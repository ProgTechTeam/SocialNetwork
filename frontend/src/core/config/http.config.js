import axios from "axios";

export const HTTP = axios.create({
  baseURL: "/api/",
  timeout: 60 * 1000,
  responseType: "json",
});
