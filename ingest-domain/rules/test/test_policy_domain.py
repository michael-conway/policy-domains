# unit tests for pd-message.py
import logging
import unittest
import sys
sys.path.append('../src')
import ingest_policy_domain

from logging.config import fileConfig

logging.config.fileConfig("/etc/irods/logging_config.ini")
logger = logging.getLogger(__name__)
logger.info("Hi from test_policy_domain.py")

class PolicyDomainTest(unittest.TestCase):

    def setUp(self):
        pass

    def test_resolve_landing(self):
        """test resolve path to landing_zone domain"""
        ipd = ingest_policy_domain.IngestPolicyDomain("localhost")
        path = ipd.classify_path("/zone1/home/ingest/landing_zone")
        self.assertEquals("landing_zone", path)

    def test_initialize(self):
        """initialize the ingest policy domain"""
        ipd = ingest_policy_domain.IngestPolicyDomain("localhost")
        ipd.initialize_domain()
        # add test assertions



if __name__ == "__main__":
    unittest.main()