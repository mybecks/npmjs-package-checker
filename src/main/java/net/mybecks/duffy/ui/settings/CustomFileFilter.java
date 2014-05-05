package net.mybecks.duffy.ui.settings;

import java.io.File;

import javax.swing.filechooser.FileFilter;

import net.mybecks.duffy.util.FileHandler;

public class CustomFileFilter extends FileFilter {

	@Override
	public boolean accept(File f) {
		if (f.isDirectory()) {
	        return true;
	    }
		
		String extension = FileHandler.getExtension(f);
        if (extension != null) {
            if (extension.equals(FileHandler.JSON)) {
                    return true;
            } else {
                return false;
            }
        }

		return false;
	}

	@Override
	public String getDescription() {
		return ".json Files";
	}

}
