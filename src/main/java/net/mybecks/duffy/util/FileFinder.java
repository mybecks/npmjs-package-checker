package net.mybecks.duffy.util;

import static java.nio.file.FileVisitResult.CONTINUE;
import static java.nio.file.FileVisitResult.SKIP_SIBLINGS;
import static java.nio.file.FileVisitResult.SKIP_SUBTREE;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileFinder extends SimpleFileVisitor<Path> {
	private static final Logger LOG = LoggerFactory.getLogger(FileFinder.class);

	private boolean visited = false;

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
		if (file.getFileName().toString().matches("package.json")) {
			LOG.info("Current package.json file: " + file);
			try {
				JsonParser.parseGlobalPackageFile(FileHandler.readFile(file));
			} catch (IOException e) {
				e.printStackTrace();
			}
			return SKIP_SIBLINGS;
		}
		return CONTINUE;
	}

	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
		if (dir.getFileName().toString().equals("node_modules")) {
			if (visited) {
				return SKIP_SUBTREE;
			}
			visited = true;
			return CONTINUE;
		} else if (dir.getFileName().toString().equals("bin")
				|| dir.getFileName().toString().equals("include")
				|| dir.getFileName().toString().equals("share")
				|| dir.getFileName().toString().equals("dtrace")
				|| dir.getFileName().toString().equals("test")
				|| dir.getFileName().toString().equals("doc")
				|| dir.getFileName().toString().equals("api")
				|| dir.getFileName().toString().equals("build")
				|| dir.getFileName().toString().equals("docs")) {
			return SKIP_SUBTREE;
		}
		return CONTINUE;
	}
}
