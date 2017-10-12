/*
 *    Copyright 2017 Information Control Company
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@Library('ucd_demo')
import createApplicationSnapshotInUrbanCode
import deployApplicationWithUrbanCode
import publishVersionToUrbanCode
import runCucumberTest
import runMvn

pipeline
{
	agent any
	
	environment
	{
		UCD_SITE = 'ucd_demo'
		UCD_URL = 'https://ucd-server:8443'
		UCD_CREDENTIAL = 'UCD-admin'
	}

	stages
	{
		stage('Build')
		{
			steps
			{
				dir('hello-ui')
				{
					runMvn 'package'
				}
			}
		}
		
		stage('Publish')
		{
			steps
			{
				publishVersionToUrbanCode 'hello-ui', 'hello-world', "${BUILD_NUMBER}", "${WORKSPACE}/hello-ui/target"
			}
		}
		
		stage('Deploy')
		{
			steps
			{
				deployApplicationWithUrbanCode 'hello-ui', 'hello-world', "${BUILD_NUMBER}", 'Dev', 'Deploy all to Tomcat'
			}
		}
		
		stage('Test')
		{
			steps
			{
				git 'file:///usr/demo/hello-test'
				runCucumberTest 'demo', 'test'
			}
		}
		
		stage('Snapshot')
		{
			steps
			{
				createApplicationSnapshotInUrbanCode 'hello-world', 'Dev', "hello.ui${BUILD_NUMBER}"
			}
		}
	}
}
