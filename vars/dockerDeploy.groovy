def call(Map config = [:]) {
    def imageName = (config.envName == 'main') ? 'nodemain:v1.0' : 'nodedev:v1.0'
    def containerName = (config.envName == 'main') ? 'nodemain' : 'nodedev'

    withCredentials([usernamePassword(credentialsId: config.credsId, passwordVariable: 'DOCKER_PASS', usernameVariable: 'DOCKER_USER')]) {
        sh "echo \$DOCKER_PASS | docker login -u \$DOCKER_USER --password-stdin"
        sh "docker pull \$DOCKER_USER/${imageName}"
        sh "docker stop ${containerName} || true && docker rm ${containerName} || true"
        sh "docker run -d --name ${containerName} -p ${config.port}:3000 \$DOCKER_USER/${imageName}"
    }
}
