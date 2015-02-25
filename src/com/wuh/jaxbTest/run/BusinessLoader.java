package com.wuh.jaxbTest.run;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.ClassUtils;

import com.wuh.jaxbTest.bean.FdframeworkBusiness;


public class BusinessLoader {
	private static Log log = LogFactory.getLog(BusinessLoader.class);
	private List<String> businessXmlPackage;
	
	public void init(){
		if(businessXmlPackage!=null&&businessXmlPackage.size()>0){
			try {
				loaderPackage(businessXmlPackage);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace(); 
				log.error("请设定具体要扫描的包名称",e);
			}
		}else{
			log.error("请设定具体要扫描的包名称");
		}
	}
	
	public void loaderPackage(List<String> businessXmlPackage) throws IOException{
		PathMatchingResourcePatternResolver ppr = new PathMatchingResourcePatternResolver();
		String resourcePattern = "**/*.rediskeybusiness.xml";
		for(String pack:businessXmlPackage){
			String packageSearchPath = (new StringBuilder())
					.append("classpath*:").append(resolveBasePackage(pack))
					.append("/").append(resourcePattern).toString();
			Resource resources[] = ppr.getResources(packageSearchPath);
			for(Resource arr:resources){
				log.info("加载服务文件"+arr.toString());
				resolvingBusinessXml(arr.getInputStream());
			}
		}
		
	}
	
	protected String resolveBasePackage(String basePackage) {
		Environment env = new StandardEnvironment();
		return ClassUtils.convertClassNameToResourcePath(env
				.resolveRequiredPlaceholders(basePackage));
	}
	
	public void resolvingBusinessXml(InputStream in){
		try {
			FdframeworkBusiness fdframeworkRediskey=(FdframeworkBusiness) JAXBContext.newInstance(new Class[]{FdframeworkBusiness.class}).createUnmarshaller().unmarshal(in);
			BusinessPool.getInstance().registerBusiness(fdframeworkRediskey.getBusiness());
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	public List<String> getBusinessXmlPackage() {
		return businessXmlPackage;
	}

	public void setBusinessXmlPackage(List<String> businessXmlPackage) {
		this.businessXmlPackage = businessXmlPackage;
	}
	
	

}
