package com.xyp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DT288 on 2016/9/8.
 */
public class Fun {

    public static void main(String[] args) {
        Map<Integer, Integer> sumMap = new HashMap<>();
        for (int i = 0; i < 255; i++) {
            int yu = i % 10;
            if (sumMap.get(yu) != null) {
                int plusOne = sumMap.get(yu) + 1;
                sumMap.put(yu, plusOne);
            } else {
                sumMap.put(yu, 1);
            }
        }
        System.out.print("Done");
    }

//    private static Pattern p = Pattern.compile("[^.0-9\\s]");
//
//
//    private static final String toolURL = "E:\\Code\\chromedriver.exe";
//
//    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(10,
//            20,
//            5, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100));
//
//    @Test
//    public void test() {
//        try {
//            ReadCVSService r = new ReadCVSService("E:\\Code\\Splider\\price_inaccuracies_detail.csv");
//            String s = CSVtoJSONUtil.CSVtoJSON(r.readLine());
//            PriceAccuracyCVS data = JsonUtil.fromJson(s, PriceAccuracyCVS.class);
//            List<WebDriverFromHilton> list = new ArrayList<WebDriverFromHilton>();
//            List<FunDTO> funList = new ArrayList<FunDTO>();
//            for (PriceAccuracyCVS.PriceAccuracyDTO dto : data.getResult()) {
//                try {
//                    WebDriverFromHilton webDriverFromHilton = WebDriverService.webDriver(dto.getUrl());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    System.out.println(JsonUtil.toJson(dto));
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.print("asd");
//    }
//
//
//    private class MinPriceElement {
//
//        public MinPriceElement(WebElement e, double p) {
//            this.webElement = e;
//            this.price = p;
//        }
//
//        public WebElement getWebElement() {
//            return webElement;
//        }
//
//        public void setWebElement(WebElement webElement) {
//            this.webElement = webElement;
//        }
//
//        public double getPrice() {
//            return price;
//        }
//
//        public void setPrice(double price) {
//            this.price = price;
//        }
//
//        private WebElement webElement;
//
//        private double price;
//    }
//
//    class TaskCallable implements Callable<Integer> {
//
//        CompareService cs;
//
//        PriceAccuracyCVS.PriceAccuracyDTO dto;
//
//        TaskCallable(CompareService cs, PriceAccuracyCVS.PriceAccuracyDTO dto) {
//            this.cs = cs;
//            this.dto = dto;
//        }
//
//        @Override
//        public Integer call() {
//            try {
//                CompareResultDto compareResultDto = cs.getDStorageRate(dto);
//                WebDriverFromHilton webDriverFromHilton = WebDriverService.webDriver(dto.getUrl());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return 1;
//        }
//    }
//
//    @Test
//    public void checkTax() {
//        ReadCVSService r = null;
//        try {
//            r = new ReadCVSService("E:\\Code\\Splider\\2017-04-22.csv");
//
//            String s = CSVtoJSONUtil.CSVtoJSON(r.readLine());
//            PriceAccuracyCVS data = JsonUtil.fromJson(s, PriceAccuracyCVS.class);
//            List<String> hotelCodeList = new ArrayList<String>();
//            for (PriceAccuracyCVS.PriceAccuracyDTO dto : data.getResult()) {
//                try {
//                    if (Math.abs(Double.valueOf(dto.getScraped_price()) - Double.valueOf(dto.getFeed_price())) < 1) {
//                        if (!dto.getScraped_tax().equals(dto.getFeed_tax())) {
//                            hotelCodeList.add(dto.getHotel_id());
//                        }
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//            System.out.print(hotelCodeList);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    @Test
//    public void fanhehe() {
//        String s = "";
//        String[] s1 = s.split("!!");
//        List<FunDTO> list = new ArrayList<FunDTO>();
//        for (String temp : s1) {
//            FunDTO dto = JsonUtil.fromJson(temp, FunDTO.class);
//            list.add(dto);
//        }
//
//        Map<String, List<WaringType>> errorMap = new HashMap<String, List<WaringType>>();
//        for (FunDTO f : list) {
//            try {
//                List<WaringType> waringTypeList = new ArrayList<WaringType>();
//
//
//                //---------------检查税对不对-------------------------------------------
//                String hotelCode = f.getPriceAccuracyDTO().getHotel_id();
//                if (Math.abs(Double.valueOf(f.getPriceAccuracyDTO().getScraped_price()) - Double.valueOf(f.getPriceAccuracyDTO().getFeed_price())) < 1) {
//                    if (!f.getPriceAccuracyDTO().getScraped_tax().equals(f.getPriceAccuracyDTO().getFeed_tax())) {
//                        waringTypeList.add(WaringType.TaxDifferent);
//                        errorMap.put(hotelCode, waringTypeList);
//                        continue;
//                    }
//                }
//                //-----------------检查google是否出错-------------------------
//                double fetchedPrice = Double.valueOf(f.getPriceAccuracyDTO().getScraped_price().replace(",", "").replace("*", ""));
//                double webDriver = Double.valueOf(f.getWebDriverFromHilton().getPrice().replace(",", "").replace("*", ""));
//                if (fetchedPrice == 0 && webDriver != 0) {
//                    waringTypeList.add(WaringType.Google_error);
//                    errorMap.put(hotelCode, waringTypeList);
//                    continue;
//                }
//
//                //-----------------先判断是否已经正确------------------------------------------
//                double googleAdaper = f.getCompareResultDto().getAdapterList().get(0).getBaseRate().doubleValue();
//                if (Math.abs(googleAdaper - fetchedPrice) < 1) {
//                    waringTypeList.add(WaringType.AlreadyRight);
//                    errorMap.put(hotelCode, waringTypeList);
//                    continue;
//                }
//
//                //-----------------酒店是否已经删除了房型-----------------------------------------------------------
//                if (webDriver == 0) {
//                    waringTypeList.add(WaringType.HotelDeletePrice);
//                    errorMap.put(hotelCode, waringTypeList);
//                    continue;
//                }
//
//                //-----------------------如果还没有正确-----------------------------------------------------------
//                boolean fetchWeb = false;
//                if (fetchedPrice != webDriver) {
//                    waringTypeList.add(WaringType.fetched_priceVSwebDriver);
//                } else {
//                    fetchWeb = true;
//                }
//
//                double cachePrice = Double.valueOf(f.getPriceAccuracyDTO().getFeed_price().replace(",", "").replace("*", ""));
//                double hte = f.getCompareResultDto().getHteList().get(0).getBaseRate().doubleValue();
//                double dstorage = f.getCompareResultDto().getDstorageList().get(0).getBaseRate().doubleValue();
//
//                if (Math.abs(hte - dstorage) < 1 && Math.abs(hte - googleAdaper) < 1 && Math.abs(googleAdaper - dstorage) < 1) {
//                    if (hte != fetchedPrice) {
//                        boolean hteFindFetch = false;
//                        for (GoogleRateCheckDto hteDto : f.getCompareResultDto().getHteList()) {
//                            if (hteDto.getBaseRate().doubleValue() == fetchedPrice) {
//                                hteFindFetch = true;
//                            }
//                        }
//                        boolean dstorageFindFetch = false;
//                        for (GoogleRateCheckDto dsDto : f.getCompareResultDto().getDstorageList()) {
//                            if (dsDto.getBaseRate().doubleValue() == fetchedPrice) {
//                                dstorageFindFetch = true;
//                            }
//                        }
//
//                        if (hteFindFetch && !dstorageFindFetch) {
//                            waringTypeList.add(WaringType.CacheLackPrice);
//                        }
//
//                        if (Math.abs(hte - fetchedPrice) > 1 && (webDriver == fetchedPrice)) {
//                            waringTypeList.add(WaringType.hteDiffWithWeb);
//                        }
//
//                    }
//
//                } else {
//                    waringTypeList.add(WaringType.HotelChangePrice);
//                }
//
//                boolean isHteHaveWebPrice = false;
//                for (GoogleRateCheckDto hteDto : f.getCompareResultDto().getHteList()) {
//                    if (hteDto.getBaseRate().doubleValue() == fetchedPrice) {
//                        isHteHaveWebPrice = true;
//                    }
//                }
//
//                boolean isdstorageHaveWebPrice = false;
//                for (GoogleRateCheckDto dsDto : f.getCompareResultDto().getDstorageList()) {
//                    if (dsDto.getBaseRate().doubleValue() == fetchedPrice) {
//                        isdstorageHaveWebPrice = true;
//                    }
//                }
//
//                if (Math.abs(hte - dstorage) < 1 && Math.abs(hte - googleAdaper) > 1) {
//                    waringTypeList.add(WaringType.GetPriceDelay);
//                }
//
//                if (isHteHaveWebPrice && !isdstorageHaveWebPrice) {
//                    waringTypeList.add(WaringType.CacheNoPrice);
//                }
//
//                if (!isHteHaveWebPrice && !isdstorageHaveWebPrice) {
//                    waringTypeList.add(WaringType.hteDiffWithWeb);
//                }
//
//                if (isHteHaveWebPrice && isdstorageHaveWebPrice) {
//                    waringTypeList.add(WaringType.HotelChangePrice);
//                }
//
//                errorMap.put(hotelCode, waringTypeList);
//            } catch (Exception e) {
//                e.printStackTrace();
//                System.out.println(JsonUtil.toJson(f));
//            }
//
//        }
//        System.out.println(JsonUtil.toJson(errorMap));
//
//        System.out.print("asd");
//    }
//
//    @Test
//    public void heheh() {
//        try {
//            ReadCVSService r = new ReadCVSService("E:\\Code\\Splider\\2017-04-30.csv");
//            String s = CSVtoJSONUtil.CSVtoJSON(r.readLine());
//            PriceAccuracyCVS data = JsonUtil.fromJson(s, PriceAccuracyCVS.class);
//            CompareService cs = new CompareService();
//            List<FunDTO> funList = new ArrayList<FunDTO>();
//
//            data.getResult().stream().parallel().forEach(dto -> {
//                try {
//                    CompareResultDto compareResultDto = cs.getDStorageRate(dto);
//                    WebDriverFromHilton webDriverFromHilton = CrawlHiltonUtil.getLowestRate(dto.getUrl());
//                    funList.add(new FunDTO(dto, compareResultDto, webDriverFromHilton));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    System.out.println(JsonUtil.toJson(dto));
//                }
//            });
//
//
//            Map<String, List<WaringType>> errorMap = new HashMap<String, List<WaringType>>();
//            for (FunDTO f : funList) {
//                try {
//                    List<WaringType> waringTypeList = new ArrayList<WaringType>();
//
//                    String hotelCode = f.getPriceAccuracyDTO().getHotel_id();
//                    String checkIn = f.getPriceAccuracyDTO().getCheck_in();
//                    String night = f.getPriceAccuracyDTO().getNights();
//
//                    String key = hotelCode + checkIn + night;
//
//                    if (f.getCompareResultDto().getHteList().isEmpty()) {
//                        waringTypeList.add(WaringType.hteNoPrice);
//                        errorMap.put(key, waringTypeList);
//                        continue;
//                    }
//                    if (f.getCompareResultDto().getDstorageList().isEmpty()) {
//                        waringTypeList.add(WaringType.DstorgeNoPrice);
//                        errorMap.put(key, waringTypeList);
//                        continue;
//                    }
//                    if (f.getCompareResultDto().getAdapterList().isEmpty()) {
//                        waringTypeList.add(WaringType.GoogleAdapterNoPrice);
//                        errorMap.put(key, waringTypeList);
//                        continue;
//                    }
//
//                    if (DateTimeUtils.parse(f.getPriceAccuracyDTO().getCheck_in()).compareTo(DateTimeUtils.getUtcDatetime()) == -1) {
//                        waringTypeList.add(WaringType.TimeaPast);
//                        errorMap.put(key, waringTypeList);
//                        continue;
//                    }
//
//                    //---------------检查税对不对-------------------------------------------
//
//                    if (Math.abs(Double.valueOf(f.getPriceAccuracyDTO().getScraped_price()) - Double.valueOf(f.getPriceAccuracyDTO().getFeed_price())) < 1) {
//                        if (!f.getPriceAccuracyDTO().getScraped_tax().equals(f.getPriceAccuracyDTO().getFeed_tax())) {
//                            waringTypeList.add(WaringType.TaxDifferent);
//                            errorMap.put(key, waringTypeList);
//                            continue;
//                        }
//                    }
//                    //-----------------检查google是否出错-------------------------
//                    double fetchedPrice = Double.valueOf(f.getPriceAccuracyDTO().getScraped_price().replace(",", "").replace("*", ""));
//                    double webDriver = Double.valueOf(f.getWebDriverFromHilton().getPrice().replace(",", "").replace("*", ""));
//                    if (fetchedPrice == 0 && webDriver != 0) {
//                        waringTypeList.add(WaringType.Google_error);
//                        errorMap.put(key, waringTypeList);
//                        continue;
//                    }
//
//                    //-----------------先判断是否已经正确------------------------------------------
//                    double googleAdaper = f.getCompareResultDto().getAdapterList().get(0).getBaseRate().doubleValue();
//                    if (Math.abs(googleAdaper - fetchedPrice) < 1 || Math.abs(googleAdaper - webDriver) < 1) {
//                        waringTypeList.add(WaringType.AlreadyRight);
//                        errorMap.put(key, waringTypeList);
//                        continue;
//                    }
//
//                    //-----------------酒店是否已经删除了房型-----------------------------------------------------------
//                    if (webDriver == 0) {
//                        waringTypeList.add(WaringType.HotelDeletePrice);
//                        errorMap.put(key, waringTypeList);
//                        continue;
//                    }
//
//                    //-----------------------如果还没有正确-----------------------------------------------------------
//                    boolean fetchWeb = false;
//                    if (fetchedPrice != webDriver) {
//                        waringTypeList.add(WaringType.fetched_priceVSwebDriver);
//                    } else {
//                        fetchWeb = true;
//                    }
//
//                    double cachePrice = Double.valueOf(f.getPriceAccuracyDTO().getFeed_price().replace(",", "").replace("*", ""));
//                    double hte = f.getCompareResultDto().getHteList().get(0).getBaseRate().doubleValue();
//                    double dstorage = f.getCompareResultDto().getDstorageList().get(0).getBaseRate().doubleValue();
//
//                    if (Math.abs(hte - dstorage) < 1 && Math.abs(hte - googleAdaper) < 1 && Math.abs(googleAdaper - dstorage) < 1) {
//                        if (hte != fetchedPrice) {
//                            boolean hteFindFetch = false;
//                            for (GoogleRateCheckDto hteDto : f.getCompareResultDto().getHteList()) {
//                                if (hteDto.getBaseRate().doubleValue() == fetchedPrice) {
//                                    hteFindFetch = true;
//                                }
//                            }
//                            boolean dstorageFindFetch = false;
//                            for (GoogleRateCheckDto dsDto : f.getCompareResultDto().getDstorageList()) {
//                                if (dsDto.getBaseRate().doubleValue() == fetchedPrice) {
//                                    dstorageFindFetch = true;
//                                }
//                            }
//
//                            if (hteFindFetch && !dstorageFindFetch) {
//                                waringTypeList.add(WaringType.CacheLackPrice);
//                            }
//
//                            if (Math.abs(hte - fetchedPrice) > 1 && Math.abs(webDriver - fetchedPrice) < 1) {
//                                waringTypeList.add(WaringType.hteDiffWithWeb);
//                            }
//
//                        }
//
//                    } else {
//                        waringTypeList.add(WaringType.HotelChangePrice);
//                    }
//
//                    boolean isHteHaveWebPrice = false;
//                    for (GoogleRateCheckDto hteDto : f.getCompareResultDto().getHteList()) {
//                        if (hteDto.getBaseRate().doubleValue() == fetchedPrice) {
//                            isHteHaveWebPrice = true;
//                        }
//                    }
//
//                    boolean isdstorageHaveWebPrice = false;
//                    for (GoogleRateCheckDto dsDto : f.getCompareResultDto().getDstorageList()) {
//                        if (dsDto.getBaseRate().doubleValue() == fetchedPrice) {
//                            isdstorageHaveWebPrice = true;
//                        }
//                    }
//
//                    if (Math.abs(hte - dstorage) < 1 && Math.abs(hte - googleAdaper) > 1) {
//                        waringTypeList.add(WaringType.GetPriceDelay);
//                    }
//
//                    if (isHteHaveWebPrice && !isdstorageHaveWebPrice) {
//                        waringTypeList.add(WaringType.CacheNoPrice);
//                    }
//
//                    if (!isHteHaveWebPrice && !isdstorageHaveWebPrice) {
//                        waringTypeList.add(WaringType.hteDiffWithWeb);
//                    }
//
//                    if (isHteHaveWebPrice && isdstorageHaveWebPrice) {
//                        waringTypeList.add(WaringType.HotelChangePrice);
//                    }
//
//                    errorMap.put(key, waringTypeList);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    System.out.println(JsonUtil.toJson(f));
//                }
//
//            }
//            System.out.println(JsonUtil.toJson(errorMap));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
