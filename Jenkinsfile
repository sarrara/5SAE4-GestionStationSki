pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Étape pour récupérer le code source depuis le référentiel Git
                checkout([$class: 'GitSCM', branches: [[name: '*/ZouhourRezgui-5SAE4']], userRemoteConfigs: [[url: 'https://github.com/sarrara/5SAE4-GestionStationSki.git']]])
            }
        }

        stage('Cleaning project') {
            steps {
                sh 'mvn clean'
              echo "clean project"
            }
        }

       

        
    }
}
