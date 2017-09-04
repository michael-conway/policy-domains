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
        self.logger.info("setup()")
        if self.connection is None:
            self.logger.info("connecting...")
            self.connect(self.host)
        channel = self.connection.channel()
        channel.exchange_declare(exchange=(":".join([domain,exchange])),
                                 type='direct')
