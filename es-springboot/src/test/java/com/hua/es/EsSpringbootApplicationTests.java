package com.hua.es;


import com.alibaba.fastjson.JSON;
import com.hua.es.pojo.Staff;
import com.hua.es.utils.RandomStringUtils;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.document.DocumentField;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchExtBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import javax.naming.directory.SearchResult;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import static java.lang.System.*;

@SpringBootTest
class EsSpringbootApplicationTests {

    @Autowired
    private RestHighLevelClient restHighLevelClient ;

    @Test
    void contextCreateIndex() throws IOException {
        CreateIndexRequest hua_index = new CreateIndexRequest("hua_index");
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(hua_index, RequestOptions.DEFAULT);
        out.println(createIndexResponse.toString());
    }
    @Test
    void contextIsExistsIndex() throws IOException {
        GetIndexRequest hua_index = new GetIndexRequest("hua_index2");
        boolean exists = restHighLevelClient.indices().exists(hua_index, RequestOptions.DEFAULT);
        out.println(exists);
    }
    @Test
    void contextDeleteIndex() throws IOException{
        DeleteIndexRequest hua_index = new DeleteIndexRequest("hua_index");
        AcknowledgedResponse delete = restHighLevelClient.indices().delete(hua_index, RequestOptions.DEFAULT);
        out.println(delete.toString());
    }

    @Test
    void contextAddDoc() throws IOException {
        Staff staff = new Staff("Alan","JavaGo");
        IndexRequest request = new IndexRequest("hua_index") ;
        request.id("1") ;
        request.timeout(TimeValue.timeValueSeconds(1)) ;
        request.timeout("1s");

        request.source(JSON.toJSONString(staff), XContentType.JSON);

        IndexResponse indexResponse = restHighLevelClient.index(request,RequestOptions.DEFAULT);
        out.println("response is "+indexResponse.toString());
        out.println("status is "+indexResponse.status());
    }

    @Test
    void contextIsExistsDocAndGetDocInfo() throws IOException {
        GetRequest getRequest = new GetRequest("hua_index","1") ;
        getRequest.fetchSourceContext(new FetchSourceContext(false)) ;
        getRequest.storedFields("_none_") ;

        boolean exists = restHighLevelClient.exists(getRequest,RequestOptions.DEFAULT) ;
        out.println(exists);
        if(exists){
            GetResponse documentFields = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
            out.println(documentFields.getSourceAsString());
            Map<String, DocumentField> fields = documentFields.getFields();
            for(var fieldKey : fields.keySet()){
                out.println("key = "+fieldKey+",value = "+fields.get(fieldKey));
            }
            out.println(documentFields);
        }
    }

    @Test
    void contextUpdateDoc() throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("hua_index","1");
        updateRequest.timeout("1s") ;

        Staff staff = new Staff("阿兰学es","Eat") ;
        updateRequest.doc(JSON.toJSONString(staff),XContentType.JSON);

        UpdateResponse updateResponse = restHighLevelClient.update(updateRequest,RequestOptions.DEFAULT) ;
        out.println(updateResponse.status());
        out.println(updateResponse.toString());
    }

    @Test
    void contextDeleteDoc() throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest("hua_index","1") ;
        deleteRequest.timeout("1s");
        deleteRequest.id("1") ;
        DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest,RequestOptions.DEFAULT) ;
        out.println(deleteResponse.status());
        out.println(deleteResponse.toString());
    }

    @Test
    void contextPutVal() throws IOException {
        BulkRequest bulkRequest = new BulkRequest() ;
        bulkRequest.timeout("200s");

        ArrayList<Staff> staffs = new ArrayList<>();

        for(int i =0 ;i<100;i++) {
            Random random = new Random();
            int staffNameLength = random.nextInt(15);
            int staffHobbyLength = random.nextInt(15);
            String staffName = RandomStringUtils.getRadomStrings(staffNameLength) ;
            String staffHobby = RandomStringUtils.getRadomStrings(staffHobbyLength) ;
            staffs.add(new Staff(staffName,staffHobby));
        }
        for(int i =0;i<staffs.size();i++){
            out.println(staffs.get(i));
            bulkRequest.add(//不能是一个index对象
                    new IndexRequest("hua_index")
                    .id(""+(i+1))
                    .source(JSON.toJSONString(staffs.get(i)),XContentType.JSON)
            );
        }
        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT) ;
        out.println(bulkResponse.status());
        out.println(bulkResponse.toString());
    }

    @Test
    void contextSearch() throws IOException {
        SearchRequest searchRequest = new SearchRequest("hua_index") ;
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder( );
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("staffName","") ;
        searchSourceBuilder.query(termQueryBuilder);
        searchSourceBuilder.from();
        searchSourceBuilder.size();
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest,RequestOptions.DEFAULT) ;
        for(SearchHit documentFields : searchResponse.getHits().getHits()){
            out.println(documentFields.getSourceAsMap());
        }
    }
}


