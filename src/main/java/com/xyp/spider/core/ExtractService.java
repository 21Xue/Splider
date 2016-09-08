package com.xyp.spider.core;

import com.xyp.spider.rule.Rule;
import com.xyp.spider.util.TextUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author zhy
 */
public class ExtractService {


    public static Elements getElement(Element element, String tagName) {

        Elements results = element.getElementsByClass(tagName);
        return results;

    }


    public static Elements getFristHtml(Rule rule) {
        try {
            String url = rule.getUrl();
            String resultTagName = rule.getResultTagName();
            int type = rule.getType();
            int requestType = rule.getRequestMoethod();

            Connection conn = Jsoup.connect(url);
            Document doc = null;
            switch (requestType) {
                case Rule.GET:
                    doc = conn.timeout(100000).get();
                    break;
                case Rule.POST:
                    doc = conn.timeout(100000).post();
                    break;
            }
            //处理返回数据
            Elements results = new Elements();
            switch (type) {
                case Rule.CLASS:
                    results = doc.getElementsByClass(resultTagName);
                    break;
                case Rule.ID:
                    Element result = doc.getElementById(resultTagName);
                    results.add(result);
                    break;
                case Rule.SELECTION:
                    results = doc.select(resultTagName);
                    break;
                default:
                    //当resultTagName为空时默认去body标签
                    if (TextUtil.isEmpty(resultTagName)) {
                        results = doc.getElementsByTag("body");
                    }
            }
            return results;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
