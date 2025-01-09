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
                bat "docker build -t apurvanaik422/seldocker100 ."
            }
        }

        stage('Pushing Image to DockerHub') {
            environment {
                DOCKER_HUB = credentials('dockercred') // Use Jenkins credentials for DockerHub
            }

            steps {
                // Print out the variables for debugging (avoid printing passwords in production)
                bat 'echo %DOCKER_HUB_USR%'
                bat 'echo %DOCKER_HUB_PSW%'

                // Secure login to DockerHub using --password-stdin

                        bat 'docker login -u %DOCKER_HUB_USR% -p %DOCKER_HUB_PSW% '


                // Push the image to DockerHub
                bat "docker push apurvanaik422/seldocker100"
                //Taging
                //bat "docker tag apurvanaik422/seldocker100:latest apurvanaik422/seldocker100:${env.BUILD_NUMBER}"
                //bat "docker push apurvanaik422/seldocker100:${env.BUILD_NUMBER}"
            }
        }
    }

    post {
        always {
            // Logout from DockerHub after pipeline execution
            bat "docker logout"
        }
    }
}
