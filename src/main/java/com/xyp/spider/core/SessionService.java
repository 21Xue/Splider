package com.xyp.spider.core;

import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by DT288 on 2016/9/8.
 */
public class SessionService {

    @Test
    public void login() throws MalformedURLException, InterruptedException {
        String htmlurl = "http://us.linkcenter.derbysoftca.com/dplatform-linkcenter/booking.htm?hotelCode=HILTON-RDUII&providerHotelCode=RDUII&checkInDate=2016-09-18&checkOutDate=2016-09-19&identifier=google-hilton&price=191.82&roomTypeCode=NEXJEX&ratePlanCode=SDT2G2&currency=USD&language=en&userCountry=US&testClick=true&sitetype=verification&additionalInfo=2&partnerId=hilton";
        HttpURLConnection httpConn = null;
        String cookie = "";
        try {
            URL url = new URL(htmlurl);
            httpConn = (HttpURLConnection) url.openConnection();
            HttpURLConnection.setFollowRedirects(true);
            httpConn.setRequestMethod("POST");
            httpConn.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36");
            httpConn.setRequestProperty("Connection", "keep-alive");
            httpConn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml");
            httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpConn.setRequestProperty("Cache-control", "no-cache, no-store");
//            httpConn.setRequestProperty("Host", "www.linkedin.com");
            //httpConn.setRequestProperty("Referer","https://www.linkedin.com/uas/login?session_redirect=http://www.linkedin.com/profile/view?id=222323610&authType=name&authToken=fcEe");
            //post方法，重定向设置
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            httpConn.setUseCaches(false);
            httpConn.setInstanceFollowRedirects(true);
            //写入，post方法必须用流写入的方式传输数据
            httpConn.setConnectTimeout(20000);
            httpConn.setReadTimeout(20000);
            //获取重定向和cookie
            //String redictURL= httpConn.getHeaderField( "Location" );
            //System.out.println("第一次请求重定向地址 location="+redictURL);

            //获取cookie
            Map<String, List<String>> map = httpConn.getHeaderFields();
            //System.out.println(map.toString());
            Set<String> set = map.keySet();
            for (Iterator<String> iterator = set.iterator(); iterator.hasNext(); ) {
                String key = iterator.next();
                if (key != null) {
                    if (key.equals("Set-Cookie")) {
                        System.out.println("key=" + key + ",开始获取cookie");
                        List<String> list = map.get(key);
                        for (String str : list) {
                            String temp = str.split("=")[0];
                            //System.out.println(temp);
                            //cookie包含到信息非常多，调试发现登录只需这条信息
                            if (temp.equals("dplatform_visitor")) {
                                cookie = str;
//                                return cookie;
                            }

                        }
                    }
                }

            }
            httpConn.disconnect();

        } catch (final MalformedURLException me) {
            System.out.println("url不存在!");
            me.getMessage();
            throw me;
        } catch (final FileNotFoundException me) {
            System.out.println(htmlurl + "反爬启动");
        } catch (final IOException e) {
            e.printStackTrace();
            httpConn.disconnect();
            Thread.sleep(20000);
        }
        finally {
            httpConn.disconnect();
        }

//        return "";
    }

    @Test
    public void testGetOneHtml() {
        String url = "https://secure3.hilton.com/en_US/dt/reservation/book.htm?execution=e1s1&amp;_eventId=rateSummaryDetailsPopup&amp;srpId=SDTDDL&amp;roomCode=NK1S&amp;index=0&amp;internalDeepLinking=true";
        try {
            getOneHtml(url, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public String getOneHtml(String htmlurl, String encoding) throws IOException, InterruptedException {
        //最多重复请求5次，用来反爬的
//        System.out.println(cookie);

        String cookie="GWSESSIONID=L67yXRGGdnmjMWhbSvc61fS3DnN4sqCNz22vMMQ12GrG9lQ2GbMJ!334962963; pseudoIATANumber={\"travelAgentAccountId\":\"00081200\",\"leadId\":\"\"}; ak_bmsc=49FEA4F2FE3FBD69978416B9ADA36155DEBA10CC9B4000008604D1573734D511~pl42pnbtzF4uzZEZAOwkWq/GIPmbiaJ2JyudQnZ1gg9EL1gpgsbg59bb2v+C/U9/fmeh0fHx8Xk/kPX5/GMuwkNhn6AOquO4wyvhvAfrDNtTZgwH+hzGRY030nofnSXBKLa+4yK7flkoL7uhdiZ5MNi40BAmhwzncZBmnn72sP9OOTqYpq+FSJMKG3sKqa6IVVSxYaTgUXyBHYG3A2VxgqBKeVVuboV8kNe7iU85L4hsw=; mmcore.tst=0.736; T204_T231FakeMaster=T231; _sdsat_landing_page=https://secure3.hilton.com/en_US/dt/reservation/book.htm?execution=e2s1|1473315992332; _sdsat_session_count=1; _sdsat_lt_pages_viewed=2; _sdsat_pages_viewed=2; _sdsat_traffic_source=https://secure3.hilton.com/en_US/dt/reservation/book.htm?execution=e2s1; ClrCSTO=T; ClrSSID=1473315992497-12912; ClrOSSID=1473315992497-12912; ClrSCD=1473315992497; mm_pers_storage=loggedin%3Ano%7CBrand%3Adt%7CPromoCode%3Ano%7CBanner_Present%3Ano%7Cproperties8%3Ano%7Chotelcode%3Ano%7CFSP_AP%3A2x%20pts%20first%20without%20AP%7CReferrer%3ADisplay%7COMvFranchise%3AFranchised%7CCookied%3ANon-Cookied%7CSearchType%3AOther%7CUrgencyMsg%3Ablank; s_dfa=hiltonglobalprod; siteElement=; AMCV_F0C120B3534685700A490D45%40AdobeOrg=1304406280%7CMCMID%7C17928472355458715037875744818650574972%7CMCAID%7CNONE%7CMCAAMLH-1473920794%7C11%7CMCAAMB-1473920794%7Chmk_Lq6TPIBMW925SPhw3Q; sn.ds=d||a; gpv_v9=DT%3Areservation%3Abook%7CRDUIIDT%3ArateSummaryDetailsPopup; s_cc=true; sn.as=ir||_~a||t~vg||0~tp||1014~ti||t1~co||1~ctbr||DT; sn.vi=vi||7c81728c-5881-431f-a501-dab69f5c37e1~cslt||1473315998814~as||1~st||1473315998821~cat||1473315998814~c||1; sn.rn=rv||63.992~rt||59.999~re||98.009; sn.wt=u||id; mmapi.store.p.0=%7B%22mmparams.d%22%3A%7B%7D%2C%22mmparams.p%22%3A%7B%22mmid%22%3A%221504852071845%7C%5C%22-68807545%7CBAAAAAq7df670g0AAA%3D%3D%5C%22%22%2C%22pd%22%3A%221504852071848%7C%5C%22-68807545%7CBAAAAAq7df670g0AAA%3D%3D%5C%22%22%2C%22srv%22%3A%221504852071851%7C%5C%22lvsvwcgus12%5C%22%22%2C%22uat%22%3A%221504852079134%7C%7B%5C%22Banner_Present%5C%22%3A%5C%22no%5C%22%2C%5C%22Brand%5C%22%3A%5C%22dt%5C%22%2C%5C%22Cookied%5C%22%3A%5C%22Non-Cookied%5C%22%2C%5C%22OMvFranchise%5C%22%3A%5C%22Franchised%5C%22%2C%5C%22FSP_AP%5C%22%3A%5C%222x%20pts%20first%20without%20AP%5C%22%2C%5C%22hotelcode%5C%22%3A%5C%22no%5C%22%2C%5C%22loggedin%5C%22%3A%5C%22no%5C%22%2C%5C%22PromoCode%5C%22%3A%5C%22no%5C%22%2C%5C%22Referrer%5C%22%3A%5C%22Display%5C%22%2C%5C%22SearchType%5C%22%3A%5C%22Other%5C%22%2C%5C%22UrgencyMsg%5C%22%3A%5C%22blank%5C%22%2C%5C%22SignInSource%5C%22%3A%5C%22Other%5C%22%7D%22%7D%7D; mmapi.store.s.0=%7B%22mmparams.d%22%3A%7B%7D%2C%22mmparams.p%22%3A%7B%7D%7D";
        String temp;
        final StringBuffer sb = new StringBuffer();
        HttpURLConnection httpConn = null;
        try {
            URL url = new URL(htmlurl);
            httpConn = (HttpURLConnection) url.openConnection();
            HttpURLConnection.setFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36");
            httpConn.setRequestProperty("Connection", "keep-alive");
            httpConn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml");
            httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpConn.setRequestProperty("cookie", cookie);
            httpConn.setRequestProperty("Cache-control", "no-cache, no-store");
            httpConn.setRequestProperty("Host", "secure3.hilton.com");
            httpConn.setConnectTimeout(20000);
            httpConn.setReadTimeout(20000);
            // logger.info(httpConn.getResponseMessage());
            BufferedReader in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), encoding));////打开连接，获取内容
            if (httpConn.getResponseCode() != 200) {
                //System.out.println(httpConn.getHeaderField("location"));
                // System.out.println(httpConn.getResponseCode()+htmlurl);
                httpConn.disconnect();
                Thread.sleep(30000);
//                cookie = login();
                return getOneHtml(htmlurl, encoding);
            }
            while ((temp = in.readLine()) != null)
            //替换点一些无用到符号
            {
                sb.append(temp);
            }
            in.close();
            httpConn.disconnect();

        } catch (final MalformedURLException me) {
            System.out.println("url不存在!");
            me.getMessage();
            throw me;
        } catch (final FileNotFoundException me) {
            System.out.println(htmlurl + "反爬启动");
            return "0";
        } catch (final IOException e) {
            e.printStackTrace();
            httpConn.disconnect();
            Thread.sleep(20000);
            return this.getOneHtml(htmlurl, encoding);
        }

        //System.out.println(sb);
        httpConn.disconnect();
        return sb.toString();

    }

}
