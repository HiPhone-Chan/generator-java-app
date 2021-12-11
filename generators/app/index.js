var Generator = require('yeoman-generator');
const utils = require('./utils');
const prompts = require('./prompts');
const cpTpl = require('./cp-tmpl');

module.exports = class extends Generator {
  // The name `constructor` is important here
  constructor(args, opts) {
    // Calling the super constructor is important so our generator is correctly set up
    super(args, opts);

    // This makes `appname` a required argument.
    this.argument("appname", { type: String, required: true });
    // Next, add your custom code
    // This method adds support for a `--babel` flag
    this.option('babel');
  }

  async exec() {
    const answers = await prompts.prompting(this);
    this.answers = answers;
  }

  async copyFiles() {
    const secret64 = await utils.exeOpenssl("openssl rand -base64 64");
    const secret = await utils.exeOpenssl("openssl rand -hex 20");
   
    this.secret64 = secret64;
    this.secret = secret;
    cpTpl.copy(this)
  }

};