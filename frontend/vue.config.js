module.exports = {
  devServer: {
    port: 4444,
    proxy: {
      "^/api": {
        target: "http://back:8080",
        changeOrigin: true,
      },
    },
  },
  transpileDependencies: ["vuetify"],
};
