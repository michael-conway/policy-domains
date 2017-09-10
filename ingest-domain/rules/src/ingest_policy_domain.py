# ingest policy domain code

import logging
import init_domain

class IngestPolicyDomain:

    def __init__(self, host="localhost"):
        self.logger = logging.getLogger(__name__)
        self.host = host
        self.logger.info("initializing IngestPolicyDomain()")

    def initialize_domain(self):
        """initialize the ingest domain exchanges"""
        self.logger.info("initializing policy domain")
        init_domain.init_domain_exchanges(self.host, "ingest", ["landing_zone", "sip_validated", "aip_assembly", "aip_pickup_window"])


    def classify_path(self, path):
        self.logger.info("classify_path() for: %s" % path)
        if path.startswith("/zone1/home/ingest/landing_zone"):
            self.logger.info("landing_zone")
            return "landing_zone"

        if path.startswith("/zone1/home/ingest/sip_validated"):
            self.logger.info("sip_validated")
            return "sip_validated"

        if path.startswith("/zone1/home/ingest/aip_assembly"):
            self.logger.info("aip_assembly")
            return "aip_assembly"

        if path.startswith("/zone1/home/ingest/aip_pickup_window"):
            self.logger.info("aip_assembly")
            return "aip_pickup_window"

        raise LookupError("Cannot resolve path %s" % path)

if __name__ == '__main__':
    ipd = IngestPolicyDomain()
    ipd.initialize_domain("localhost")