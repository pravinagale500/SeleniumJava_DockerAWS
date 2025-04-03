pipeline {
    agent {
        docker {
            image 'mcr.microsoft.com/windows/servercore:ltsc2022'
            args '-v //var/run/docker.sock:/var/run/docker.sock'
        }
    }
    stages {
        stage('Build') {
            steps {
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
                    sh 'docker stop selenium-container'
                } catch(Exception e){
                }
            }
        }
        success {
           script {
                try {
                    sh 'docker stop selenium-container'
                } catch(Exception e){
                }
            }
        }
        failure {
            script {
                try {
                    sh 'docker stop selenium-container'
                } catch(Exception e){
                }
            }
        }
    }
}