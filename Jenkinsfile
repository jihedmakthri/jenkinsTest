pipeline {
    agent any
    stages {
        stage('Testing Maven') {
            steps {
                sh "mvn -version"
            }
        }

        stage('Git Checkout') {
            steps {
                echo 'Pulling from Git...'
                git branch: 'main',
                url: 'https://github.com/jihedmakthri/jenkinsTest.git'
            }
        }

        stage('Maven Clean') {
            steps {
                sh "mvn clean"
            }
        }

        stage('Maven Compile') {
            steps {
                sh "mvn compile"
            }
        }

        stage('Maven Package') {
            steps {
                sh "mvn package"
            }
        }

        stage('Maven Verify') {
            steps {
                sh "mvn verify"
            }
        }

        stage('Maven Install') {
            steps {
                sh "mvn install"
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                withCredentials([usernamePassword(credentialsId: dockerhub_creds, usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASSWORD')]) {
                    dockerImage = docker.build("jihedmakthri/jenkins-test:${env.BUILD_NUMBER}")
                    }
                }
            }
        }
        stage('Push Docker Image') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: dockerhub_creds, usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASSWORD')]) {
                    dockerImage.push()}
                }
            }
        }
    }
}
