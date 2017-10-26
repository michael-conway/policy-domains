/**
 * 
 */
package org.angrygoat.domain.ingest.messaging;

import java.io.IOException;

import org.angrygoat.domain.ingest.config.IngestEvents;
import org.angrygoat.domain.ingest.config.PolicyDomainContext;
import org.angrygoat.domainmachine.domain.DataObjectEvent;
import org.angrygoat.domainmachine.exception.PolicyDomainRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.amqp.inbound.AmqpInboundChannelAdapter;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Adapter that processes inbound landing zone messages
 * 
 * @author mcc
 *
 */
@Component

public class IngestLandingZoneMessageAdapter {

	public static final Logger log = LoggerFactory.getLogger(IngestLandingZoneMessageAdapter.class);

	@Autowired
	private PolicyDomainContext policyDomainContext;

	@Bean
	public MessageChannel amqpInputChannel() {
		return new DirectChannel();
	}

	@Bean
	public AmqpInboundChannelAdapter inbound(SimpleMessageListenerContainer listenerContainer,
			@Qualifier("ingestLandingZoneInputChannel") MessageChannel channel) {
		log.info("inbound()");
		AmqpInboundChannelAdapter adapter = new AmqpInboundChannelAdapter(listenerContainer);
		adapter.setOutputChannel(channel);
		return adapter;
	}

	@Bean
	public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
		log.info("container()");
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
		container.setQueueNames("ingest:landing_zone");
		container.setConcurrentConsumers(2);
		// ...
		return container;
	}

	@Bean
	@ServiceActivator(inputChannel = "ingestLandingZoneInputChannel")
	public MessageHandler handler() {
		log.info("handler()");
		return new MessageHandler() {

			@Override
			public void handleMessage(Message<?> message) throws MessagingException {
				log.info("ingest:landing_zone message: {}",  message.getPayload().toString());
				String payStr = new String((byte[]) message.getPayload());
				log.info("paystr:{}", payStr);
				ObjectMapper objectMapper = new ObjectMapper();
				try {
					DataObjectEvent doe = objectMapper.readValue(payStr, DataObjectEvent.class);
					log.info("data object event:{}", doe);
					
					 Message<String> smMessage = MessageBuilder
					            .withPayload(IngestEvents.DEPOSIT_SIP_IN_LANDING_AREA.toString())
					            .setHeader("foo", "bar")
					            .build();
					    policyDomainContext.getStateMachine().sendEvent(smMessage);
						
				} catch (IOException e) {
					log.error("exception converting event", e);
					throw new PolicyDomainRuntimeException("cannot convert data object event", e);
				}
			}

		};
	}

	/**
	 * @return the policyDomainContext
	 */
	public PolicyDomainContext getPolicyDomainContext() {
		return policyDomainContext;
	}

	/**
	 * @param policyDomainContext
	 *            the policyDomainContext to set
	 */
	public void setPolicyDomainContext(PolicyDomainContext policyDomainContext) {
		log.info("setting policyDomainContext");
		this.policyDomainContext = policyDomainContext;
		log.info("set policy domain context");
	}

}
