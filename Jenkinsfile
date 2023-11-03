pipeline{
    agent any

    stages{
        stage('GetProject'){
            steps{
                git 'https://github.com/jackroberts1993/jackspetitions.git'
            }
        }

        stage('Build'){
            steps{
                //Run Maven on an agent on Linux Machine with Maven installed
                sh "mvn clean:clean"
                sh "mvn dependency:copy-dependencies"
                sh "mvn compiler:compile"

            }
        }

        stage('Package'){
            steps{
                //Package files with Maven
                sh "mvn package"

            }
        }

        stage('Execute'){
            steps{
                sh "mvn exec:java"
            }
        }

        stage ('Deploy')
                {
                    steps {
                        sh 'docker build -f Dockerfile -t myapp . '
                        sh 'docker rm -f "myappcontainer" || true'
                        sh 'docker run --name "myappcontainer" -p 8081:8080 --detach myapp:latest'
                    }
                }
    }
    //Check if package successful
    post{
        success{
            archiveArtifacts allowEmptyArchive: true,
                artifacts: '**/jackspetitions.war'
            }
        }
}