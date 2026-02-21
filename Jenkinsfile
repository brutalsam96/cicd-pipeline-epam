pipeline {
    agent {
      docker { image 'node:20-alpine' }
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
