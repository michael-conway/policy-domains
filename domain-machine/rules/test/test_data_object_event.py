# unit tests for pd-message.py
import data_object_event
import logging
import unittest
import sys
sys.path.append('../src')
from logging.config import fileConfig

logging.config.fileConfig("logging_config.ini")
logger = logging.getLogger(__name__)
logger.info("Hi from test_data_object_event.py")

class DataObjectEventTest(unittest.TestCase):

    def setUp(self):
        pass

    def test_create_with_rei(self):
        """create an event object based on rei"""
        logging.info("test_create_with_rei()")
        rei = {"doi":{"filePath":"/a/path.txt"},"uoic":{"userName":"user", "rodsZone":"zone"}}
        doe = data_object_event.DataObjectEvent(rei)
        self.assertEqual("/a/path.txt", doe.dataPath)

if __name__ == "__main__":
    unittest.main()