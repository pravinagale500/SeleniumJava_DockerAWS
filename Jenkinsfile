pipeline {
    agent {
        docker {
            image 'mcr.microsoft.com/windows/servercore:ltsc2022'
            args '-v //var/run/docker.sock:/var/run/docker.sock'
        }
    }
    stages {
        stage('Start Selenium Container') {
            steps {
                bat 'docker run -d -p 4444:4444 --name selenium-container selenium/standalone-chrome:latest'
            }
        }
        stage('Build') {
            steps {
                git 'https://github.com/pravinagale500/SeleniumJava_DockerAWS.git'
                bat 'mvn clean install'
            }
        }
        stage('Run Selenium Tests') {
            steps {
                bat 'mvn test -Dselenium.remote.url=http://host.docker.internal:4444/wd/hub'
            }
        }
        stage('Publish Test Results') {
            steps {
                junit 'target/surefire-reports/*.xml'
                archiveArtifacts artifacts: 'target/*.jar, target/surefire-reports/*'
                archiveArtifacts artifacts: 'target/site/serenity/**'
            }
            post {
                always {
                    cleanWs()
                }
            }
        }
    }
    post {
        always {
            script {
                try {
                    bat 'docker stop selenium-container'
                    bat 'docker rm selenium-container'
                } catch(Exception e){
                }
            }
        }
    }
}