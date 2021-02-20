package top.elichn.config;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.slf4j.LoggerFactory;

/**
 * <p>Title: ConfigClient</p>
 * <p>Description: https://github.com/lexburner/longPolling-demo  curl -X GET "localhost:8080/publishConfig?dataId=user&configInfo=helloworld888"</p>
 * <p>Company: shanshu</p>
 *
 * @author eli
 * @version 1.0
 * @date 2021/2/20
 */
@Slf4j
public class ConfigClient {

    private CloseableHttpClient httpClient;
    private RequestConfig requestConfig;

    public ConfigClient() {
        this.httpClient = HttpClientBuilder.create().build();
        // ① httpClient 客户端超时时间要大于长轮询约定的超时时间
        this.requestConfig = RequestConfig.custom().setSocketTimeout(40000).build();
    }

    @SneakyThrows
    public void longPolling(String url, String dataId) {
        String endpoint = url + "?dataId=" + dataId;
        HttpGet request = new HttpGet(endpoint);
        CloseableHttpResponse response = httpClient.execute(request);
        switch (response.getStatusLine().getStatusCode()) {
            case 200: {
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity()
                        .getContent()));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
                response.close();
                String configInfo = result.toString();
                log.info("dataId: [{}] changed, receive configInfo: {}", dataId, configInfo);
                longPolling(url, dataId);
                break;
            }
            // ② 304 响应码标记配置未变更
            case 304: {
                log.info("longPolling dataId: [{}] once finished, configInfo is unchanged, longPolling again", dataId);
                longPolling(url, dataId);
                break;
            }
            default: {
                throw new RuntimeException("unExcepted HTTP status code");
            }
        }

    }

    public static void main(String[] args) {
        // httpClient 会打印很多 debug 日志，关闭掉
        Logger logger = (Logger)LoggerFactory.getLogger("org.apache.http");
        logger.setLevel(Level.INFO);
        logger.setAdditive(false);

        ConfigClient configClient = new ConfigClient();
        // ③ 对 dataId: user 进行配置监听
        configClient.longPolling("http://127.0.0.1:8080/listener", "user");
    }

}
