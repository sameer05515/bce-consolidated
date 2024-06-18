package com.p.sevice.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.p.service.exception.RestServiceException;
import com.p.service.pojo.TopicGroupRelation;
import com.p.service.vo.GroupVO;
import com.p.service.vo.TopicGroupRelVO;
import com.p.service.vo.TopicVO;

public class TopicUtil {

	/** The resource. */
	protected static ResourceBundle resource;

	/** The locale. */
	static Locale locale = Locale.ENGLISH;
	static {
		resource = ResourceBundle.getBundle("com.p.sevice.util.TopicResource", locale);
	}

	/** The email name ptrn. */
	private static Pattern emailNamePtrn = Pattern
			.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

	/**
	 * Validate email address.
	 *
	 * @param userName
	 *            the user name
	 * @return true, if successful
	 */
	public static boolean validateEmailAddress(String userName) {

		Matcher matcher = emailNamePtrn.matcher(userName);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	/**
	 * Zetta err msg.
	 *
	 * @param key
	 *            the key
	 * @return the string
	 */
	public static String getErrMsg(String key) {

		String msg = resource.getString(key);
		return msg;
	}

	public static String generateSessionId() {
		String sessionId;
		Calendar c = Calendar.getInstance();
		sessionId = c.hashCode() + "" + c.getTimeInMillis();
		return sessionId;
	}

	/**
	 * Zetta err msg.
	 *
	 * @param value
	 *            the value
	 * @return the string
	 */
	public static boolean isNullOrEmpty(String value) {

		if (value == null || (value != null && value.equalsIgnoreCase(""))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if is numeric.
	 *
	 * @param s
	 *            the s
	 * @return true, if is numeric
	 */
	public static boolean isNumeric(String s) {
		return java.util.regex.Pattern.matches("\\d+", s);
	}

	@SuppressWarnings("unused")
	public static boolean isDateFormat(String dateInString) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date date = sdf.parse(dateInString);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unused")
	public static boolean isDateFormatMMDDYYYY(String dateInString) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
			Date date = sdf.parse(dateInString);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static Date covertStringDate(String dateInString) {
		try {
			// SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
			Date date = sdf.parse(dateInString);
			return date;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date covertStringToDateMMDDYYYY(String dateInString) {
		try {
			// SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
			Date date = sdf.parse(dateInString);
			return date;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// String format dd-MMM-yyyy
	public static Date covertStringToDate(String dateInString) throws RestServiceException {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			Date date = sdf.parse(dateInString);
			return date;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RestServiceException(TopicResponseErrorCodes.ZTUSER029);
		}
	}

	public static Date getTodayDate() {
		Calendar c = Calendar.getInstance();
		return c.getTime();
	}

	public static String localDateinUTC() {
		String lv_dateFormateInUTC = ""; // Will hold the final converted date
		SimpleDateFormat lv_formatter = new SimpleDateFormat();
		lv_formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		lv_dateFormateInUTC = lv_formatter.format(new Date());
		return "UTC :" + lv_dateFormateInUTC;
	}

	public static String convertDateinUTC(Date date) {

		String lv_dateFormateInUTC = ""; // Will hold the final converted date
		SimpleDateFormat lv_formatter = new SimpleDateFormat();

		lv_formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		lv_dateFormateInUTC = lv_formatter.format(date);
		return "UTC :" + lv_dateFormateInUTC;
	}

	public static Date convertToUTC(Date date) {
		// **** YOUR CODE **** BEGIN ****
		long ts = date.getTime();
		Date localTime = new Date(ts);
		String format = "yyyy/MM/dd hh:mm:ss a";
		SimpleDateFormat sdf = new SimpleDateFormat(format);

		// Convert Local Time to UTC (Works Fine)
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		c.setTime(localTime);
		System.out.println("===>" + c.getTime());

		@SuppressWarnings("deprecation")
		Date gmtTime = new Date(sdf.format(localTime));
		System.out.println("Local:" + localTime.toString() + "," + localTime.getTime() + " --> UTC time:"
				+ gmtTime.toString() + "," + gmtTime.getTime());

		// **** YOUR CODE **** END ****
		return gmtTime;
		// Convert UTC to Local Time
		/*
		 * Date fromGmt = new Date(gmtTime.getTime() +
		 * TimeZone.getDefault().getOffset(localTime.getTime()));
		 * System.out.println("UTC time:" + gmtTime.toString() + "," +
		 * gmtTime.getTime() + " --> Local:" + fromGmt.toString() + "-" +
		 * fromGmt.getTime());
		 */
	}

	public static boolean isAlphaNumeric(String employeeId) {
		String regex = "^[a-zA-Z0-9]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(employeeId);
		return matcher.matches();
	}

	// public static void main (String args[]) throws ZettaException{
	/*
	 * Date hireDate = ZettaUtil.covertStringToDateMMDDYYYY("15-07-2015"); Date
	 * prodDate = ZettaUtil.covertStringToDateMMDDYYYY("14-07-2015"); int it =
	 * prodDate.compareTo(hireDate); if(prodDate.compareTo(hireDate) < 0){
	 * System.out.println("in If"); <<<<<<< HEAD }
	 */
	/*
	 * System.out.println("test=="+isAlphaNumeric("C00179"));
	 * 
	 * //System.out.println("test"+it); Calendar c =Calendar.getInstance();
	 * //c.add(Calendar.HOUR, 4); System.out.println(c.getTime());
	 * ZettaUtil.convertToUTC(c.getTime());
	 */
	// String status=ZettaUtil.isLoginIdAssosiatedToAgentOrSME("228763");
	// System.out.println(status);
	// ZettaUtil.generateSessionId();

	// }

	// public static String isLoginIdAssosiatedToAgentOrSME(String loginId)
	// throws ZettaException{
	// ZettaUtilImpl zui=new ZettaUtilImpl();
	// Users user=zui.getUserByID(loginId);
	// if(user==null){
	// return "notindb";
	// }
	// Set<Employees> empList=user.getEmployeeses();
	// for(Employees emp:empList){
	// Set<Employeeroles> empRoleList=emp.getEmployeeroleses();
	// for(Employeeroles empRole:empRoleList){
	// if(empRole.getRoles().getRoleName().equals("Agent")||empRole.getRoles().getRoleName().equals("SME")){
	// return "adminorsme";
	// }
	//
	// }
	// }
	//
	// return "notadminorsme";
	//
	// }

	// public static String accountNameofAgentOrSME(String loginId) throws
	// ZettaException{
	// ZettaUtilImpl zui=new ZettaUtilImpl();
	// Users user=zui.getUserByID(loginId);
	// if(user==null){
	// return "notindb";
	// }
	// Set<Employees> empList=user.getEmployeeses();
	// for(Employees emp:empList){
	// Set<Employeeroles> empRoleList=emp.getEmployeeroleses();
	// for(Employeeroles empRole:empRoleList){
	// if(empRole.getRoles().getRoleName().equals("Agent")||empRole.getRoles().getRoleName().equals("SME")){
	// return emp.getAccounts().getAccountName();
	// }
	//
	// }
	// }
	//
	// return "notadminorsme";
	//
	// }

	private static List<TopicGroupRelVO> getTopicGroupRelVOList(List<TopicGroupRelation> listTopicGroupRelation) {
		List<TopicGroupRelVO> listTopicGroupRelVO = new ArrayList<TopicGroupRelVO>();
		if (listTopicGroupRelation != null && listTopicGroupRelation.size() > 0) {
			for (TopicGroupRelation tr : listTopicGroupRelation) {
				listTopicGroupRelVO.add(new TopicGroupRelVO(tr.getId(),
						new TopicVO(tr.getTopics().getId(), tr.getTopics().getTitle(), tr.getTopics().getDescription()),
						new GroupVO(tr.getGroups().getId(), tr.getGroups().getTitle(),
								tr.getGroups().getDescription())));
			}
		}

		return listTopicGroupRelVO;
	}

	public static void main(String arg[]) {
		// try {
		// String val=accountNameofAgentOrSME("810967");
		// System.out.println(val);
		//
		// } catch (ZettaException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

}
