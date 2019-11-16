pipeline {
    agent any
    // tools { // this is the jdk specified in global configurations
    //  jdk 'JDK11'
    // }

    triggers {
        pollSCM('* * * * *')
    }
    stages {
        stage("Allow gradlew access") {
            steps {
                sh "chmod +x gradlew"
            }
        }

        stage("Compile") {
            steps {
                sh "./gradlew compileJava"
            }
        }

        stage("Unit Tests") {
            steps {
                sh "./gradlew test"
            }
        }

        stage("Package") {
            steps {
                sh "./gradlew build"
            }
        }
        stage("Docker build") {
            steps {
                sh "docker build -t ozihler/esdws:1 ."
            }
        }

        stage("Docker push") {
            environment {
                DOCKER_HUB_CREDENTIALS = credentials('Dockerhub')
            }
            steps {
                sh "docker login --username " + DOCKER_HUB_CREDENTIALS_USR + " --password " + DOCKER_HUB_CREDENTIALS_PSW
                sh "docker push ozihler/esdws:1"
            }
        }

        stage("Deploy to staging") {
            steps {
                sh "docker stop /esdws || true && docker run -d --rm -p 8091:5000 --name esdws ozihler/esdws:1"

            }
        }
    }
}
