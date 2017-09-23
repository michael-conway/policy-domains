import json
import session_vars
# data object event serialization from rule engine
class DataObjectEvent():
    """Data object event serialization"""
    def __init__(self, rei=None, event=""):
        self.event = event
        sv = session_vars.get_map(rei)
        self.filePath = sv['data_object']['object_path']
        self.clientUser = sv['client_user']

    def toJSON(self):
        return json.dumps(self, default=lambda o: o.__dict__,
                          sort_keys=True, indent=4)