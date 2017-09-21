# general message functions for python
import logging
import pika
import json
from logging.config import fileConfig
import data_object_event

class PdMessage:
    "default constructor"

    def __init__(self,host="irods421.irodslocal"):
        self.logger = logging.getLogger(__name__)
        self.logger.info("initializing PdMessage()")
        self.connection = None
        self.host = host

    def connect(self):
        """connect to the queue"""
        self.logger.info("connecting to %s" % self.host)
        self.connection = pika.BlockingConnection(pika.ConnectionParameters(self.host))
        self.logger.info("connected!")

    def setup(self, domain, exchange, persistent="true"):
        """setup of queue/exchange"""
        self.logger.info("setup(domain=%s, exchange=%s)" % (domain,exchange))
        if self.connection is None:
            self.logger.info("connecting...")
            self.connect()
        definedExchange = ":".join([domain,exchange])
        channel = self.connection.channel()
        channel.exchange_declare(exchange=domain,
                                 exchange_type='direct')
        channel.queue_declare(queue=definedExchange,durable=True)
        channel.queue_bind(exchange=domain,
                   queue=definedExchange,
                   routing_key=definedExchange)

    def close(self):
        """close conn after operation"""

    def jsonizeRei(self, inputRei):
        """turn rei structure into the transmission format"""
        json.dump(inputRei)

    def send(self, domain, region, eventType, rei):
        """send the serialized event data to the appropriate topic"""
        self.logger.info("sending to domain: %s" % domain)
        self.logger.info("region: %s" % region)
        self.logger.info("eventType: %s" % eventType)

        jsonMessage = ""
        if (eventType == "acPostProcForPut"):
            jsonMessage = self.createDataObjectEvent(eventType, rei)
        else:
            return

        definedExchange = ":".join([domain, region])
        channel = self.connection.channel()
        channel.basic_publish(exchange=domain,
                              routing_key=definedExchange,
                              body=jsonMessage)
        self.close()

    def createDataObjectEvent(self,eventType, rei):
        """create the data object event for the event type and rei structure as a json string"""
        self.logger.info("create data object event for eventType: %s" % eventType)

        evt = data_object_event.DataObjectEvent(rei, eventType)
        return evt.toJSON()
