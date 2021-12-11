

module.exports = {
  async prompting(app) {
    return await app.prompt([
      {
        type: "input",
        name: "package",
        message: "What's your package",
        default: 'com.company.app'
      }
    ])
  }
}