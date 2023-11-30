pipeline 
{
    agent any
    
    tools{
    	maven 'maven'
        }
        
    environment{
   
        BUILD_NUMBER = "${BUILD_NUMBER}"
   
    }
    

    stages 
    {
        stage('Build') 
        {
            steps
            {
                 git 'https://github.com/jglick/simple-maven-project-with-tests.git'
                 bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
            post 
            {
                success
                {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
        
        
        stage("Deploy to QA"){
            steps{
                echo("deploy to qa done")
            }
        }
                    
        stage('Regression Automation Test'){
           steps{
              catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE'){
                 git 'https://github.com/debasmita-a/gorest-API.git'
                 sh "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testrunners/testng_regression.xml"
              }
           }
        }        

       stage('Publish Allure Reports'){
          steps{
             script{
                 allure([
                       includeProperties: false,
                       jdk: '',
                       properties: [],
                       reportBuildPolicy: 'ALWAYS',
                       results: [[path: '/allure-results']]
                       ])
             }
          }
       }
		stage('Publish Regression Extent Report'){
            steps{
                     publishHTML([allowMissing: false,
                                  alwaysLinkToLastBuild: false, 
                                  keepAll: false, 
                                  reportDir: 'target', 
                                  reportFiles: 'APIExecutionReport.html', 
                                  reportName: 'API HTML Regression Extent Report', 
                                  reportTitles: ''])
            }
        }
        
          stage("Deploy to STAGE"){
            steps{
                echo("deploy to Stage done")
            }
        }
                    
        stage('Sanity Automation Test'){
           steps{
              catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE'){
                 git 'https://github.com/debasmita-a/gorest-API.git'
                 sh "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testrunners/testng_sanity.xml"
              }
           }
        }        

       stage('Publish Allure Reports'){
          steps{
             script{
                 allure([
                       includeProperties: false,
                       jdk: '',
                       properties: [],
                       reportBuildPolicy: 'ALWAYS',
                       results: [[path: '/allure-results']]
                       ])
             }
          }
       }
       
		stage('Publish Regression Extent Report'){
            steps{
                     publishHTML([allowMissing: false,
                                  alwaysLinkToLastBuild: false, 
                                  keepAll: false, 
                                  reportDir: 'target', 
                                  reportFiles: 'APIExecutionReport.html', 
                                  reportName: 'API HTML Regression Extent Report', 
                                  reportTitles: ''])
            }
        }
      
    }
}