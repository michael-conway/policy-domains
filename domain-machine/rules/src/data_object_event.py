import json
import session_vars
# data object event serialization from rule engine
class DataObjectEvent():
    """Data object event serialization"""
    def __init__(self, rei=None, event=""):
        self.event = event
        sv = session_vars.get_map(rei)
        self.filePath = sv['data_object']['object_path']
        self.clientUser = sv['client_user']['user_name']
        self.zone = sv['client_user']['irods_zone']

    def toJSON(self):

        outobj = {"filePath":self.filePath, "clientUser": self.clientUser, "zone": self.zone, "event": self.event}

        return json.dumps(outobj,
                          sort_keys=True, indent=4)