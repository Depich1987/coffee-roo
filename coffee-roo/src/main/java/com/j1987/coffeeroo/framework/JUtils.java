package com.j1987.coffeeroo.framework;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.SQLExec;

import com.j1987.coffeeroo.domain.JFactory;
import com.j1987.coffeeroo.domain.JFirm;

public class JUtils {

	public static final String DB_PERSISTENCE_UNIT = "persistenceUnit";
	public static final String SECURITY_UNAUTHENTICATED_USER = "anonymousUser";

	public static final SimpleDateFormat DATE_FORMAT_URL = new SimpleDateFormat(
			"yyyy-MM-dd");
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
			"dd/MM/yyyy");
	public static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat(
			"dd/MM/yyyy HH:mm:ss");

	public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat(
			"HH:mm");

	public static final String LANGUAGE_FR = "french";
	public static final String LANGUAGE_EN = "english";
	
	public static final String COCOA_PRODUCT = "Cacao";
	public static final String COFFEE_PRODUCT = "Cafe";
	
	public static final String STATUS_PUSH = "Refuse";
	public static final String STATUS_NO = "Non";
	public static final String STATUS_STANDBY = "En Attente";
	public static final String STATUS_CONFIRMED = "Accepte";
	public static final String STATUS_YES = "Oui";
	
	public static final String CLASSIFICATION_G1 = "G1";
	public static final String CLASSIFICATION_G2 = "G2";
	public static final String CLASSIFICATION_SG = "SG";

	public static final String DB_ROLE_ADMIN = "ROLE_ADMIN";
	public static final String DB_ROLE_SUPERVISOR = "ROLE_SUPERVISOR";
	public static final String DB_ROLE_FACTORY_MANAGER = "ROLE_FACTORY_MANAGER";
	public static final String DB_ROLE_FACTORY_AGENT = "ROLE_FACTORY_AGENT";

	public static final String DB_UI_ROLE_ADMIN = "ADMINISTRATEUR";
	public static final String DB_UI_ROLE_SUPERVISOR = "SUPERVISEUR";
	public static final String DB_UI_ROLE_FACTORY_MANAGER = "CHEF USINE";
	public static final String DB_UI_ROLE_FACTORY_AGENT = "AGENT";

	public static final String HTTP_SESSION_FACTORY_CODE = "userFactoryCode";
	public static final String HTTP_SESSION_FACTORY_NAME = "userFactoryName";

	public static final String HTTP_SESSION_COMPANY_CODE = "userCompanyCode";
	public static final String HTTP_SESSION_COMPANY_NAME = "userCompanyName";
	public static final String HTTP_SESSION_USER_ROLE = "userRole";

	public JUtils() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Allows to run an SQL script from within our system. This is basically
	 * done via an Ant built-in Task
	 * */
	public static void executeSql(String sqlFilePath, String driver,
			String url, String userName, String password) {
		final class SqlExecuter extends SQLExec {
			public SqlExecuter() {
				Project project = new Project();
				project.init();
				setProject(project);
				setTaskType("sql");
				setTaskName("sql");
			}
		}

		SqlExecuter executer = new SqlExecuter();
		executer.setSrc(new File(sqlFilePath));
		executer.setDriver(driver);
		executer.setUrl(url);
		executer.setUserid(userName);
		executer.setPassword(password);
		executer.execute();
	}

	public static Map<String, String> uiRoleNamesMap() {

		Map<String, String> roleNamesMap = new HashMap<String, String>();

		roleNamesMap.put(JUtils.DB_ROLE_ADMIN, JUtils.DB_UI_ROLE_ADMIN);
		roleNamesMap.put(JUtils.DB_ROLE_SUPERVISOR,
				JUtils.DB_UI_ROLE_SUPERVISOR);
		roleNamesMap.put(JUtils.DB_ROLE_FACTORY_MANAGER,
				JUtils.DB_UI_ROLE_FACTORY_MANAGER);
		roleNamesMap.put(JUtils.DB_ROLE_FACTORY_AGENT,
				JUtils.DB_UI_ROLE_FACTORY_AGENT);

		return roleNamesMap;
	}

	public static Map<String, String> dbRoleNamesMap() {

		Map<String, String> roleNamesMap = new HashMap<String, String>();

		roleNamesMap.put(JUtils.DB_UI_ROLE_ADMIN, JUtils.DB_ROLE_ADMIN);
		roleNamesMap.put(JUtils.DB_UI_ROLE_SUPERVISOR,
				JUtils.DB_ROLE_SUPERVISOR);
		roleNamesMap.put(JUtils.DB_UI_ROLE_FACTORY_MANAGER,
				JUtils.DB_ROLE_FACTORY_MANAGER);
		roleNamesMap.put(JUtils.DB_UI_ROLE_FACTORY_AGENT,
				JUtils.DB_ROLE_FACTORY_AGENT);

		return roleNamesMap;
	}

	public static Map<Long, String> populateUiStatusMap() {

		Map<Long, String> statusMap = new HashMap<Long, String>();

		statusMap.put(Long.valueOf("0"), JUtils.STATUS_PUSH);
		statusMap.put(Long.valueOf("1"), JUtils.STATUS_STANDBY);
		statusMap.put(Long.valueOf("2"), JUtils.STATUS_CONFIRMED);

		return statusMap;
	}

	/**
	 * Retrieves a a comma separated String objects from a Collection. Useful
	 * for the view
	 * */
	public static <T> String retrieveObjectPropertiesAsString(
			Collection<T> collection) {
		StringBuffer buffer = new StringBuffer();

		for (T obj : collection) {
			String prop = null;
			// Access the property (ex: the name)
			if (obj instanceof JFirm) {
				prop = ((JFirm) obj).getName();

			} else if (obj instanceof JFactory) {
				prop = ((JFactory) obj).getName();

			}

			if (buffer.indexOf(prop) == -1) {
				buffer.append(prop).append(", ");
			}
		}

		String names = buffer.toString();
		if (!StringUtils.isEmpty(names)) {
			int lastIndex = names.lastIndexOf(", ");
			names = names.substring(0, lastIndex);
		}
		return names;
	}
	
    public static String convertNumberToWords(Long n,String language){
    	
    	if (language.equals(LANGUAGE_EN)){
    		return EnglishNumberToWords.convert(n);
    	}
    	
    	return FrenchNumberToWords.convert(n);
    }

}
