const fsPromises = require('fs').promises;
const path = require('path')

const TEMPLATE_PATH = "com/chf/app";
const CONFIG_WHITE_LIST = [".gitignore"]

module.exports = {
  copy(app) {
    const javaDir = path.resolve(__dirname, './templates');
    copyFile(app, javaDir);
  }
}

async function copyFile(app, dir) {
  const files = await fsPromises.readdir(dir);
  const projectName = app.options.appname;
  const packageDir = app.answers.package.replace(/\./g, "/");

  for (const file of files) {
    const child = path.resolve(dir, file);
    const result = await fsPromises.stat(child);
    if (result.isDirectory()) {
      copyFile(app, child);
    } else {
      const src = child.replace(__dirname + "/templates", '.');
      if (file.endsWith(".java")) {
        const dst = path.resolve(projectName, src.replace(TEMPLATE_PATH, packageDir));
        app.fs.copyTpl(
          app.templatePath(src),
          app.destinationPath(dst),
          {
            package: app.answers.package
          }
        );
      } else if (!file.startsWith(".") || CONFIG_WHITE_LIST.includes(file)) {
        const dst = path.resolve(projectName, src);
        app.fs.copyTpl(
          app.templatePath(src),
          app.destinationPath(dst),
          {
            package: app.answers.package,
            project: projectName,
            secret64: app.secret64,
            secret: app.secret
          }
        );
      }
    }
  }
  app.fs.commit();
}