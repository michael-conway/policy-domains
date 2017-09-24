/**
 * 
 */
package org.angrygoat.domainmachine.domain;

/**
 * Event on a data object
 * @author Mike
 *
 */
public class DataObjectEvent {
	private String clientUser = "";
	private String event = "";
	private String filePath = "";
	private String zone = "";
	public String getClientUser() {
		return clientUser;
	}
	public void setClientUser(String clientUser) {
		this.clientUser = clientUser;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DataObjectEvent [");
		if (clientUser != null)
			builder.append("clientUser=").append(clientUser).append(", ");
		if (event != null)
			builder.append("event=").append(event).append(", ");
		if (filePath != null)
			builder.append("filePath=").append(filePath).append(", ");
		if (zone != null)
			builder.append("zone=").append(zone);
		builder.append("]");
		return builder.toString();
	}
	
	
}
