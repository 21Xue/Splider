package com.xyp.zigzag;

/**
 * Created by DT241 on 2017/4/27.
 */
public class CrawlHiltonUtil {

//    private static String prefix = "https://secure3.hilton.com";
//
//    //    private static Pattern p = Pattern.compile("[^.0-9\\s]");
////    private static Pattern p2 = Pattern.compile("([0-9]+|[0-9]{1,3}(,[0-9]{3})*)(.[0-9]{1,2})?");
//    private static Pattern p = Pattern.compile("[^0-9\\.]*([0-9\\.]+)[^0-9\\.]*");
////
////    public CrawlHiltonUtil(String prefix) {
////        if (prefix == null) {
////            prefix = "https://secure3.hilton.com";
////        }
////        this.prefix = prefix;
////    }
//
//    public static WebDriverFromHilton getLowestRate(String deeplink) throws Exception {
//
//        long startTime = System.currentTimeMillis();
//        HttpClientContext context = HttpClientContext.create();
//        String result = HttpInvoker.doGetWithContext(context, deeplink.trim(), null, "UTF-8");
//        Document document = Jsoup.parse(result);
//        Elements elements = document.getElementsByClass("rate");
//        List<Element> list = new ArrayList<Element>();
//        first:
//        for (Element element : elements) {
//            for (Element aelement : element.getElementsByTag("a")) {
//                if (aelement.text().contains("HONORS")) {
//                    continue first;
//                }
//            }
//            list.add(element);
//        }
//        String price = "0";
//        String tax = "0";
//        String total = "0";
//        String charge = "0";
//        if (list.isEmpty()) {
//            return new WebDriverFromHilton(price, tax, total, charge);
//        }
//        Element minElement = Collections.min(list, new Comparator<Element>() {
//            @Override
//            public int compare(Element o1, Element o2) {
//                Matcher m1 = p.matcher(getPriceString(o1));
//                String price1 = "0";
//                if (m1.find()) {
//                    price1 = m1.group(1);
//                }
//
//                Matcher m2 = p.matcher(getPriceString(o2));
//                String price2 = "0";
//                if (m2.find()) {
//                    price2 = m2.group(1);
//                }
//                if (Double.valueOf(price1) == 0) {
//                    return 1;
//                }
//                if (Double.valueOf(price2) == 0) {
//                    return -1;
//                }
//                if (Double.valueOf(price1) < Double.valueOf(price2)) {
//                    return -1;
//                }
//                return 1;
//            }
//        });
//
//        String rateUrl = minElement.getElementsByClass("rate-desc-wrapper").get(0).getElementsByTag("a").get(0).attr("href");
//        rateUrl = prefix + rateUrl;
//        result = HttpInvoker.doGetWithContext(context, rateUrl, null, "UTF-8");
//        document = Jsoup.parse(result);
//
//        Elements trElements = document.getElementsByTag("tr");
//
//
//        for (Element element : trElements) {
//            Elements tdElements = element.getElementsByTag("td");
//            for (Element tdElement : tdElements) {
//                if ("Room Subtotal".equals(tdElement.text())) {
//                    price = tdElements.get(tdElements.size() - 1).text();
//                }
//                if ("Total for stay".equals(tdElement.text())) {
//                    total = tdElements.get(tdElements.size() - 1).text();
//                }
//                if ("Taxes".equals(tdElement.text())) {
//                    tax = tdElements.get(tdElements.size() - 1).text();
//                }
//                if (tdElement.text().contains("Charge")) {
//                    charge = tdElements.get(tdElements.size() - 1).text();
//                }
//
//            }
//
//        }
//
//        long endTime = System.currentTimeMillis();
//
//        System.out.print(endTime - startTime);
//        return new WebDriverFromHilton(price, tax, total, charge);
//    }
//
//    private static String getPriceString(Element element) {
//        String price1 = element.getElementsByClass("new-price").text();
//        if (StringUtils.isEmpty(price1)) {
//            price1 = element.getElementsByClass("priceamount").text();
//        }
//        return price1;
//    }
//
//    public static void main(String[] args) {
//
//        Matcher m = p.matcher("a1234");
//        if (m.find()) {
//            System.out.println(m.group(1));
//        }
//
//    }
}
