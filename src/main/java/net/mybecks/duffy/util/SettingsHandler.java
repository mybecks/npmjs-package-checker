package net.mybecks.duffy.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import net.mybecks.duffy.pojo.Settings;
import net.mybecks.duffy.ui.UiBuilder;

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
					content = FileHandler.readFile(Settings.getInstance().getPackageJsonPath(),
							StandardCharsets.UTF_8);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
						
				LOG.info("Path of package.json file: "+Settings.getInstance().getPackageJsonPath());
				JsonParser.parsePackageFile(content);
			}
		}).start();
		
	}
}
