pipeline {
    agent any
    tools {
        nodejs 'node'
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
                sh 'docker build -t nodemain:v1.0 .'
            }
        }
    }
}
