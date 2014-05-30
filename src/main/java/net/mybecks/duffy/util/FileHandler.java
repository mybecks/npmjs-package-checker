package net.mybecks.duffy.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.mybecks.duffy.pojo.Settings;

public class FileHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(FileHandler.class);
	public static final String JSON = "json";

	public static String getExtension(File f) {
		String ext = null;
		String s = f.getName();
		int i = s.lastIndexOf('.');

		if (i > 0 && i < s.length() - 1) {
			ext = s.substring(i + 1).toLowerCase();
		}
		return ext;
	}

	public static String readFile(String path, Charset encoding)
			throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
	
	public static String readFile(Path path, Charset encoding)
			throws IOException {
		byte[] encoded = Files.readAllBytes(path);
		return new String(encoded, encoding);
	}
	
	public static String readFile(String path)
			throws IOException {
		return readFile(path, StandardCharsets.UTF_8);
	}
	
	public static String readFile(Path path)
			throws IOException {
		return readFile(path, StandardCharsets.UTF_8);
	}
	
	public static void findGlobalPackages() throws IOException{
		Path startingDir = Paths.get(Settings.getInstance().getNodeInstallationPath());

        FileFinder finder = new FileFinder();
        Files.walkFileTree(startingDir, finder);
//        finder.done();
	}
	
//	http://docs.oracle.com/javase/tutorial/essential/io/examples/Find.java
}
