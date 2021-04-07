def call(String repoUrl, String BRANCH) { 
pipeline {
    agent any 
	stages{
        stage ('Code Checkout') {
        steps{ 
	     git branch: "${BRANCH}",
             url: "${repoUrl}"
            }            
        }
    }
  }
}
