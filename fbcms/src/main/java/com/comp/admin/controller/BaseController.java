package com.comp.admin.controller;

import com.comp.admin.utils.BaseException;
import com.fbcms.util.DataResult;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
public class BaseController {

	public Logger log = Logger.getLogger(this.getClass());

	@ResponseBody
	@ExceptionHandler(BaseException.class)
	public DataResult baseExceptionHandler(BaseException ex, HttpServletResponse response, HttpServletRequest request) throws IOException {
		
		log.error(ex.getMessage(), ex);
		DataResult res = new DataResult();
        res.setStatus(ex.getReturnCode());
        res.setMessage(ex.getReturnMessage());
        res.setSuccess(false);
        log.info(toJsonString(res));
        return res;
	}

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public DataResult exceptionHandler(Exception ex, HttpServletResponse response, HttpServletRequest request) throws IOException {

        log.error(ex.getMessage(), ex);
        DataResult res = new DataResult();
        res.setStatus(500);
        res.setMessage("服务器异常，码农正在努力修复中......");
        res.setSuccess(false);
        log.info(toJsonString(res));
        return res;
    }
	


	public String toJsonString(Object obj){
        ObjectMapper mapper = new ObjectMapper();
        String jsonlist = "";
        try {
            jsonlist = mapper.writeValueAsString(obj);
        }catch (Exception e) {
           
        }  
        return jsonlist;
    }
	
}
