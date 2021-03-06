def notify(status) {
   println ${BUILD_USER_EMAIL}
   wrap([$class: 'BuildUser']) {
      println ${BUILD_USER_EMAIL}
       emailext (
       subject: "${status}: Job ${env.JOB_NAME} ([${env.BUILD_NUMBER})",
       body: """
       Check console output at <a href="${env.BUILD_URL}">${env.JOB_NAME} (${env.BUILD_NUMBER})</a>""",
       to: "${BUILD_USER_EMAIL}",
        
       from: 'jenkins@company.com')
   }
   println ${BUILD_USER_EMAIL}
}
