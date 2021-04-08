def call(String repoUrl, String BRANCH, String JDK_VERSION, String MVN_VERSION, String WL_VERSION, String PATCH_VERSION, String PATCH_PATH) { 
pipeline {
    agent any 
    stages{
        stage ('Cleaning') {
        steps{
	    sh '''
            rm -rf *
            ls
	    '''
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
            sh '''
            pwd
            ls -la
	    '''
		script {
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
			 "sh ./build-base-image.sh".execute()
			echo "executed"
			// def scriptFileContent = libraryResource( '/var/lib/jenkins/workspace/New-containerbuild-base-image.sh' )
		        // sh scriptFileContent
			// './build-base-image.sh' "${JDK_VERSION}" "${MVN_VERSION}" "${WL_VERSION}" "${PATCH_VERSION}" "${PATCH_PATH}"
			
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
