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
            cd eng-docker
	    '''
			//PATCH_VERSION="${PATCH_VERSION}"
			if ( "${PATCH_VERSION}" )
            		echo "true"
			else
			echo "false"
            
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
