callIngestPostProcSipDepositInLandingZone {
  ingestPostProcSipDepositInLandingZone(*dataObjectPath,*user,*zone);
  writeLine("stdout","called ingestPostProcSipDepositInLandingZone");
}
INPUT *dataObjectPath="/path",*user="test",*zone="zone"
OUTPUT ruleExecOut
