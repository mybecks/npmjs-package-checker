package net.mybecks.duffy.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.mybecks.duffy.pojo.Settings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RemoteVersionChecker {
	private static final Logger LOG = LoggerFactory.getLogger(RemoteVersionChecker.class);
	public RemoteVersionChecker(){
		
	}
	
	public static String getRemoteVersion(String dependencyName) throws MalformedURLException{
		URL url = new URL(Settings.getInstance().getRepositoryUrl()+dependencyName);
		String urlContent;
		String remoteVersion = null;
		try {
			urlContent = readUrlContent(url);
			remoteVersion = parseUrlContent(urlContent);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return remoteVersion;
	}
	
	private static String readUrlContent(URL url) throws IOException{
		InputStream in = url.openStream();
		BufferedReader br = new BufferedReader(
                new InputStreamReader(in));

		String inputLine;
		StringBuilder sb = new StringBuilder();
		while ((inputLine = br.readLine()) != null) {
			sb.append(inputLine);
		}
		br.close();
		return sb.toString();
	}
	
	//TODO another Thread
	private static String parseUrlContent(String content){
	    Pattern pattern = Pattern.compile("\\d+\\.\\d+\\.\\d+");
	    Matcher matcher = pattern.matcher(content);

	    while (matcher.find()) {
	    	LOG.info(matcher.group());
	    	return matcher.group();
	    }
		return null;
	}
}
