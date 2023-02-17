/**
 * Project: GdPlatform
 * Source file: W3cNodeUtils.java
 * Create At 2011-11-22 上午10:13:31
 * Create By 龚云
 */
package coral.base.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * org.w3c.dom.Node操作工具类
 * 
 * @author 龚云
 * 
 */
public class W3cNodeUtils {

	/**
	 * 获取节点的值，包括节点下的文本{@link Node#TEXT_NODE}，CDATA
	 * {@link Node#CDATA_SECTION_NODE}，不包括属性值以及子节点的任何值，若有多个满足以上条件的值则合并这些值。<br>
	 * 默认返回 {@link StringUtils#EMPTY}既空字符串。
	 * 
	 * @param node
	 * @return
	 */
	public static String getContentString(Node node) {
		return getContentString(node, StringUtils.EMPTY);
	}

	/**
	 * 1.参见{@link #getContentStrings(Node, String)}<br>
	 * 2.若1中的列表为空则返回默认，否则返回第一个。
	 * 
	 * @param node
	 * @param defaultVal
	 *            默认值
	 * @return
	 */
	public static String getContentString(Node node, String defaultVal) {
		List<String> stringList = getContentStrings(node, defaultVal);
		if (stringList == null || stringList.size() < 1)
			return defaultVal;
		return stringList.get(0);
	}

	/**
	 * 获取节点的值，包括节点下的文本{@link Node#TEXT_NODE}，CDATA
	 * {@link Node#CDATA_SECTION_NODE}，不包括属性值以及子节点的任何值，若有多个满足以上条件的值逐个加入返回的列表中。
	 * 
	 * @param node
	 * @param defaultVal
	 *            默认值
	 * @return
	 */
	public static List<String> getContentStrings(Node node, String defaultVal) {
		List<String> stringList = new ArrayList<String>();
		if (node == null)
			stringList.add(defaultVal);
		else {
			NodeList children = node.getChildNodes();
			for (int i = 0; i < children.getLength(); i++) {
				Node child = children.item(i);
				switch (child.getNodeType()) {
					case Node.TEXT_NODE:
					case Node.CDATA_SECTION_NODE:
						stringList.add(StringUtils.trimToEmpty(child
								.getTextContent()));
						break;
				}
			}
		}
		return stringList;
	}
}
