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
                sh "mvn clean"
                sh "mvn dependency:copy-dependencies"
                sh "mvn compiler:compile"
            }
        }

        stage('Package'){
            steps{
                //Package files with Maven
                sh "mvn package"
                archiveArtifacts artifacts: 'target/*.war', allowEmptyArchive: true
            }
        }

        stage('Deploy'){

            steps{
                timeout(time: 10, unit: 'MINUTES') {
                    input message: 'Deploy to Production?', ok: 'Yes'
                }
                echo 'Initiating Deployment'
                sh 'docker build -f Dockerfile -t myapp . '
                sh 'docker rm -f "myappcontainer" || true'
                sh 'docker run --name "myappcontainer" -p 9090:8080 --detach myapp:latest'
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