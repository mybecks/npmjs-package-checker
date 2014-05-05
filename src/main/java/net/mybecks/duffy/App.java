package net.mybecks.duffy;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import net.mybecks.duffy.pojo.Settings;
import net.mybecks.duffy.ui.UiBuilder;

/**
 * Hello world!
 *
 */
public class App 
{	
	
    public static void main( String[] args )
    {
    	
    	System.setProperty("http.proxyHost", "proxy");
		System.setProperty("http.proxyPort", "8080");
		System.setProperty("https.proxyHost", "proxy");
		System.setProperty("https.proxyPort", "8080");
		
		//Only for development
    	Settings.getInstance().setRepositoryUrl("https://www.npmjs.org/package/");
    	Settings.getInstance().setPackageJsonPath("/Users/d043622/Git/search_html/web/package.json");
    	
    	SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				try {
                    UIManager.setLookAndFeel(
                                  "javax.swing.plaf.metal.MetalLookAndFeel");
                                //  "com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                                //UIManager.getCrossPlatformLookAndFeelClassName());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
				UiBuilder.createAndShowUI();
			}
		});        
    }
}
