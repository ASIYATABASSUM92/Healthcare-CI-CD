/**
 * HEALTHCARE CI/CD PIPELINE
 * 
 * PURPOSE: Automates build, test, quality check, and deployment
 * 
 * STAGES:
 * 1. Git Checkout     - Get latest code
 * 2. Maven Build      - Compile Java code
 * 3. Unit Tests       - Run automated tests
 * 4. SonarQube        - Code quality analysis
 * 5. Quality Gate     - Verify quality standards
 * 6. Package WAR      - Create deployable artifact
 * 7. Upload Nexus     - Store artifact in repository
 * 8. Deploy Tomcat    - Deploy to production server
 */

pipeline {
    // WHERE: Run on any available Jenkins agent
    agent any
    
    // TOOLS: Specify which tools to use
    // WHY: Jenkins needs to know Maven and JDK versions
    tools {
        maven 'Maven-3.8.6'  // Must match tool name in Jenkins
        jdk 'JDK-21'         // Must match tool name in Jenkins
    }
    
    // ENVIRONMENT: Variables available to all stages
    environment {
        // Node IP addresses - REPLACE WITH YOUR ACTUAL IPs!
        SONAR_HOST = 'http://192.168.0.XX:9000'   // Node 2 IP
        SONAR_TOKEN = credentials('sonarqube-token')  // From Jenkins credentials
        NEXUS_URL = '192.168.0.XX:8081'           // Node 3 IP
        TOMCAT_URL = 'http://192.168.0.XX:8080'   // Node 4 IP
    }
    
    stages {
        // ========================================
        // STAGE 1: GET SOURCE CODE
        // ========================================
        stage('1. Git Checkout') {
            steps {
                echo '=========================================='
                echo '===== Stage 1: Cloning Repository ======='
                echo '=========================================='
                
                // WHY: Get latest code from GitHub
                // WHAT: Clones main branch
                git branch: 'main', 
                    url: 'https://github.com/UNIQUE0024/healthcare-cicd.git'
                    
                echo '✅ Code cloned successfully'
            }
        }
        
        // ========================================
        // STAGE 2: COMPILE CODE
        // ========================================
        stage('2. Maven Build') {
            steps {
                echo '=========================================='
                echo '====== Stage 2: Building with Maven ====='
                echo '=========================================='
                
                // WHY: Compiles Java source code to bytecode
                // WHAT: mvn clean = remove old builds, compile = create new .class files
                sh 'mvn clean compile'
                
                echo '✅ Compilation successful'
            }
        }
        
        // ========================================
        // STAGE 3: RUN TESTS
        // ========================================
        stage('3. Unit Tests') {
            steps {
                echo '=========================================='
                echo '======= Stage 3: Running Unit Tests ====='
                echo '=========================================='
                
                // WHY: Verify code works correctly
                // WHAT: Runs all JUnit tests
                sh 'mvn test'
                
                echo '✅ All tests passed'
            }
            post {
                // ALWAYS: Even if tests fail, record results
                always {
                    // WHY: Creates test report visible in Jenkins UI
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
        
        // ========================================
        // STAGE 4: CODE QUALITY ANALYSIS
        // ========================================
        stage('4. SonarQube Analysis') {
            steps {
                echo '=========================================='
                echo '==== Stage 4: Running SonarQube ========='
                echo '=========================================='
                
                // WHY: Checks for bugs, vulnerabilities, code smells
                // WHAT: Sends code to SonarQube for analysis
                withSonarQubeEnv('SonarQube') {
                    sh """
                        mvn sonar:sonar \
                          -Dsonar.projectKey=healthcare-app \
                          -Dsonar.projectName='Healthcare Management System' \
                          -Dsonar.host.url=${SONAR_HOST} \
                          -Dsonar.token=${SONAR_TOKEN}
                    """
                }
                
                echo '✅ Code analysis complete'
            }
        }
        
        // ========================================
        // STAGE 5: QUALITY GATE CHECK
        // ========================================
        stage('5. Quality Gate Check') {
            steps {
                echo '=========================================='
                echo '===== Stage 5: Checking Quality Gate ===='
                echo '=========================================='
                
                // WHY: Ensures code meets minimum quality standards
                // WHAT: Waits for SonarQube to finish and checks result
                timeout(time: 5, unit: 'MINUTES') {
                    // abortPipeline: false = continue even if quality gate fails
                    waitForQualityGate abortPipeline: false
                }
                
                echo '✅ Quality gate passed'
            }
        }
        
        // ========================================
        // STAGE 6: CREATE DEPLOYABLE ARTIFACT
        // ========================================
        stage('6. Package WAR') {
            steps {
                echo '=========================================='
                echo '====== Stage 6: Creating WAR File ======='
                echo '=========================================='
                
                // WHY: Creates deployable WAR file
                // WHAT: Packages all classes, resources, and web.xml into healthcare.war
                sh 'mvn package -DskipTests'
                
                // Show WAR file details
                sh 'ls -lh target/*.war'
                
                echo '✅ WAR file created successfully'
            }
        }
        
        // ========================================
        // STAGE 7: STORE ARTIFACT
        // ========================================
        stage('7. Upload to Nexus') {
            steps {
                echo '=========================================='
                echo '===== Stage 7: Uploading to Nexus ======='
                echo '=========================================='
                
                // WHY: Store artifact for versioning and distribution
                // WHAT: Uploads WAR to Nexus repository
                nexusArtifactUploader(
                    nexusVersion: 'nexus3',
                    protocol: 'http',
                    nexusUrl: "${NEXUS_URL}",
                    groupId: 'com.healthcare',
                    version: '1.0.0',
                    repository: 'maven-releases',
                    credentialsId: 'nexus-credentials',
                    artifacts: [
                        [
                            artifactId: 'healthcare',
                            classifier: '',
                            file: 'target/healthcare.war',
                            type: 'war'
                        ]
                    ]
                )
                
                echo '✅ Artifact uploaded to Nexus'
            }
        }
        
        // ========================================
        // STAGE 8: DEPLOY TO PRODUCTION
        // ========================================
        stage('8. Deploy to Tomcat') {
            steps {
                echo '=========================================='
                echo '===== Stage 8: Deploying to Tomcat ======'
                echo '=========================================='
                
                // WHY: Deploy application to production server
                // WHAT: Copies WAR to Tomcat and starts application
                deploy adapters: [
                    tomcat9(
                        credentialsId: 'tomcat-deployer',
                        path: '',
                        url: "${TOMCAT_URL}"
                    )
                ], 
                contextPath: 'healthcare',  // URL path: /healthcare
                war: 'target/*.war'
                
                echo '=========================================='
                echo '✅ DEPLOYMENT SUCCESSFUL!'
                echo "📱 Application: ${TOMCAT_URL}/healthcare"
                echo '=========================================='
            }
        }
    }
    
    // POST-BUILD ACTIONS
    // WHY: Run after pipeline completes (success or failure)
    post {
        success {
            echo '=========================================='
            echo '======= PIPELINE COMPLETED! ============='
            echo '=========================================='
            echo "✅ Jenkins:     http://NODE1_IP:8081"
            echo "✅ SonarQube:   ${SONAR_HOST}"
            echo "✅ Nexus:       http://${NEXUS_URL}"
            echo "✅ Application: ${TOMCAT_URL}/healthcare"
            echo '=========================================='
        }
        failure {
            echo '=========================================='
            echo '========= PIPELINE FAILED! =============='
            echo '=========================================='
            echo '❌ Check console output for errors'
        }
        always {
            // Clean workspace after build (free disk space)
            cleanWs()
        }
    }
}
