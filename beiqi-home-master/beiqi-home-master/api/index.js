// api/index.js
const modulesFiles = require.context("./apiList", true, /\.js$/);
const modules = modulesFiles.keys().reduce((modules, modulePath) => {
  const moduleName = modulePath.replace(/^\.\/(.*)\.\w+$/, "$1");

  const value = modulesFiles(modulePath);

  modules[moduleName] = value.default || value;

  return modules;
}, {});

export default modules;