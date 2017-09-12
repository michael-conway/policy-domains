# general message functions for python
import logging
import pika
import json
from logging.config import fileConfig


class PdMessage:
    "default constructor"

    def __init__(self,host="localhost"):
        self.logger = logging.getLogger(__name__)
        self.logger.info("initializing PdMessage()")
        self.connection = None
        self.host = host

    def connect(self, host="localhost"):
        """connect to the queue"""
        self.logger.info("connecting to %s" % host)
        self.connection = pika.BlockingConnection(pika.ConnectionParameters(host))
        self.logger.info("connected!")

    def setup(self, domain, exchange, persistent="true"):
        """setup of queue/exchange"""
        self.logger.info("setup(domain=%s, exchange=%s)" % (domain,exchange))
        if self.connection is None:
            self.logger.info("connecting...")
            self.connect(self.host)
        definedExchange = ":".join([domain,exchange])
        channel = self.connection.channel()
        channel.exchange_declare(exchange=domain,
                                 type='direct')
        channel.queue_declare(queue=definedExchange,durable=True)
        channel.queue_bind(exchange=domain,
                   queue=definedExchange,
                   routing_key=definedExchange)


    def jsonizeRei(self, inputRei):
        """turn rei structure into the transmission format"""
        json.dump(inputRei)

    def send(self, domain, region, eventType, rei):
        """send the serialized event data to the appropriate topic"""
        self.logger.info("sending to domain: %s" % domain)
        self.logger.info("region: %s" % region)
        self.logger.info("eventType: %s" % eventType)
        definedExchange = ":".join([domain, region])
        channel = self.connection.channel()
        channel.basic_publish(exchange=domain,
                              routing_key=definedExchange,
                              body=self.jsonizeRei(rei))



