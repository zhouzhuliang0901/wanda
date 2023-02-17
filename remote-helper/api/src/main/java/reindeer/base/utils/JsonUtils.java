package reindeer.base.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import coral.widget.data.DataSet;
import coral.widget.utils.EasyUIJsonConverter;
import coral.widget.utils.MetaDataJsonConverter4JPA;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.DefaultValueProcessorMatcher;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.PropertyFilter;
import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.log.Log;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class JsonUtils {

    private static ObjectMapper om = new ObjectMapper();
    private static JsonConfig jsonConfig = null;

    static {

        jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new TimeJsonValueProcessor());
        jsonConfig.registerJsonValueProcessor(Timestamp.class, new TimeJsonValueProcessor());
        jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
            public boolean apply(Object source, String name, Object value) {
                return value == null;
            }
        });
        jsonConfig.setDefaultValueProcessorMatcher(new DefaultValueProcessorMatcher() {
            public Object getMatch(Class aClass, Set set) {
                return null;
            }
        });

        // 对象的所有字段全部列入，还是其他的选项，可以忽略null等
        om.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        // 设置Date类型的序列化及反序列化格式
        om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        om.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        // 忽略空Bean转json的错误
        om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 忽略未知属性，防止json字符串中存在，java对象中不存在对应属性的情况出现错误
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 注册一个时间序列化及反序列化的处理模块，用于解决jdk8中localDateTime等的序列化问题
//        om.registerModule(new JavaTimeModule());
    }

    public static ObjectMapper getObjectMapper() {
        return om;
    }

    public static JSONObject toJSONObject(Object obj) {
        return JSONObject.fromObject(obj, jsonConfig);
    }

    public static JSONArray toJSONArray(Object obj) {
        return JSONArray.fromObject(obj, jsonConfig);
    }

    public static JsonNode readTree(JSONObject jso) {
        try {
            return om.readTree(JSONObject.fromObject(jso, jsonConfig).toString());
        } catch (IOException e) {
            Log.error(e);
        }
        return null;
    }

    public static JsonNode readTree(JSONArray jsa) {
        try {
            return om.readTree(JSONArray.fromObject(jsa, jsonConfig).toString());
        } catch (IOException e) {
            Log.error(e);
        }
        return null;
    }

    public static JsonNode toJson(PaginationArrayList<?> list, Class<?> clazz) {
        try {
            return om.readTree(EasyUIJsonConverter.convertDataSetToJson(
                    DataSet.convert(list, clazz)).toString());
        } catch (IOException e) {
            Log.error(e);
        }
        return null;
    }

    public static JsonNode toJson(List<?> list, Class<?> clazz) {
        try {
            return om.readTree(EasyUIJsonConverter.convertDataSetToJson(
                    DataSet.convert(list, clazz)).toString());
        } catch (IOException e) {
            Log.error(e);
        }
        return null;
    }

    public static JsonNode toJson(Object obj) {
        try {
            return om.readTree(MetaDataJsonConverter4JPA.convertToJson(obj).toString());
        } catch (IOException e) {
            Log.error(e);
        }
        return null;
    }

    private static class TimeJsonValueProcessor implements JsonValueProcessor {
        private SimpleDateFormat sdf;

        private TimeJsonValueProcessor() {
            this.sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }

        public Object processArrayValue(Object arg0, JsonConfig arg1) {
            return this.process(arg0);
        }

        public Object processObjectValue(String arg0, Object arg1, JsonConfig arg2) {
            return this.process(arg1);
        }

        private Object process(Object value) {
            return !(value instanceof Date) && !(value instanceof Timestamp) ? value : this.sdf.format(value);
        }
    }

}
