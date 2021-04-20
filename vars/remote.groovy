def remote = [:]
remote.name = "hostname"
remote.host = "localhost"
remote.allowAnyHosts = true
node {
  withCredentials([sshUserPrivateKey(credentialsId: 'eedb5f06-cb33-4352-a9ea-c28b8a4f35a7', keyFileVariable: 'identity', passphraseVariable: 'passphrase', usernameVariable: 'userName')]) {
  remote.user = userName
    remote.identityFile = identity
  remote.passphrase = passphrase
  stage("SSH Steps Rocks!") {
    writeFile file: 'abc.sh', text: 'ls'
    sshPut remote: remote, from: 'abc.sh', into: '.'
  }
}
