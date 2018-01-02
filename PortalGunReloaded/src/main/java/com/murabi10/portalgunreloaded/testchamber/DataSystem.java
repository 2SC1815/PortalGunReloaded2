package com.murabi10.portalgunreloaded.testchamber;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.murabi10.portalgunreloaded.PortalGun;

public class DataSystem {

	public static boolean Save(TestChamber chamber, TestChamberData data, String dirName) {

		File dir = new File(PortalGun.TEST_CHAMBER_FILE_PATH + dirName);

		if (!dir.exists()) {
			dir.mkdirs();
		}

		try {
			ObjectOutputStream objOutStream = new ObjectOutputStream(new FileOutputStream(ChamberPath(dirName)));

			objOutStream.writeObject(chamber);
			objOutStream.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		try {
			ObjectOutputStream objOutStream = new ObjectOutputStream(new FileOutputStream(DataPath(dirName)));

			objOutStream.writeObject(data);
			objOutStream.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

	public static TestChamber getChamberObject(String dirName) {
		if (!isCompleteExist(dirName)) {
			return null;
		}
		try {
			ObjectInputStream objInStream = new ObjectInputStream(new FileInputStream(new File(ChamberPath(dirName))));
			TestChamber chamber = (TestChamber) objInStream.readObject();
			objInStream.close();

			return chamber;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static TestChamberData getChamberData(String dirName) {
		if (!isCompleteExist(dirName)) {
			return null;
		}
		try {
			ObjectInputStream objInStream = new ObjectInputStream(new FileInputStream(new File(DataPath(dirName))));
			TestChamberData chamber = (TestChamberData) objInStream.readObject();
			objInStream.close();
			chamber.setFileName(dirName);
			return chamber;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean isCompleteExist(String dirName) {
		File data = new File(DataPath(dirName));
		File chamber = new File(ChamberPath(dirName));
		return data.exists() && chamber.exists() && data.isFile() && chamber.isFile()
				&& data.getName().endsWith(PortalGun.TEST_CHAMBER_DATA_EXTENSION)
				&& chamber.getName().endsWith(PortalGun.TEST_CHAMBER_EXTENSION);
	}

	public static ArrayList<String> getChambers() {
		ArrayList<String> rtnfile = new ArrayList<String>();
		File[] files = new File(PortalGun.TEST_CHAMBER_FILE_PATH).listFiles();
		for (int i = 0; i < files.length; i++) {
			File f = files[i];
			String fName = f.getName();
			if (f.isDirectory() && isCompleteExist(fName)) {
				rtnfile.add(fName);
			}
		}
		return rtnfile;
	}

	public static void delete(String dirName) {

		new File(DataPath(dirName)).delete();
		new File(ChamberPath(dirName)).delete();
		new File(PortalGun.TEST_CHAMBER_FILE_PATH + dirName).delete();
	}

	public static void rename(String dirName, String out) {

		new File(DataPath(dirName)).renameTo(new File(
				PortalGun.TEST_CHAMBER_FILE_PATH + dirName + File.separator + out + PortalGun.TEST_CHAMBER_DATA_EXTENSION));
		new File(ChamberPath(dirName)).renameTo(new File(PortalGun.TEST_CHAMBER_FILE_PATH + dirName + File.separator
				+ out + PortalGun.TEST_CHAMBER_EXTENSION));
		new File(PortalGun.TEST_CHAMBER_FILE_PATH + dirName).renameTo(new File(PortalGun.TEST_CHAMBER_FILE_PATH + out));
	}

	private static String ChamberPath(String bindname) {
		return PortalGun.TEST_CHAMBER_FILE_PATH + bindname + File.separator + bindname
				+ PortalGun.TEST_CHAMBER_EXTENSION;
	}

	private static String DataPath(String bindname) {
		return PortalGun.TEST_CHAMBER_FILE_PATH + bindname + File.separator + bindname
				+ PortalGun.TEST_CHAMBER_DATA_EXTENSION;
	}

	public static boolean Save(TestChamberData td, String dirName) {

		File dir = new File(PortalGun.TEST_CHAMBER_FILE_PATH + dirName);

		if (!dir.exists()) {
			dir.mkdirs();
		}

		try {
			ObjectOutputStream objOutStream = new ObjectOutputStream(new FileOutputStream(DataPath(dirName)));

			objOutStream.writeObject(td);
			objOutStream.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

}
