def call(String hostname, String targethostname, String username, String password, String src, String dest ) { 
node {
        stage ('Coping files') {

		sh 'rsync --rsh="sshpass -p  ${password} ssh -o StrictHostKeyChecking=no -l ${username}" ${hostname}:${src} ${targethostname}:${dest}'
           
     }
   }
 }

