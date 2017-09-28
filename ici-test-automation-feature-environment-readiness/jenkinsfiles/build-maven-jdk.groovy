if (JDK_VERSION == null) error "JDK_VERSION must be supplied as build argument like jdk-7 or jdk-8"

node('docker')
{
	stage('checkout')
	{
		checkout scm
	}

	stage('build')
	{
		dir("Dockerfiles/$JDK_VERSION")
		{
			def app = docker.build('maven-jenkins-slave')
			app.tag(JDK_VERSION)
		}
	}
}