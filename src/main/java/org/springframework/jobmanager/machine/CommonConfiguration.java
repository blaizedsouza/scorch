/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.jobmanager.machine;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.event.OnStateEntryEvent;
import org.springframework.statemachine.event.OnStateExitEvent;
import org.springframework.statemachine.event.StateMachineEvent;

@Configuration
public class CommonConfiguration {

	private final static Log log = LogFactory.getLog(CommonConfiguration.class);

	@Configuration
	static class ApplicationConfig {

		@Bean
		public TestEventListener testEventListener() {
			return new TestEventListener();
		}
	}

	static class TestEventListener implements ApplicationListener<StateMachineEvent> {

		@Override
		public void onApplicationEvent(StateMachineEvent event) {
			if (event instanceof OnStateEntryEvent) {
				OnStateEntryEvent e = (OnStateEntryEvent)event;
				log.info("Entry state " + e.getState().getId());
			} else if (event instanceof OnStateExitEvent) {
				OnStateExitEvent e = (OnStateExitEvent)event;
				log.info("Exit state " + e.getState().getId());
			}
		}

	}

}
