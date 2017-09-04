# unit tests for pd-message.py
import init_domain
import logging
import unittest
import sys
sys.path.append('../src')

import init_domain
from logging.config import fileConfig

logging.config.fileConfig("logging_config.ini")
logger = logging.getLogger(__name__)
logger.info("Hi from test_init_domain.py")

class InitDomainTest(unittest.TestCase):

    def setUp(self):
        pass

    def test_init(self):
        """test call setup with localhost"""
        logging.info("test_init()")
        init_domain.init_domain_exchanges("localhost", "testdomain", ["testexch1", "testexch2"])


if __name__ == "__main__":
    unittest.main()