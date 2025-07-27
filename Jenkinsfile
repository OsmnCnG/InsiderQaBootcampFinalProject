pipeline {
    agent any

    tools {
        maven 'MAVEN_HOME 3.9.9'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Pulling code from GitHub...'
                git 'https://github.com/OsmnCnG/InsiderQaBootcampFinalProject.git'
            }
        }

        stage('Build') {
            steps {
                echo 'Compiling the project...'
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
                sh 'mvn test'
            }
        }

        stage('Allure Report') {
            steps {
                echo 'Generating Allure report...'
                sh 'mvn allure:report'
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution completed.'
        }
    }
}
