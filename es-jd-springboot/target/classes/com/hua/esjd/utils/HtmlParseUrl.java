package com.hua.esjd.utils;


import com.hua.esjd.pojo.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class HtmlParseUrl {

    public List<Content> parseJD(String keyword) throws IOException {
        String url = "https://search.jd.com/Search?keyword="+keyword;

        List<Content> contents = new ArrayList<>();
        Document document = Jsoup.parse(new URL(url), 30000);
        Element element = document.getElementById("J_goodsList");
        Elements elements = element.getElementsByTag("li");
        for(Element ele : elements) {
            if(!ele.attr("class").equalsIgnoreCase("ps-item")){
                String img = ele.getElementsByTag("img").eq(0).attr("data-lazy-img");
                String price = ele.getElementsByClass("p-price").eq(0).text();
                String T_name = ele.getElementsByClass("p-name").eq(0).text() ;
                contents.add(new Content(T_name,img,price)) ;
            }
        }
        return  contents ;
    }
}
