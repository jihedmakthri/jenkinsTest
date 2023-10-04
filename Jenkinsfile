pipeline {
    agent any

    stages {
        stage('testing Maven ...'){
            steps{
                sh "mvn -version"
            }
        }
        stage('Git checkout ...'){
            steps{
                echo 'Pulling from git ...'
                git branch: 'main',
                url: 'https://github.com/jihedmakthri/jenkinsTest.git'
            }
        }
    }
}