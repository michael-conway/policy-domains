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
host = "irods421.irodslocal"

class PdTest(unittest.TestCase):

    host = "irods421.irodslocal"

    def setUp(self):
        pass


    def test_connect(self):
        """testconnect"""
        logger.info("testConnect()")
        pdm = pd_message.PdMessage(host)
        pdm.connect()
        self.assertTrue(pdm.connection,"connection not created")

    def test_setup(self):
        """test call setup with localhost"""
        pdm = pd_message.PdMessage(host)
        pdm.setup("testdomain","testexchange")
        self.assertTrue(pdm.connection,"connection not created")

    def test_send_acPostProcForPut(self):
        """test sending acpostprocforput"""
        rei = {"doi":{"filePath":"/a/path.txt"},"uoic":{"userName":"user", "rodsZone":"zone"}}

        pdm = pd_message.PdMessage(host)
        pdm.setup("testdomain", "testexchange")
        pdm.send("testdomain","landing_zone","acPostProcForPut", rei)

if __name__ == "__main__":
    unittest.main()