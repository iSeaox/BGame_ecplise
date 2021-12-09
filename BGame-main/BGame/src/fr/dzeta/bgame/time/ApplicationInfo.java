package fr.dzeta.bgame.time;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ApplicationInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long count;
	private int openning;
	
	public ApplicationInfo() {
		this.count = 0;
		this.openning = 0;
	}
	
	public ApplicationInfo load() {
		ApplicationInfo info = null;
		if(new File("./temp/time/elapsed.tim").exists()) {
			try (FileInputStream fis = new FileInputStream("./temp/time/elapsed.tim"); ObjectInputStream ois = new ObjectInputStream(fis)){
				info =  (ApplicationInfo)ois.readObject();
				
				ois.close();
				fis.close();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		else {
			info = new ApplicationInfo();
		}
		return info;
	}
	
	public void flush() {
		System.out.println("[end]<info> flush");
		File file = new File("./temp/time/elapsed.tim");
		if(!file.exists()) {
			try {
				file.getParentFile().mkdirs();
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try (FileOutputStream fos = new FileOutputStream("./temp/time/elapsed.tim"); 
				ObjectOutputStream oos = new ObjectOutputStream(fos)){
			oos.writeObject(this);
			
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public long getCount() {
		return count;
	}

	public int getOpenning() {
		return openning;
	}
	
	public void increaseOpenning() {
		this.openning += 1;
	}
	
	public void increaseCount() {
		this.count += 1;
	}
}
