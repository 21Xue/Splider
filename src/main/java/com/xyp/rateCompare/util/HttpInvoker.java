package com.xyp.rateCompare.util;


import org.apache.http.*;
import org.apache.http.client.HttpRequestRetryHandler;

import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.routing.HttpRoute;

import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;

/**
 * Created by DT241 on 2016/7/1.
 */
public class HttpInvoker {

//    private static PoolingHttpClientConnectionManager cm;

    private static HttpRequestRetryHandler httpRequestRetryHandler;

//    static {
//        ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
//        LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory.getSocketFactory();
//        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
//                .register("http", plainsf)
//                .register("https", sslsf)
//                .build();
//        cm = new PoolingHttpClientConnectionManager(registry);
//        // 将最大连接数增加到200
//        cm.setMaxTotal(200);
//        // 将每个路由基础的连接增加到20
//        cm.setDefaultMaxPerRoute(20);
//        HttpHost searchFeed = new HttpHost("http://us.searchfeed.derbysoft.com/dplatform-searchfeed", 80);
//        cm.setMaxPerRoute(new HttpRoute(searchFeed), 50);
//
//        //请求重试处理
//        httpRequestRetryHandler = new HttpRequestRetryHandler() {
//            public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
//                if (executionCount >= 10) {// 如果已经重试了10次，就放弃
//                    return false;
//                }
//                if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
//                    return true;
//                }
//                if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
//                    return false;
//                }
//                if (exception instanceof InterruptedIOException) {// 超时
//                    return false;
//                }
//                if (exception instanceof UnknownHostException) {// 目标服务器不可达
//                    return false;
//                }
//                if (exception instanceof ConnectTimeoutException) {// 连接被拒绝
//                    return false;
//                }
//                if (exception instanceof SSLException) {// ssl握手异常
//                    return false;
//                }
//
//                HttpClientContext clientContext = HttpClientContext.adapt(context);
//                HttpRequest request = clientContext.getRequest();
//                if (!(request instanceof HttpEntityEnclosingRequest)) {
//                    return true;
//                }
//                return false;
//            }
//        };
//    }

    public static String doGet(String url, List<NameValuePair> params, String charset) {
//        String result = "";
//        CloseableHttpClient httpClient = HttpClients.custom()
//                .setConnectionManager(cm)
//                .setRetryHandler(httpRequestRetryHandler)
//                .build();
//        if (params != null) {
//            url = url + "?" + URLEncodedUtils
//                    .format(params, Charset.forName("UTF-8"));
//        }
//        HttpGet httpget = new HttpGet(url);
//        config(httpget);
//        CloseableHttpResponse response = null;
//        try {
//            response = httpClient.execute(httpget, HttpClientContext.create());
//            HttpEntity entity = response.getEntity();
//            result = EntityUtils.toString(entity, charset);
//            EntityUtils.consume(entity);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (response != null)
//                    response.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return result;
        return null;
    }

//    public static void config(HttpRequestBase httpRequestBase) {
//        httpRequestBase.setHeader("User-Agent", "Mozilla/5.0");
//        httpRequestBase.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//        httpRequestBase.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");//"en-US,en;q=0.5");
//        httpRequestBase.setHeader("Accept-Charset", "ISO-8859-1,utf-8,gbk,gb2312;q=0.7,*;q=0.7");
//
//        // 配置请求的超时设置
//        RequestConfig requestConfig = RequestConfig.custom()
//                .setConnectionRequestTimeout(60000)
//                .setConnectTimeout(60000)
//                .setSocketTimeout(60000)
//                .build();
//        httpRequestBase.setConfig(requestConfig);
//    }
}
