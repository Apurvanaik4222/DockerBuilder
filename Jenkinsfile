pipeline {
    agent any

    stages {
        stage('Building jars') {
            steps {
                bat "mvn clean package -DskipTests"
            }
        }

        stage('Creating an Image') {
            steps {
                script {
                    // Build Docker image with the specified tag
                    app = docker.build('apurvanaik422/seldocker100:latest')
                }
            }
        }

        stage('Pushing Image to DockerHub') {
            steps {
                script {
                    // Push the built image to DockerHub using the credentials
                    docker.withRegistry('', 'dockercred') {
                        docker.push('apurvanaik422/seldocker100:latest')
                    }
                }
            }
        }
    }
}
