pipeline{
    agent any
    parameters{
        booleanParam(name: 'DEPLOY_APPROVAL', defaultValue: false, description: 'Approve Deployment?')
        }

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
            when{
                // Manual approval required to proceed with deployment
                expression { params.DEPLOY_APPROVAL == true}
            }
            steps{
                sh 'docker build -f Dockerfile -t myapp . '
                sh 'docker rm -f "myappcontainer" || true'
                sh 'docker run --name "myappcontainer" -p 9090:8081 --detach myapp:latest'
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