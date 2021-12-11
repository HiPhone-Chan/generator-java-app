const openssl = require('openssl-nodejs')

module.exports = {
  exeOpenssl(cmd) {
    return new Promise((resolve, reject) => {
      openssl(cmd, (err, buffer) => {
        if (err && err.length) {
          reject(err);
        }
        resolve(buffer.toString());
      });
    })
  }

}