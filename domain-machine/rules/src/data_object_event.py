# data object event serialization from rule engine
class DataObjectEvent():
    """Data object event serialization"""
    def __init__(self, rei=None):

        if (rei is None):
            self.dataPath = ""
            self.userName = ""
            self.zone = ""
        else:
            self.dataPath = rei['doi']['filePath']
            self.userName = rei['uoic']['userName']
            self.zone = rei['uoic']['rodsZone']


