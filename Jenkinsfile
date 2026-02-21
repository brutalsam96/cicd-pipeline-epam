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
                sh "trivy image --exit-code 0 --severity HIGH,CRITICAL nodemain:v1.0" 
                // This will return 0 despite found vulnurabilities
            }
        }
        stage('Push to Docker Hub') {
          steps {
            script {
              withCredentials([usernamePassword(credentialsId: 'jenkins-docker-hub', passwordVariable: 'DOCKER_PASS', usernameVariable: 'DOCKER_USER')]) {
                sh "echo \$DOCKER_PASS | docker login -u \$DOCKER_USER --password-stdin"

                def imageName = (env.BRANCH_NAME == 'main') ? 'nodemain:v1.0' : 'nodedev:v1.0'
                sh "docker tag ${imageName} \$DOCKER_USER/${imageName}"
                sh "docker push \$DOCKER_USER/${imageName}"
              }
            }
          }
        }
        stage('Trigger CD') {
          steps {
            script {
              if (env.BRANCH_NAME == 'main') {
                build job: 'Deploy_to_main', wait: false
              } else if (env.BRANCH_NAME == 'dev') {
                build job: 'Deploy_to_dev', wait: false
              }
            }
          }
        }
    }
}
