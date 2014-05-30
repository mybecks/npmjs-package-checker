package net.mybecks.duffy.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import net.mybecks.duffy.pojo.Settings;
import net.mybecks.duffy.ui.UiBuilder;
import net.mybecks.duffy.ui.table.TableBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SettingsHandler {
	private static final Logger LOG = LoggerFactory.getLogger(SettingsHandler.class);	
	
	public static void refreshData(){
		UiBuilder.updateLastUpdatedTime();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {				
				String content = null;
				try {
					TableBuilder.clearTableData();
					
					if(Settings.getInstance().getNodeInstallationPath() != null && !Settings.getInstance().getNodeInstallationPath().isEmpty()){
						LOG.info("Starting to fetch global packages from "+Settings.getInstance().getNodeInstallationPath());
						FileHandler.findGlobalPackages();
					} 
					
					if(Settings.getInstance().getPackageJsonPath() != null && !Settings.getInstance().getPackageJsonPath().isEmpty()){
						LOG.info("Starting to fetch lokal packages from "+Settings.getInstance().getPackageJsonPath());
						content = FileHandler.readFile(Settings.getInstance().getPackageJsonPath(),
								StandardCharsets.UTF_8);
						JsonParser.parsePackageFile(content);
					}			
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}).start();
		
	}
}
