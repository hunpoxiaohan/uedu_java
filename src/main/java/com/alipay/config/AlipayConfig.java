package com.alipay.config;
import java.io.FileWriter;
import java.io.IOException;
public class AlipayConfig {
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2021000120618768";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCDUhDnFBFaJkMr4ZEYCTjgPLEQGHMlSJFWrHnI/7iMfB/K1QOyEnhjOyzQTvGLIXrOgCmcfzoC2+XYbLyn6Fk5KCb0jQyN/a+YDKnsHxMn8Y2rZSNkj0d6AmBDVFiESlGTpmdTxR7tb6yB4S+AQl012ZgjAHV3VGK+ReS8zP89gbXkwaY/1YR0H9ux4hIDOpJPsaN/7ppYC4V9ZgGGYFu2pzRGduGTeIUhppceXdctfjLU19Uzr6c2A/FfYDgEOShRbPyrwn2uvZRQcW6qEcgWo6suI4pieWn/NaquxVozzM+oFRD2g+lKdBsPeZM4ejemCbNBITv/N3ZWDW/O7DtpAgMBAAECggEAQITmhxHmAJvB7uJRTjThu9I10Emi8RN5TNNKtoVlX6hwWlBUE35dpatiNipDJG9bje+MAjbEArmVcMLj4b8XbyyaDxbpry2Pwy2dosQVvk9Aoo/7VqHKpVhbgVkEcGKvN9LO+2pi6pcuc1V26BYzA40X4UKNdAaCySc/3b7+PHIyHAeBfv5RoVKVhyaXYTW6sD51Fi3jG9/J05mE4PkLU2p1zSps1bkMc+lOvtRTu8u68Vs1jnHDKwNTYQPFdM3C9dy+gBVnS42iyAfXAM/GDBfr/A1VkS9NwfQmcJFageJ0XnAfnff9miDpq//QMIxxzsuM1kf2LWddysb7zafugQKBgQDbeUF6JQ+960no3Mp++ccC+CTvLodqjHdsJP8XytQSZhXkhrvBfQuhy0srWaksX8jEP7gtU23vaxJvUw6cR8XzvunSVqryCVpS3mISXmGX8Sb9cpzK1LhZq3jSnc29kHgujiekJoKOujHiLssVrBS/Ko47mlElcyGqBOMvrFPl2QKBgQCZLQUyY7Zi4ItpzHb8GujzYlJMBHtMoDeMh6hJYcFITSSDrVDPe9HZzAb7F4zKafsvLRbjEhaY4fz2LeB769+UzxDv01sE1CtaRRQpkuk+DZJuRXlUq5Xutcjgxj3Ys9wvLbhtGyAR901ATd7mXZ5CNb6/eGgz04I12gMGm5a4EQKBgG8fH7XWWfgQz2UllSlhEhBAz4KCjnG2GSkaOkYzndiK8363cQzwYRlUMwUJ6ovbG22xzXC1ky3rinPvG7Co7mMRQJYM55SQg7j9e65NNyCVKuacRyKzPXu5Q/1vhKH8Kb14oD2OegAF6gfZJV4duItp4oXvBjLt7heFTfnvSLNRAoGAPsrX4IpD0FDgleR9umeFq5BY0FTd/tGHNBLAiCSMyYjmORypjgTiR/h9vB0xWCEOGY7QXRGjNdUekATKi8JsC2K/PLFWFKqtgvnihBB3kTqbFp1gWbdlZVAcIxjhbZ592tFRU1jVUsggYQYLJECoidfidDd18Bj6vfy7VNAffyECgYBhgNu9RI04U+vnkewOvi9ZQoqqYVZ/0iYNmDQmKsBilKyL0TkDdsJwc5hzVBLIsbkZQcGKw3AELbPjID5sLirS2/IqOEwCszsm45pEXCnrjzvXvL6+oqHicnz0mF8rxCa7s0Z9nn5gHpy9i78zHnLx1KtCeQCcB7nuPfiqQurhew==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAokb3gzZ7uvMsvX7I/d96lIPLqoTInW1qSjU40uI1lxmZAeDqlpXd3FvOcwaDPxbQ7lwvh0vBj1E27uXLbDFWKCn44J7Lzmrb4BKTaDUSWiOgpkAnxHWvEdr/naNfwTULxKGtoAftjmB6Ms0syOvACF6BBncLUfqcZFYnsCmKzMSTbK1ptLCNR9sQWPve76mZW0TYFjFZDgkZ2Nxwhw2RDElMLJMdeGoLpf73kTtjkHXAYaurmrkCBkxCJsn23RKX6kbAs3ssDGtQsRRHICh9wTkAPNFRUAv6U3j5LB8PuG3yOfHC8wkrnva2+/oJgtkBd6QVzOAWI91uFf4PT24tZQIDAQAB";
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8080/edu/testPay/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8080/edu/testPay/return_url.jsp";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
