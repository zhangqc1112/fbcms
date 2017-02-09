package com.fbcms.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.codehaus.jackson.map.ObjectMapper;
/**
 *
 */
public class ParamUtil {

	public static Object copyProperties(Object rtuObject, Object object) {
		Class classType = object.getClass();
		Class rtuClassType = rtuObject.getClass();

		Field fields[] = classType.getDeclaredFields();

		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			String fieldName = field.getName();
			String firstLetter = fieldName.substring(0, 1).toUpperCase();

			String getMethodName = "get" + firstLetter + fieldName.substring(1);
			String setMethodName = "set" + firstLetter + fieldName.substring(1);

			try {

				Method getMethod = classType.getMethod(getMethodName, new Class[] {});
				Method setMethod = rtuClassType.getMethod(setMethodName, new Class[] { field.getType() });
				Object value = getMethod.invoke(object, new Object[] {});
				if (null != value) {
					setMethod.invoke(rtuObject, new Object[] { value });
				}

			} catch (Exception e) {

			}
		}

		return rtuObject;
	}
	
	public static Object copyProperties_V2(Object rtuObject, Object[] objects) {
		for(Object object:objects){
			Class classType = object.getClass();
			Class rtuClassType = rtuObject.getClass();

			Field fields[] = classType.getDeclaredFields();

			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				String fieldName = field.getName();
				String firstLetter = fieldName.substring(0, 1).toUpperCase();

				String getMethodName = "get" + firstLetter + fieldName.substring(1);
				String setMethodName = "set" + firstLetter + fieldName.substring(1);

				try {

					Method getMethod = classType.getMethod(getMethodName, new Class[] {});
					Method getMethodRet = rtuClassType.getMethod(getMethodName, new Class[] {});
					
					Method setMethod = rtuClassType.getMethod(setMethodName, new Class[] { field.getType() });
					Object value = getMethod.invoke(object, new Object[] {});
					if (null != value) {
						setMethod.invoke(rtuObject, new Object[] { value });
					}

				} catch (Exception e) {

				}
			}
		}
		

		return rtuObject;
	}


	public static boolean checkEmail(String email) {
		boolean flag = false;
		try {

			if (StringUtil.isBlank(email)) {
				return true;
			}

			String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	
	public static String toJsonString(Object obj){
	    ObjectMapper mapper = new ObjectMapper();  
        String jsonlist = "";
        try {
            jsonlist = mapper.writeValueAsString(obj);
        }catch (Exception e) {
           
        }  
        return jsonlist;
	}

}
