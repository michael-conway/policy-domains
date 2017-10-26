
ingestPostProcSipDepositInLandingZone {
#Input parameters are:
#  data object path
#  user
#  zone
#Output parameter is:
#
  writeLine("stdout","deposit of sip  *dataObjectPath");
}
INPUT *dataObjectPath="/path",*user="test",*zone="zone"
OUTPUT ruleExecOut
