pipeline {
    agent any
    options {
        durabilityHint 'MAX_SURVIVABILITY'
    }
    stages {
        stage('git clone') {
            steps {
                sh 'pwd'
                sh 'git clone https://github.com/cherepakhin/companies.git'
                sh 'ls'
            }
        }
        stage('unit tests') {
            steps {
                sh 'ls'
                sh './mvnw test -Dtest=!*_IntegrationTest'
                sh 'ls'
            }
        }
        stage('integration tests') {
            steps {
                sh 'ls'
                sh './mvnw test -Dtest=*_IntegrationTest'
                sh 'ls'
            }
        }
        stage('deploy to nexus') {
            steps {
                sh 'ls'
                sh './mvnw deploy'
                sh 'ls'
            }
        }
    }
}
