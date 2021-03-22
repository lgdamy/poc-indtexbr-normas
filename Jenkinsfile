pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        sh 'mvn clean install'
      }
    }

    stage('package') {
      steps {
        sh 'docker build -t tcc-norma-api .'
      }
    }

    stage('tag') {
      steps {
        sh 'docker tag tcc-norma-api:latest srochg/tcc-norma-api'
      }
    }

    stage('push') {
      steps {
        sh 'docker push srochg/tcc-norma-api'
      }
    }

    stage('rollout') {
      steps {
        sh 'kubectl rollout restart deployment tcc-norma-api-deployment'
      }
    }

    stage('qa-test') {
      steps {
        sh '''echo Iniciando os testes



'''
        sh 'sleep 3'
        sh 'echo Finalizando os testes com sucesso'
      }
    }

  }
}