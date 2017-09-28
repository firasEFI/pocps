def skiptest = SKIPTEST.toBoolean() ? "-DskipTests" : ""
def result

node('jdk8') {
    stage ('Checkout') {
        deleteDir()
        checkout scm
    }

    try {
        stage ('Build') {
            sshagent(['vagrant_on_skm']) {
                sh "ssh -f -o ExitOnForwardFailure=yes -L 441:5.44.137.168:441 -p 21024 1.tcp.eu.ngrok.io sleep 80"
            }

            withEnv(["JAVA_HOME=c:\\Program Files\\Java\\jdk1.8.0_144", "PSRM_LOGIN_URL=$BASE_URL/loginPage.jsp?normal"]) {
                dir('SeleniumTests') {
                    bat "c:\\maven\\apache-maven-3.5.0\\bin\\mvn clean install -Dtestng.suite=testSuites/testEnvironmentReadiness.xml -Dselenium.baseurl=${env.PSRM_LOGIN_URL} -Dselenium.loginurl=${env.PSRM_LOGIN_URL}"
                }
            }
        }       
    }
    catch(Exception e) {
        currentBuild.result = 'FAILURE'
    }
    finally {
        bat "taskkill /IM ssh.exe /F || true"   
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

