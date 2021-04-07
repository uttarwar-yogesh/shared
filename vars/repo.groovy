def call(String repoUrl, String BRANCH, String JDK_VERSION, String MVN_VERSION, String WL_VERSION, String PATCH_VERSION, String PATCH_PATH) { 
pipeline {
    agent any 
        stage ('Code Checkout') {
        steps{ 
	            git branch: "${BRANCH}",
             url: "${repoUrl}"
            }            
        }
    }
}
