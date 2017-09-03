# general message functions for python
import logging
import pika
import json
from logging.config import fileConfig

class PdMessage():
	"default constructor"
	def __init__(self):
		self.logger = logger = logging.getLogger(__name__)
		self.logger.info("initializing PdMessage()")
		

	def connect(self,host="localhost"):
		"""connect to the queue"""
		self.logger.info("connecting to %s" % host)
		connection = pika.BlockingConnection(pika.ConnectionParameters(host))
		channel = connection.channel()
		self.logger.info("connected!")
		return  channel

	