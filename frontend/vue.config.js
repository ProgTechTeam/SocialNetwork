module.exports = {
  outputDir: 'build/dist',
  assetsDir: 'static',
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
