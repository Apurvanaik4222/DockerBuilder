

pipeline {
    agent any

    stages {
        stage('Building JARs') {
            agent {
                docker {
                    image 'maven:3.9.3-eclipse-temurin-17-focal'
                    args '-u root -v /tmp/m2:/root/.m2'
                }
            }
            steps {
                script {
                    // Ensure the workspace path is Docker-compatible (escaping '@' if necessary)
                    def transformedPath = env.WORKSPACE.replace('\\', '/').replace('C:', '/c')
                    // Try to print the transformed path to check
                    echo "Transformed Workspace Path: ${transformedPath}"

                    // If the @ character is causing issues, escape or enclose paths in quotes
                    def workspacePath = transformedPath.replace('@', '\\@')
                    echo "Adjusted Path for Docker: ${workspacePath}"

                    // Run Maven build
                    sh "mvn clean package -DskipTests"
                }
            }
        }

        stage('Creating an Image') {
            steps {
                script {
                    // Build the Docker image
                    app = docker.build('apurvanaik422/seldocker100')
                }
            }
        }

        stage('Pushing Image to DockerHub') {
            steps {
                script {
                    docker.withRegistry('', 'dockercred') {
                        // Push the image with the 'latest' tag
                        app.push('latest')
                    }
                }
            }
        }
    }
}
