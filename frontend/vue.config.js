module.exports = {
  devServer: {
    port: 4444,
    proxy: {
      "^/api": {
        target: "http://localhost:8080",
        changeOrigin: true,
      },
    },
  },
  transpileDependencies: ["vuetify"],
};
