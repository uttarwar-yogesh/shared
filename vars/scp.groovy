
def call(String hostname, String targethostname, String username, String password, String src, String dest ) {
	
properties([
     
        parameters ([
          
            string(name: 'hostname', defaultValue: '', description: 'Provide source hostname'),
            string(name: 'targethostname', defaultValue: '', description: 'Provide target hostname'),
            string(name: 'username', defaultValue: '', description: 'Provide username'),
            string(name: 'password', defaultValue: '', description: 'Provide password'),
            string(name: 'src', defaultValue: '', description: 'provide src path'),
            string(name: 'dest', defaultValue: '', description: 'provide dest path'),
        ])
    ])
	
node {
	environment {
	targethostname = 'targethostname'
	username = 'username'
	password = 'password'
	src = '${params.src}'
	dest = '${params.dest}'
	}
        stage ('Coping files') {
                echo '${env.password}'
		echo "${env.password}"
		sh 'rsync --rsh="sshpass -p  ${password} ssh -o StrictHostKeyChecking=no -l ${username}" ${hostname}:${src} ${targethostname}:${dest}'
           
     }
   }
 }

