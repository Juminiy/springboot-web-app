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
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

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
}
