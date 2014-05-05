package net.mybecks.duffy.util;

import java.net.MalformedURLException;

import net.mybecks.duffy.pojo.Package;
import net.mybecks.duffy.pojo.Settings;
import net.mybecks.duffy.ui.UiBuilder;
import net.mybecks.duffy.ui.table.TableBuilder;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonParser {
	private static final Logger LOG = LoggerFactory.getLogger(JsonParser.class);

	public static void parsePackageFile(String content){
		LOG.info("Start parsing");
		JSONObject json = (JSONObject) JSONSerializer.toJSON(content);
		JSONObject packagesObj = json.getJSONObject("devDependencies");
		JSONObject depObj = json.getJSONObject("dependencies");
		
		savePackageProperties(packagesObj);
		savePackageProperties(depObj);
		
		
		LOG.info(Settings.getInstance().toString());
		TableBuilder.refreshTableData();
	}
	
	private static void savePackageProperties(JSONObject currentObj){
		Object[] packageNames = currentObj.keySet().toArray();
		for(int i=0; i<packageNames.length; i++){
			Package pckg = new Package();
			pckg.setName(packageNames[i].toString());
			String localVersion = currentObj.getString(packageNames[i].toString());
			String pattern = "[~^]";
			pckg.setLocalVersion(localVersion.replaceAll(pattern,""));
			
			try {
				pckg.setRemoteVersion(RemoteVersionChecker.getRemoteVersion(packageNames[i].toString()));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			
			LOG.info("Add package: "+pckg.getName());
			Settings.getInstance().addPackage(pckg);
		}

	}
}
