pipeline {
    agent any
    tools {
        nodejs 'node'
        docker "docker"
    }
    stages {
        stage('Build') {
            steps {
                sh 'npm install'
            }
        }
        stage('Test') {
            steps {
                sh 'npm test'
            }
        }
        stage('Deploy') {
            steps {
                sh 'docker build -t nodedev:v1.0 .'
            }
        }
    }
}
