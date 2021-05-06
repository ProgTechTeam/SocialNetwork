import { HTTP } from "../config/http.config";

export const get = async (url, params) => {
  try {
    return await HTTP.get(url, {
      params: params,
    });
  } catch (error) {
    console.log(error);
  }
};

export const post = async (url, data) => {
  try {
    return await HTTP.post(url, data);
  } catch (error) {
    console.log(error);
  }
};

export const put = async (url, data) => {
  try {
    return await HTTP.put(url, data);
  } catch (error) {
    console.log(error);
  }
};

export const auth = async (url, data) => {
  try {
    const formData = new FormData();
    formData.append("username", data.email);
    formData.append("password", data.password);
    return await HTTP.post(url, formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });
  } catch (error) {
    console.log(error);
  }
};
