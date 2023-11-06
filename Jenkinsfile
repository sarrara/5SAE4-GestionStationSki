pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Étape pour récupérer le code source depuis le référentiel Git
                checkout([$class: 'GitSCM', branches: [[name: '*/ZouhourRezgui-5SAE4']], userRemoteConfigs: [[url: 'https://github.com/sarrara/5SAE4-GestionStationSki.git']]])
            }
        }

        stage('Build') {
            steps {
                // Étape pour construire votre projet
                // Exemple : sh 'mvn clean install'
              echo "zouhour"
            }
        }

       

        
    }
}
