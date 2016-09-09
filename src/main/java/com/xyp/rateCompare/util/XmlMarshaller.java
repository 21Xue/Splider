package com.xyp.rateCompare.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.xml.transform.StringResult;
import org.springframework.xml.transform.StringSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.util.concurrent.ConcurrentHashMap;

/**
 * User: Tony
 * Date: 12-6-30
 */
public class XmlMarshaller {

    private static Log log = LogFactory.getLog(XmlMarshaller.class);
    private final static ConcurrentHashMap<String, JAXBContext> contextMap = new ConcurrentHashMap<String, JAXBContext>();


    public static String outputXML(Object element) {
        if (element == null) {
            return null;
        }
        Jaxb2Marshaller jm = new Jaxb2Marshaller();
        jm.setContextPath(element.getClass().getPackage().getName());
        StringResult sr = new StringResult();
        jm.marshal(element, sr);
        String result = sr.toString();
        return result;
    }

    public static <T> T unmarshall(Class<T> cls, String str) {
        if (str == null) {
            return null;
        }
        Jaxb2Marshaller jm = new Jaxb2Marshaller();
        jm.setContextPath(cls.getClass().getPackage().getName());
        StringResult sr = new StringResult();

        Object unmarshal = jm.unmarshal(new StringSource(str));
        if (unmarshal != null) {
            return (T) unmarshal;
        }
        return null;
    }

    public static <T> T unmarshall(String source, Class<T> cls) {
        if (StringUtils.isBlank(source)) {
            return null;
        }
        JAXBContext context = getContext(cls);
        try {
            Unmarshaller m = context.createUnmarshaller();
            Object unmarshal1 = m.unmarshal(new StringSource(source));
            if (unmarshal1 instanceof JAXBElement) {
                JAXBElement unmarshal = (JAXBElement) unmarshal1;
                T obj = (T) unmarshal.getValue();
                return obj;
            } else {
                return cls.cast(unmarshal1);
            }
        } catch (JAXBException e) {
            if (log.isWarnEnabled())
                log.warn("Exception while marsaling", e);
            return null;
        }
    }

    private static JAXBContext getContext(Class<? extends Object> theClass) {
        String key = theClass.getPackage().getName();
        if (!contextMap.containsKey(key)) {
            try {
                contextMap.putIfAbsent(key, JAXBContext.newInstance(theClass.getPackage().getName()));
            } catch (JAXBException e) {
                if (log.isWarnEnabled())
                    log.warn("Exception while obtaining the context", e);
            }
        }
        return contextMap.get(key);
    }
//    /**
//     * 格式化XML字符串
//     * @param str
//     * @return
//     */
//    public static String formatXml(String str){
//        try{
//            Document document = null;
//            document = DocumentHelper.parseText(str);
//            // 格式化输出格式
//            OutputFormat format = OutputFormat.createPrettyPrint();
//            format.setEncoding("UTF-8");
//            StringWriter writer = new StringWriter();
//            // 格式化输出流
//            XMLWriter xmlWriter = new XMLWriter(writer, format);
//            // 将document写入到输出流
//            xmlWriter.write(document);
//            xmlWriter.close();
//            return writer.toString();
//        }catch (Exception e){
//            log.error(e);
//            return str;
//        }
//    }

}
