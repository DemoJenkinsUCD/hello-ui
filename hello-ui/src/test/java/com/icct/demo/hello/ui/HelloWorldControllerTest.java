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

package com.icct.demo.hello.ui;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
public class HelloWorldControllerTest 
{
	@Mock private RestTemplate restTemplate;
	
	private Greeting greeting = new Greeting("Testing", "testers");
	
	private HelloWorldController controller = new HelloWorldController();
	
	@Before
	public void init()
	{
		when(restTemplate.getForObject(anyString(), eq(Greeting.class))).thenReturn(greeting);
		controller.setTemplate(restTemplate);
	}
	
	@Test
	public void testGreeting()
	{
		ModelMap map = controller.greet();
		
		Greeting result = (Greeting) map.get(HelloWorldController.PARM_HELLO);
		
		assertEquals("Greeting returned", greeting, result);
	}
}
