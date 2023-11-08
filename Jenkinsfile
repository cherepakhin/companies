pipeline {
    agent any
    stages {
        stage('git clone') {
            steps {
                sh 'rm -r companies'
                sh 'git clone https://github.com/cherepakhin/companies.git'
                sh 'ls'
            }
        }
        stage('unit tests') {
            steps {
                sh 'ls'
                sh 'cd companies;ls;./mvnw test -Dtest=!*_IntegrationTest'
                sh 'ls'
            }
        }
        stage('integration tests') {
            steps {
                sh 'ls'
                sh 'cd companies;ls;./mvnw test -Dtest=*_IntegrationTest'
                sh 'ls'
            }
        }
    }
}
