pipeline {
 

    agent any

    stages {
        stage('GIT') {
            steps {
                echo "Getting Project from Git"
                git branch: 'medyassinesamaali-5sae4',
                url: 'https://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@github.com/sarrara/5SAE4-GestionStationSki.git'
            }}
            stage('Build') {
            steps {
                script {
                    sh "mvn --version" // Use the specified Maven installation
                    sh "mvn clean package -DskipTests" // Build your Maven project
                }
            }
        }

      stage('Run Sonar')  {
            steps {
                withCredentials([string(credentialsId: 'fda3202d-683d-43e4-af02-d0e0b0e3c726', variable: 'SONAR_TOKEN')]) {
                    sh 'mvn sonar:sonar -Dsonar.host.url=http://172.10.0.140:9000/ -Dsonar.login=$SONAR_TOKEN'
                }
            }
        }
        
        
        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Collect JaCoCo Coverage') {
            steps{
                   jacoco(execPattern: '**/target/jacoco.exec')
    }
        }

    stage('JUNIT TEST with JaCoCo') {
      steps {
        sh 'mvn test jacoco:report'
        echo 'Test stage done'
      }
    }
          stage("Deploy Artifact to Nexus") {
            steps {
                sh "mvn deploy -DskipTests"
            }
        }    
        stage("Build docker image") {
            steps {
                sh "docker build -t ski ."
            }
        }
      
          stage('Deploy Image to Docker Hub') {
            steps {
             script {
            // Define your Docker access token
                  def dockerAccessToken = 'dckr_pat_8MwQzJ5aBugVs3THqKaNjUlQJKM'

            // Log in to Docker Hub with --password-stdin
                    sh "echo '${dockerAccessToken}' | docker login -u yessinedocker --password-stdin"
            // Tag and push the Docker image
                    sh "docker tag ski yessinedocker/ski"
                    sh "docker push yessinedocker/ski"
            }
            }
        }
        
        stage("Run docker containers") {
            steps {
                sh "docker compose up -d"
            }
        }
        
        // stage("Build docker image") {
        //     steps {
        //         sh "sudo docker build -t  . "
        //     }
        // }
    }
    //     stage("Build Docker image") {
    //         steps {
    //             script {
    //                 dockerImage = docker.build(dockerImageName)
    //             }
    //         }
    //     }

    //     stage("Start app and db") {
    //         steps {
    //             sh "docker-compose up -d"
    //         }
    //     }
    // }
    

    post {
        always {
            cleanWs()
        }
    }
       
}