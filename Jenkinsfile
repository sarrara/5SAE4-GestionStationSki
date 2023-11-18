pipeline {


    agent any

    stages {

        stage('GIT Checkout') {
            steps {
                echo "Checkout Git"
                git branch: 'nourhenesaddem-5SAE4',
                url: 'https://github.com/sarrara/5SAE4-GestionStationSki.git'
            }
        }

        stage('Build') {
            steps {
                script {
                    sh "mvn --version" // Use the specified Maven installation
                    sh "mvn clean package -DskipTests" // Build your Maven project
                }
            }
        }

        stage('Unit Tests') {
            steps {
                sh 'mvn test install  jacoco:report'
            }
        }

        stage('Publish JaCoCo Report') {
            steps {
                script {
                    // Publish JaCoCo report
                    /* groovylint-disable-next-line LineLength */
                    jacoco(execPattern: '**/target/jacoco.exec', classPattern: '**/classes', sourcePattern: '**/src/main/java')
                }
            }
        }

        stage('SonarQube')  {
            steps {
                withCredentials([string(credentialsId: 'SonarQube_Token',    variable: 'SONAR_TOKEN')]) {
                    sh 'mvn sonar:sonar -Dsonar.host.url=http://192.168.1.3:9000/ -Dsonar.login=$SONAR_TOKEN -Dsonar.jacoco.reportPaths=target/jacoco.exec'
                }
            }
        }

        stage("Nexus") {
            steps {
                sh "mvn deploy -DskipTests"
            }
        }

        stage("Docker Image") {
            steps {
                sh "docker build -t nourhenesaddem/gestion-station-ski:1.1 ."
            }
        }

        stage('Docker Hub'){
            steps {
                // Deployer l'image
                sh 'docker login -u nourhenesaddem -p devops123'
                sh 'docker push nourhenesaddem/gestion-station-ski:1.1'
            }
        }

        stage('Docker Compose'){
            steps {
                // Lancer Docker-Compose
                sh 'docker compose up -d'
            }
        }

        stage('Email Notification') {
            steps {
                script {
                    try {
                        echo 'Sending email notification'
                    } catch (Exception e) {
                        currentBuild.result = 'FAILURE'
                        throw e
                    }
                }
            }
        }

    }

    post {
        success {
            // Envoyer un email si la construction réussit
            emailext subject: 'Projet Construit avec Succès',
                      body: 'La construction de votre projet a réussi. Félicitations!',
                      to: 'nourhene.saddem@esprit.tn'
        }
        failure {
            // Envoyer un email en cas d'erreur
            emailext subject: 'Erreur Détectée',
                      body: 'La construction de votre projet a échoué. Veuillez vérifier les logs pour plus de renseignements',
                      to: 'nourhene.saddem@esprit.tn',
                      attachLog: true
        }
    }

}