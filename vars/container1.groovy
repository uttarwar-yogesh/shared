def call(String repoUrl, String BRANCH, String JDK_VERSION, String MVN_VERSION, String WL_VERSION, String PATCH_VERSION, String PATCH_PATH)
{ 
pipeline {
    agent any 
    stages{
        stage ('Cleaning') {
        steps{
	    sh '''
            rm -rf *
	    pwd
            ls
	    cp /tmp/1.txt .
	    ls 
	    
	    rsync --rsh="sshpass -p bumpyShow72@ ssh -o StrictHostKeyChecking=no -l yuttarwar" localhost:/tmp/1.txt /tmp/test/
	    
	    cd /tmp/test
	    ls 
	    '''
	    println JDK_VERSION
	    echo "${repoUrl}"
	    echo "${BRANCH}"
	    echo "${JDK_VERSION}"
	    echo "${MVN_VERSION}"
	    echo "${WL_VERSION}"
	    echo "${PATCH_VERSION}"
		
            }            
        }
        stage ('Code Checkout') {
        steps{ 
            
            // git clone ${repoUrl}
            // cd eng-docker
            // git checkout ${BRANCH}
	     git branch: "${BRANCH}",
             url: "${repoUrl}"
            }            
        }
        stage ('Building Docker Image') {
        steps{
                
		script {
			sh "echo ${JDK_VERSION}"
			def scriptPath = "build-base-image.sh"
			def command = "bash -x $scriptPath $JDK_VERSION $MVN_VERSION $WL_VERSION $PATCH_VERSION $PATCH_PATH"
			println command.execute().text
			
			//PATCH_VERSION="${PATCH_VERSION}"
			if ( "${PATCH_VERSION}" )
			{	
            		echo "true"
			 sh '''
           		 pwd
           		 ls -la
	   		 '''
			// File script = new File('./var/lib/jenkins/workspace/New-container/build-base-image.sh "${JDK_VERSION}" "${MVN_VERSION}" "${WL_VERSION}" ')
			// script.getText().execute()
			// "sh ./build-base-image.sh".execute()
			// def scriptFileContent = libraryResource( '/var/lib/jenkins/workspace/New-containerbuild-base-image.sh' )
		        // sh scriptFileContent
			// './build-base-image.sh' "${JDK_VERSION}" "${MVN_VERSION}" "${WL_VERSION}" "${PATCH_VERSION}" "${PATCH_PATH}"
			
			// def cmd = [ "/bin/bash", "-c", "build-base-image.sh", "$JDK_VERSION", "$MVN_VERSION", "$WL_VERSION", "$PATCH_VERSION", "$PATCH_PATH" ]
			// println cmd
			// def cmd = [ "/bin/bash", "-c", "build-base-image.sh", '+JDK_VERSION+' '+MVN_VERSION+' '+WL_VERSION+' '+PATCH_VERSION+' '+PATCH_PATH+' ]
			def sout = new StringBuffer()
			def serr = new StringBuffer()	
			
			//def proc = cmd.execute()
			proc.consumeProcessOutput( sout, serr)
			proc.waitForProcessOutput()
			println "out> $sout"
			println "err> $serr"
			
			
			echo "Executed"
				
			}
			else
			{
			echo "false"
			'./build-base-image.sh' "${JDK_VERSION}" "${MVN_VERSION}" "${WL_VERSION}" 
			}
		}
            
            }
        }
        stage ('Pushing Docker Image') {
        steps{
            sh '''
            echo "will push docker image in future" 
            '''
            }
        }
    }
}
}
