package coral.base.app;

import java.net.URISyntaxException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;

import wfc.service.config.Config;

public class AppContext {

	/** 用户信息，对象为SecurityUser类型 */
	public static final String SESSION_USER = "session_user";

	/** 用户角色 */
	public static final String SESSION_ROLE = "session_role";

	/** 用户信息，对象为{@link WcsUserExtInfo}类型，可能为null */
	public static final String SESSION_USER_EXT = "session_user_ext";

	/** 用户所属组织，对象为OrganNode数组 */
	public static final String SESSION_ORG = "session_org";

	/** 用户的地址，一般为IP地址 */
	public static final String SESSION_REMOTE_ADDR = "session_remote_addr";

	/** 用户当前ExtJs主题 */
	public static final String SESSION_EXT_JS_THEME = "session_ext_js_theme";

	/** 用户当前Easyui主题 */
	public static final String SESSION_EASYUI_THEME = "session_easyui_theme";

	/** 用户当前框架 */
	public static final String SESSION_FRAME_THEME = "session_frame_theme";

	/** 用户能访问的菜单，对象为菜单编号集合 */
	public static final String SESSION_MENU_CODE = "session_menu_code";

	/** 是否需要进行验证码的验证 */
	public static final String SESSION_NEED_CHECK = "session_need_check";

	/** 验证码，对象为字符串类型 */
	public static final String SESSION_CHECK_STRING = "session_check_string";

	/** 外部应用权限集合 */
	public static final String SESSION_ACL_EXTERNAL_USER = "session_acl_external_user";

	/** 部门管理窗口code:dept_code_menu;办事管理窗口code:windowManager */
	public static final String ManagerId = "managerId";

	/** 组别按部门过滤 */
	public static final String GroupFilter = "groupFilter";

	public static final String CORAL_ROOT_MENU_CODE = "coral";

	public static final String ORGAN = "organ";

	public static final long ADMIN_USER = 1;

	public static final long ADMIN_ROLE = 1;

	public static final String CORAL_ADMIN = "coral";

	public static final String MENU_RESOURCE_TYPE_CODE = "Menu";

	public static final String MENU_OPERATION_CODE = "Read1";

	public static ApplicationContext currentContext;

	public static String appPath;

	public static String classesPath;

	public static String webRootPath;

	public static String databaseName;

	public static String webserviceProxyUrlPrefix;

	public static long adminUserId;

	public static boolean isContextReady() {
		return currentContext != null;
	}

	public static Object getBean(String id) {
		return currentContext.getBean(id);
	}

	public static Map<String, ?> getBean(Class<?> clazz) {
		return currentContext.getBeansOfType(clazz);
	}

	public static Object getSingleBean(Class<?> clazz) {
		Map<String, ?> map = getBean(clazz);
		return map.values().iterator().next();
	}

	private static void init() {
		try {
			String classespath = StringUtils.trimToEmpty(Config
					.get("coral.classespath"));
			String defaultClassesPath = AppContext.class.getResource("/")
					.toURI().getPath();
			AppContext.classesPath = classespath.isEmpty() ? defaultClassesPath
					: classespath;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	static {
		init();
	}

}
