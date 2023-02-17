package reindeer.base.utils;

import wfc.service.util.CloneHelper;

public class Transformer4RequestWrapper extends WfcTransformer4Base {

	public Transformer4RequestWrapper(RequestWrapper requestWrapper) {
		this.requestWrapper = requestWrapper;
	}

	@Override
	public String getParameter(String name) {
		return requestWrapper.getParameter(name);
	}

	@Override
	public String[] getParameterValues(String name) {
		return requestWrapper.getParameterValues(name);
	}

	/**
	 * @see wfc.facility.tool.autocode.Transformer4Base#toBean(java.lang.Object, java.lang.Class)
	 */
	@Override
	public Object toBean(Object bean, Class<?> clazz) {
		return super.toBean(CloneHelper.deepClone(bean), clazz);
	}
	
	private RequestWrapper requestWrapper;

}
