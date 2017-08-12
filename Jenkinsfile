pipeline {
    agent { docker 'gradle:3.5' }
    stages {
        stage('build') {
            steps {
				sh 'cd carRentWar'
                sh 'gradle clean war'
            }
        }
    }
}