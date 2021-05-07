module.exports = {
  outputDir: 'build/dist',
  assetsDir: 'static',
  devServer: {
    host: 'localhost',
    port: 8081,
//    proxy: {
//      "^/api": {
//        target: "http://localhost:8080",
//        changeOrigin: true,
//      },
//    },
  },
  transpileDependencies: ["vuetify"],
};
