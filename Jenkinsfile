pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                npm install
            }
        }
        stage('Test') {
            steps {
                npm test
            }
        }
        stage('Deploy') {
            steps {
                docker build -t nodedev:v1.0.
            }
        }
    }
}
