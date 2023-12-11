pipeline {
    agent any
    options {
        durabilityHint 'MAX_SURVIVABILITY'
    }
    stages {
        stage('git clone') {
            steps {
                sh 'pwd'
                sh 'rm -r compaines'
                sh 'git clone https://github.com/cherepakhin/companies.git'
                sh 'ls'
            }
        }
        stage('unit tests') {
            steps {
                sh 'ls'
                sh 'cd companies;chmod +x mvnw;ls -al;pwd'
                sh 'pwd;ls -al;cd companies;./mvnw test -Dtest=!*_IntegrationTest'
                sh 'ls'
            }
        }
        stage('deploy to nexus') {
            steps {
                sh 'ls'
                sh 'cd companies;ls;./mvnw -Dmaven.test.skip=true deploy'
                sh 'ls'
            }
        }
    }
}
