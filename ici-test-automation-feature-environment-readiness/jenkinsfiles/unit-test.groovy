def result

node('3-jdk-8') {
    stage ('Checkout') {
        deleteDir()
        echo "Build project"
        checkout scm
    }

    try {
        stage ('Build') {
            sh "cd SeleniumTests; mvn -Dtestng.suite=testSuites/unitTests.xml clean install"
        }       
    }
    catch(Exception e) {
        currentBuild.result = 'FAILURE'        
    }

    try {
        stage('Report')
        {
            junit "SeleniumTests/**/junitreports/*.xml"
        }
    }
    catch(Exception e) {
        currentBuild.result = 'FAILURE' 
    }
}


stage ('Notification') {
    if (currentBuild.currentResult == 'FAILURE')
        notifySlack "${env.JOB_NAME} - ${currentBuild.currentResult}\n${env.BUILD_URL}console"
    else
        notifySlack "${env.JOB_NAME} - ${currentBuild.currentResult}\n${env.BUILD_URL}testReport/"
}


def notifySlack(message)
{
    node()
    {
        sh "curl -X POST -H 'Content-type: application/json' --data '{\"text\":\"$message\"}' ${env.SLACK_JENKINS_WEBHOOK_URL}"
    }
}

