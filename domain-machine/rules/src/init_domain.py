# domain initialization code for policy domains
import pd_message

import logging

logging.config.fileConfig("/etc/irods/logging_config.ini")
logger = logging.getLogger(__name__)

def init_domain_exchanges(host, domain, regions):
    """set up the exchanges for the policy domain"""
    logger.info("initing domain exchanges for domain: %s" % domain)
    pdm = pd_message.PdMessage(host)
    pdm.connect()
    logger.info("connected to: %s" % host)
    for region in regions:
        logger.info("setting up exchange %s and queue/route for %s" % (domain,region))
        pdm.setup(domain, region)


