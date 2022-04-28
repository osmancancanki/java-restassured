import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RequestHelper {
    public RequestSpecification requestSpecification;
    public ResponseSpecification responseSpecification;
    ConfigReader configReader = new ConfigReader();

    public RequestHelper() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
                .setBaseUri(configReader.getConfigProperty("url"))
                .setContentType(ContentType.JSON)
                .addHeader("api-key", configReader.getConfigProperty("token"));
        requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.BODY);
        responseSpecification = responseSpecBuilder.build();
    }
}
