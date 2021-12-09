package fr.dzeta.bgame.time;

public class TimeUtils {
	public static String parseSecond(long second) {
		String temp = "";
		if(second >= 60 * 60) {
			temp += ((long)second / (60 * 60)) + " h ";
			second %= 60 * 60;
		}
		if(second >= 60) {
			temp += ((long)second / 60) + " min ";
			second %= 60;
		}
		else {
			temp += "0 min ";
		}
		return temp + second +" sec";
	}
}
