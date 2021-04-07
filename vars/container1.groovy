def call(String repoUrl, String BRANCH, String JDK_VERSION, String MVN_VERSION, String WL_VERSION, String PATCH_VERSION, String PATCH_PATH) { 
pipeline {
    agent any 
    stages{
        stage ('Cleaning') {
        steps{
            sh '''
            rm -rf *
            ls
	    echo "${repoUrl}"
	    echo "${BRANCH}"
	    echo ${JDK_VERSION}
	    echo ${MVN_VERSION}
	    echo ${WL_VERSION}
            '''
            }            
        }
        stage ('Code Checkout') {
        steps{ 
            sh '''
            // git clone ${repoUrl}
            // cd eng-docker
            // git checkout ${BRANCH}
	    
            ls -l
            '''
            }            
        }
        stage ('Building Docker Image') {
        steps{
            sh '''
            pwd
            ls -la
            cd eng-docker
			PATCH_VERSION="${PATCH_VERSION:-}"
			if [[ -z ${PATCH_VERSION} ]]
			then
            ./build-base-image.sh ${JDK_VERSION} ${MVN_VERSION} ${WL_VERSION}
			else
			./build-base-image.sh ${JDK_VERSION} ${MVN_VERSION} ${WL_VERSION} ${PATCH_VERSION} ${PATCH_PATH}
			fi
            '''
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
