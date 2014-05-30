package net.mybecks.duffy.pojo;

import java.util.ArrayList;
import java.util.List;

public class Settings {
	private static Settings instance = new Settings();
	private List<Package> packages;
	private String packageJsonPath;
	private String nodeInstallationPath;
	private String repositoryUrl;
	
	private Settings() {
		packages = new ArrayList<Package>();
	}

	public static Settings getInstance() {
		return instance;
	}
	
	public void addPackage(Package pckg){
		this.packages.add(pckg);
	}
	
	public List<Package> getPackages(){
		return packages;
	}
	
	public Object[][] getPackagesAsMultiDimArray(){
		Object[][] data = new Object[packages.size()][3];
		
		for(int i=0; i<packages.size(); i++){

			String remoteVersion = packages.get(i).getRemoteVersion() == null ? "0.0.0" : packages.get(i).getRemoteVersion();

			data[i][0] = packages.get(i).getName();
			data[i][1] = packages.get(i).getLocalVersion();
			data[i][2] = remoteVersion;
		}
		return data;
	}
	
	public String getPackageJsonPath() {
		return packageJsonPath;
	}

	public void setPackageJsonPath(String packageJsonPath) {
		this.packageJsonPath = packageJsonPath;
	}
		
	public String getNodeInstallationPath() {
		return nodeInstallationPath;
	}

	public void setNodeInstallationPath(String nodeInstallationPath) {
		this.nodeInstallationPath = nodeInstallationPath;
	}

	public String getRepositoryUrl() {
		return repositoryUrl;
	}

	public void setRepositoryUrl(String repositoryUrl) {
		this.repositoryUrl = repositoryUrl;
	}

	@Override
	public String toString() {
		return "Settings [packages=" + packages + ", packageJsonPath="
				+ packageJsonPath + ", repositoryUrl=" + repositoryUrl + "]";
	}
}
