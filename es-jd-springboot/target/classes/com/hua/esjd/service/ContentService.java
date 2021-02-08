package com.hua.esjd.service;


import com.alibaba.fastjson.JSON;
import com.hua.esjd.pojo.Content;
import com.hua.esjd.utils.HtmlParseUrl;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.lang.System.in;
import static java.lang.System.out;

@Service
public class ContentService {
    @Autowired
    private RestHighLevelClient restHighLevelClient ;
    @Autowired
    HtmlParseUrl htmlParseUrl ;
    public boolean parseContent(String keyword) throws IOException {
        BulkRequest bulkRequest = new BulkRequest() ;
        bulkRequest.timeout("5m");

        List<Content> contents = htmlParseUrl.parseJD(keyword) ;
        for(int i =0;i<contents.size();i++){
            bulkRequest.add(
                    new IndexRequest("jd_goods")
                            .id(""+(i+1))
                            .source(JSON.toJSONString(contents.get(i)), XContentType.JSON)
            );
        }
        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT) ;
        return ! bulkResponse.hasFailures();
    }
    public boolean createIndex(String index) throws IOException {
        GetIndexRequest jd_goods = new GetIndexRequest(index);
        boolean exists = restHighLevelClient.indices().exists(jd_goods, RequestOptions.DEFAULT);
        if (!exists) {
            CreateIndexRequest jd_index = new CreateIndexRequest(index);
            restHighLevelClient.indices().create(jd_index, RequestOptions.DEFAULT);
            return true ;
        }
        return false;
    }

    public boolean deleteIndex(String index) throws IOException {
        GetIndexRequest jd_goods = new GetIndexRequest(index);
        boolean exists = restHighLevelClient.indices().exists(jd_goods, RequestOptions.DEFAULT);
        if (exists) {
            DeleteIndexRequest jd_index = new DeleteIndexRequest(index);
            AcknowledgedResponse delete = restHighLevelClient.indices().delete(jd_index, RequestOptions.DEFAULT);
            return true ;
        }
        return false;
    }

    public ArrayList<Map<String,Object>> search(String index, String keyword,int pageNumber,int pageSize) throws IOException {
        if(pageNumber<=1){
            pageNumber = 1 ;
        }

        SearchRequest searchRequest = new SearchRequest(index) ;
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        searchSourceBuilder.from(pageNumber);
        searchSourceBuilder.size(pageSize);

        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title",keyword) ;
        searchSourceBuilder.query(termQueryBuilder);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest,RequestOptions.DEFAULT) ;
        ArrayList<Map<String,Object>> searchResult = new ArrayList<>();
        for(SearchHit documentFields : searchResponse.getHits().getHits()){
            searchResult.add(documentFields.getSourceAsMap()) ;
        }
        return searchResult ;
    }

    public ArrayList<Map<String,Object>> searchHighLight(String index, String keyword,int pageNumber,int pageSize) throws IOException {
        if(pageNumber<=1){
            pageNumber = 1 ;
        }

        SearchRequest searchRequest = new SearchRequest(index) ;
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        searchSourceBuilder.from(pageNumber);
        searchSourceBuilder.size(pageSize);

        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title",keyword) ;
        searchSourceBuilder.query(termQueryBuilder);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("title") ;
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");
        searchSourceBuilder.highlighter(highlightBuilder);

        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest,RequestOptions.DEFAULT) ;
        ArrayList<Map<String,Object>> searchResult = new ArrayList<>();
        for(SearchHit hit : searchResponse.getHits().getHits()){
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            HighlightField title = highlightFields.get("title") ;
            Map<String,Object> sourceAsMap = hit.getSourceAsMap();
            if(title!=null){
                Text[] fragments = title.fragments() ;
                String new_title = "" ;
                for(Text text : fragments){
                    new_title += text ;
                }
                sourceAsMap.put("title",new_title) ;
            }
            searchResult.add(sourceAsMap);
        }
        return searchResult ;
    }

}
