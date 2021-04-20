def call() { 
  script{
      remote1 = [name: 'test', host: 'localhost', user: 'yuttarwar', password: "@", allowAnyHosts: true]
              sshCommand remote: remote, command: "for i in {1..5}; do echo -n \"Loop \$i \"; date ; sleep 1; done" 
  }
} 
