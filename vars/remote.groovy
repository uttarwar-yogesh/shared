def call() { 
  script{
      remote = [name: 'test', host: 'localhost', user: 'yuttarwar', password: "bumpyShow72@", allowAnyHosts: true]
              sshCommand remote: remote, command: "for i in {1..5}; do echo -n \"Loop \$i \"; date ; sleep 1; done" 
  }
} 
