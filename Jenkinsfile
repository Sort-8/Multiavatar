pipeline {
    agent {
      node {
          label 'master'
       }
    }
    tools {
        maven 'maven'
    }
    options { timeout(time: 4, unit: 'MINUTES') }
    stages {
        stage('Pull') {
            options{
                timeout(time:20,unit:'SECONDS')
            }
            steps {
                echo 'Pulling..'
                git 'https://github.com/Sort-8/Multiavatar.git'
            }
        }
        stage('Build') {
            steps {
                echo 'Building..'
                sh label: 'mvn构建', script: 'mvn clean && mvn install'
            }
        }
        stage('Run') {
            steps{
                sh"""JENKINS_NODE_COOKIE=dontkillme sh multiavatar.sh restart """
            }
        }
        stage('Send Email'){
            steps {
                emailext body: '${DEFAULT_CONTENT}', mimeType: 'text/html', subject: '自动化构建通知：${PROJECT_NAME} - Build # ${BUILD_NUMBER} - ${BUILD_STATUS}!', to: '${DEFAULT_RECIPIENTS}'
            }
        }
    }
}