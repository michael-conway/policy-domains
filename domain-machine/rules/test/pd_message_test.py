# unit tests for pd-message.py
import logging
import unittest
import sys
sys.path.append('../src')

import pd_message
from logging.config import fileConfig

logging.config.fileConfig("logging_config.ini")
logger = logging.getLogger(__name__)
logger.info("Hi from init")



class PdTest(unittest.TestCase):

    def setUp(self):
        pass

    def test_foo(self):
        pass

    def test_connect(self):
        """testconnect"""
        print("in test")
        logger.info("testConnect()")
        pdm = pd_message.PdMessage()
        connection = pdm.connect("localhost")
        self.assertTrue(connection,"connection not created")


if __name__ == "__main__":
    unittest.main()