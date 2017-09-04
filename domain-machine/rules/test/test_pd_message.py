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


    def test_connect(self):
        """testconnect"""
        logger.info("testConnect()")
        pdm = pd_message.PdMessage("localhost")
        pdm.connect()
        self.assertTrue(pdm.connection,"connection not created")

    def test_setup(self):
        """test call setup with localhost"""
        pdm = pd_message.PdMessage("localhost")
        pdm.setup("testdomain","testexchange")
        self.assertTrue(pdm.connection,"connection not created")


if __name__ == "__main__":
    unittest.main()