pipeline {
    agent any

    tools {
        maven 'MAVEN_HOME'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Pulling code from GitHub...'
                git branch: 'main', url: 'https://github.com/OsmnCnG/InsiderQaBootcampFinalProject.git'
            }
        }

        stage('Build') {
            steps {
                echo 'Compiling the project...'
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
                bat 'mvn test'
            }
        }

        stage('Allure Report') {
            steps {
                echo 'Generating Allure report...'
                bat 'mvn allure:report'
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution completed.'
        }
    }
}
