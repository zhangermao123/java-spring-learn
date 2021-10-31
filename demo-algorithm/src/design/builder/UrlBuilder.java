package design.builder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.builder
 * @descripation 生成器 url 拼接 类似 https://baidu.com?name=ts&pasd=345
 * @date 2021-07-16
 */
public class UrlBuilder {

    private String domain;
    private String scheme;
    private String path;
    private Map<String, Object> query;

    public static UrlBuilder builder() {
        return new UrlBuilder();
    }

    public UrlBuilder setDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public UrlBuilder setScheme(String scheme) {
        this.scheme = scheme;
        return this;
    }

    public UrlBuilder setPath(String path) {
        this.path = path;
        return this;
    }

    public UrlBuilder setQuery(Map<String, Object> query) {
        this.query = query;
        return this;
    }

    public String build() {
        StringBuilder stringBuilder = new StringBuilder();
        Optional.ofNullable(this).map(builder -> {
            Optional.of(builder.scheme).map(s -> stringBuilder.append(s)).orElse(stringBuilder.append("http:"));
            Optional.of(builder.domain).map(d -> stringBuilder.append(d)).orElseThrow(() -> new RuntimeException("没有domin"));
            Optional.of(builder.path).map(p -> stringBuilder.append("/").append(path));
            if (null != builder.query) {
                stringBuilder.append("?");
                builder.query.forEach((k, v) -> {
                    stringBuilder.append(k).append("=").append(v).append("&");
                });
                stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
            }
            return stringBuilder.toString();
        });
        return stringBuilder.toString();
    }
}
