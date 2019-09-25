package tver.wa.services;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

@SpringBootTest
public class GithubDataServiceTest {

    private final MockWebServer mockWebServer = new MockWebServer();
    private static final String GITHUB_V3_MIME_TYPE = "application/vnd.github.v3+json";
    private static final String USER_AGENT = "Spring 5 WebClient";
    private static final String BASE_URL = "localhost/";
    private WebClient webGithubClient = WebClient
            .builder()
            .baseUrl(BASE_URL)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, GITHUB_V3_MIME_TYPE)
            .defaultHeader(HttpHeaders.USER_AGENT, USER_AGENT)
            .build();
    private String url = mockWebServer.url(BASE_URL).toString();
    private HttpClient client = HttpClientBuilder.create().build();
    private HttpGet request = new HttpGet(url);

    @After
    public void onShutdown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    public void getLastGithubDataTest() {
        mockWebServer.enqueue(
                new MockResponse()
                        .setResponseCode(200)
                        .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .setBody(
                                "{\"userId\": 1,\"id\": 1, \"title\": \"sunt aut facere repellat provident occaecati "
                                        + "excepturi optio reprehenderit\", \"body\": \"quia et suscipit\"}"
                        )
        );
        Mono<String> result = webGithubClient.get().uri(url).retrieve().bodyToMono(String.class);
        assertEquals(result.block(), "");
    }

    @Test
    public void getLastGithubDataTest2() throws IOException {
        mockWebServer.enqueue(
                new MockResponse()
                        .setResponseCode(200)
                        .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .setBody("{\"userId\": 1,\"id\": 1, \"title\": \"sunt aut facere repellat provident occaecati "
                                + "excepturi optio reprehenderit\", \"body\": \"quia et suscipit\"}"));
        HttpResponse response = client.execute(request);
        Mono<String> result = Mono.just(EntityUtils.toString(response.getEntity()));
        String test = "";
        assertEquals(result.block(), "");
    }

}
