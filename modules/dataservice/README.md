## 说明文档
### 设备数据传输接口
首先在网站获取设备对应的 AccessKey 和 SecretKey
#### 1. 接口基本信息
- **接口名称**: 设备数据保存接口
- **请求方式**: `POST`
- **请求地址**: `http://localhost:8980/js/deviceAction/save`
- **请求数据格式**: `JSON`
- **响应数据格式**: `JSON`
#### 2. 请求参数

| 参数名       | 类型   | 是否必填 | 说明 |
|-------------|--------|---------|----------------|
| accessKey   | String | 是      | 访问密钥 |
| nonce       | String | 是      | 随机数（4 位数字） |
| timestamp   | String | 是      | 当前时间戳（秒级） |
| sign        | String | 是      | 请求签名 |
| dataDeviceId | String | 是     | 设备 ID |
请求体：body
{"dataDeviceId":"123","dataDeviceData":"123","isNewRecord":true,"corpCode":"0","corpName":"JeeSite"}

#### 3. 签名计算方式

签名 (`sign`) 计算方式如下：

1. 连接 `accessSecret`（示例中为 `"e2ab71c7d8c91d65aa5c04dc77ce4973"`）+ `nonce` + `timestamp` 形成待加密字符串；
2. 使用 `SHA256` 算法计算摘要；
3. 将计算出的摘要作为 `sign` 传入请求头。


#### 4. 请求示例（Java 代码）

```java
import cn.hutool.crypto.digest.Digester;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import java.util.HashMap;
import java.util.Map;

public class ApiRequestExample {
    public static void main(String[] args) {
        // 构造请求体
        DataserviceDeviceData dataserviceDeviceData = new DataserviceDeviceData();
        dataserviceDeviceData.setDataDeviceId("123");
        String json = JSONUtil.toJsonStr(dataserviceDeviceData);

        // 构造请求头
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("accessKey", "6309de886483c18b7edd502efd7efd3d");
        hashMap.put("nonce", RandomUtil.randomNumbers(4));
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));

        // 计算签名
        Digester md5 = new Digester(DigestAlgorithm.SHA256);
        String mySign = md5.digestHex("e2ab71c7d8c91d65aa5c04dc77ce4973"
                + hashMap.get("nonce") + hashMap.get("timestamp"));
        hashMap.put("sign", mySign);

        // 发送请求
        HttpResponse httpResponse = HttpRequest.post("http://localhost:8980/js/deviceAction/save")
                .body(json)
                .addHeaders(hashMap)
                .execute();

        // 输出响应
        System.out.println(httpResponse.body());
    }
}