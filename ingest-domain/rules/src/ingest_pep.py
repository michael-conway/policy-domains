# high level peps for ingest function

import logging
import pd_message
from logging.config import fileConfig

logging.config.fileConfig("/etc/irods/logging_config.ini")
logger = logging.getLogger(__name__)
logger.info("Hi from ingest_pep.py")

def ingestPostProcSipDepositInLandingZone(dataObjectPath, user, zone):
    """High level hook called when a SIP has been deposited in a landing zone"""
    logger.info("ingestPostProcSipDepositInLandingZone()")
    logger.info("dataObjectPath: %s" % dataObjectPath)
    logger.info("user:%s" % user)
    logger.info("zone:%s" % zone)