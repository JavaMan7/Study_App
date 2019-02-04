package application;

import java.io.File;



public class LoadFiles {

	public static File[] MOD;
	
	
	public void loadMod(File folder){
		
		
		MOD = folder.listFiles();
		
		for (File file : MOD) 
        {
            System.out.println(file.getName());
            
            
            
        }
	 
	
	
	}
	
	
	
	
}
