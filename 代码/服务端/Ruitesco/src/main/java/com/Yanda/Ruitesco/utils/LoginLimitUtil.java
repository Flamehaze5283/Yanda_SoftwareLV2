package com.Yanda.Ruitesco.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.Yanda.Ruitesco.javabean.LoginLimit;

public class LoginLimitUtil {
	//基础时间
	private static int basetime;
	//失败次数阈值
	private static int usernamethreshold;
	private static int ipthreshold;
	//等级 总时间=基础时间*2^等级数
	private static int usernamebaselevel;
	private static int ipbaselevel;
	//失败次数计数
	private static Map<String,Integer> usernameLoginCount;
	private static Map<String,Integer> ipLoginCount;
	//黑白名单，无视上述规则。黑名单直接禁止登录，白名单无视登录限制。
	//黑名单优先级大于白名单，username优先级大于ip。
	//从配置文件中读取，用逗号分隔
	private static List<String> ipWhiteList;
	private static List<String> ipBlackList;
	private static List<String> usernameWhiteList;
	private static List<String> usernameBlackList;
	static {
		basetime=30;
		usernamethreshold=5;
		ipthreshold=30;
		usernamebaselevel=1;
		ipbaselevel=6;
		usernameLoginCount=new HashMap<String,Integer>();
		ipLoginCount=new HashMap<String,Integer>();
		Properties properties=new Properties();
		InputStream inputStream = JdbcUtil.class.getClassLoader().getResourceAsStream("login.properties");
		try {
			properties.load(new InputStreamReader(inputStream,"UTF-8"));
			String tmp=properties.getProperty("ip_white_list");
			if(tmp!=null&&!tmp.equals(""))
				ipWhiteList=Arrays.asList(tmp.split(","));
			else
				ipWhiteList=new ArrayList<String>();
			tmp=properties.getProperty("ip_black_list");
			if(tmp!=null&&!tmp.equals(""))
				ipBlackList=Arrays.asList(tmp.split(","));
			else
				ipBlackList=new ArrayList<String>();
			tmp=properties.getProperty("username_white_list");
			if(tmp!=null&&!tmp.equals(""))
				usernameWhiteList=Arrays.asList(tmp.split(","));
			else
				usernameWhiteList=new ArrayList<String>();
			tmp=properties.getProperty("username_black_list");
			if(tmp!=null&&!tmp.equals(""))
				usernameBlackList=Arrays.asList(tmp.split(","));
			else
				usernameBlackList=new ArrayList<String>();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	

	public static long QuaryLimited(String username,String ip)
	{
		if(usernameBlackList.indexOf(username)!=-1)
			return Long.MAX_VALUE;
		if(usernameWhiteList.indexOf(username)!=-1)
			return 0;
		if(ipBlackList.indexOf(ip)!=-1)
			return Long.MAX_VALUE;
		if(ipWhiteList.indexOf(ip)!=-1)
			return 0;
		List<LoginLimit> usernameLimitList=null;
		List<LoginLimit> ipLimitList=null;
		LoginLimit usernameLimit=null;
		LoginLimit ipLimit=null;
		usernameLimitList = JdbcUtil.executeQuery("select * from login_limit where username=?", LoginLimit.class, username);		
		ipLimitList = JdbcUtil.executeQuery("select * from login_limit where ip=?", LoginLimit.class, ip);
		long usernameremainTime=-1;
		long ipremainTime=-1;
		if(usernameLimitList!=null)
		{
			usernameLimit=usernameLimitList.get(0);
			Timestamp limitStartTime=usernameLimit.getCreate_time();
			long limitTime=basetime * (long)Math.pow(2, usernameLimit.getLevel());
			Date now=new Date();
			long interval = (now.getTime() - limitStartTime.getTime())/1000;
			if(interval > limitTime)
				usernameremainTime=0;
			else
				usernameremainTime = limitTime-interval;
		}
		if(ipLimitList!=null)
		{
			ipLimit=ipLimitList.get(0);
			Timestamp limitStartTime=ipLimit.getCreate_time();
			long limitTime=basetime * (long)Math.pow(2, ipLimit.getLevel());
			Date now=new Date();
			long interval = (now.getTime() - limitStartTime.getTime())/1000;
			if(interval > limitTime)
				ipremainTime=0;
			else
				ipremainTime = limitTime-interval;
		}
		if(usernameLimitList==null&&ipLimitList==null)
			return 0;
		else
			return usernameremainTime>ipremainTime?usernameremainTime:ipremainTime;

	}
	
	public static void AddCount(String username,String ip)
	{
		if(usernameLoginCount.containsKey(username))
		{
			int tmp=usernameLoginCount.get(username);
			if(++tmp==usernamethreshold)
			{
				usernameLoginCount.remove(username);
				int level=JdbcUtil.executeQueryInt("select level from login_limit where username=?",username);
				Date date=new Date();
				Timestamp now=new Timestamp(date.getTime());
				if(level!=Integer.MIN_VALUE)
					JdbcUtil.executeUpdate("update login_limit set create_time=?,level=? where username=?",now,level+1,username);
				else
				{
					JdbcUtil.executeUpdate("insert into login_limit(username,status,create_time,level) values(?,?,?,?)",username,1,now,usernamebaselevel);
				}
			}
			else
				usernameLoginCount.put(username, tmp);
			
		}else
		{
			usernameLoginCount.put(username, 1);
		}
		if(ipLoginCount.containsKey(ip))
		{
			int tmp=ipLoginCount.get(ip);
			if(++tmp==ipthreshold)
			{
				ipLoginCount.remove(ip);
				int level=JdbcUtil.executeQueryInt("select level from login_limit where ip=?",ip);
				Date date=new Date();
				Timestamp now=new Timestamp(date.getTime());
				if(level!=Integer.MIN_VALUE)
					JdbcUtil.executeUpdate("update login_limit set create_time=?,level=? where ip=?",now,level+1,ip);
				else
					JdbcUtil.executeUpdate("insert into login_limit(ip,status,create_time,level) values(?,?,?,?)",ip,2,now,ipbaselevel);
			}
			else
				ipLoginCount.put(ip, tmp);
		}else
		{
			ipLoginCount.put(ip, 1);
		}
	}
	
	public static void RemoveCount(String username,String ip)
	{
		usernameLoginCount.remove(username);
		ipLoginCount.remove(ip);
		JdbcUtil.executeUpdate("delete from login_limit where username=?", username);
		JdbcUtil.executeUpdate("delete from login_limit where ip=?", ip);
	}


	
	public static int getBasetime() {
		return basetime;
	}

	public static void setBasetime(int basetime) {
		LoginLimitUtil.basetime = basetime;
	}

	public static int getIpbaselevel() {
		return ipbaselevel;
	}

	public static void setIpbaselevel(int ipbaselevel) {
		LoginLimitUtil.ipbaselevel = ipbaselevel;
	}
	
	public static int getUsernamebaselevel() {
		return usernamebaselevel;
	}

	public static void setUsernamebaselevel(int usernamebaselevel) {
		LoginLimitUtil.usernamebaselevel = usernamebaselevel;
	}


	
}
