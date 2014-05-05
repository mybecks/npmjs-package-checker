package net.mybecks.duffy.util;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SystemUtils {
	private static final Logger LOG = LoggerFactory
			.getLogger(SystemUtils.class);

	public static void openUrlInDefaultBrowser(String url) throws IOException,
			URISyntaxException {
		
		if (Desktop.isDesktopSupported()) {
			Desktop desktop = Desktop.getDesktop();

			if (desktop.isSupported(Desktop.Action.BROWSE)) {
				URI uri = new URI(url);
				desktop.browse(uri);
			}
		}
	}
}
