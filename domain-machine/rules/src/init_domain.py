# domain initialization code for policy domains
import pd_message

import logging

logging.config.fileConfig("/etc/irods/logging_config.ini")
logger = logging.getLogger(__name__)

def init_domain_exchanges(host, domain, exchanges):
    """set up the exchanges for the policy domain"""
    logger.info("initing domain exchanges for domain: %s" % domain)
    pdm = pd_message.PdMessage(host)
    pdm.connect()
    logger.info("connected to: %s" % host)
    for exchange in exchanges:
        logger.info("setting up exchange for %s" % exchange)
        pdm.setup(domain,  exchange)


