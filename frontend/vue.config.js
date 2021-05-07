module.exports = {
  outputDir: 'build/dist',
  assetsDir: 'static',
  devServer: {
    host: 'localhost',
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
