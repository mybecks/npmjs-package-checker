package net.mybecks.duffy.pojo;

public class Package {

	private String name;
	private String localVersion;
	private String remoteVersion;

	public Package() {

	}

	public Package(String name, String localVersion, String remoteVersion) {
		super();
		this.name = name;
		this.localVersion = localVersion;
		this.remoteVersion = remoteVersion;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocalVersion() {
		return localVersion;
	}

	public void setLocalVersion(String localVersion) {
		this.localVersion = localVersion;
	}

	public String getRemoteVersion() {
		return remoteVersion;
	}

	public void setRemoteVersion(String remoteVersion) {
		this.remoteVersion = remoteVersion;
	}

	@Override
	public String toString() {
		return "Package [name=" + name + ", localVersion=" + localVersion
				+ ", remoteVersion=" + remoteVersion + "]";
	}

}
