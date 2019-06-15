package com.phonemarket.util;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2019-05-09
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016092800613208";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCA4oNCW7oyoHh/bByCQrZ1PDyx28E4IgnsMmVUqvqLb7DlJvRgkS7TsnFeFP5+S5PRvHUv6mmN9YwsRBhodx46TsMvKL5ZxIGjuJWs8tNv/kP/wfRzx9Q2PNoWStwfI4/zgdVEuBzWbRDOcQik4r5RVV11PStFSUZnZZ0YHvmb18V/2EChK7N9S0UA1VRsZBvmybD/khc0WS99JsBVPq9wmaRIk8bROfZ/sJGTSpGYXiQAtNfEysLN+WRMbk8MFIQDStxDZNe5rz3cIGpXx8BVFJfDlI92EEeUEZNSK3/4oDcO+knGtsizs9oTlPi6zmaRO4MLsjj3N1llP6+iAHN9AgMBAAECggEAe32NmuVc0Olky2KBJmZZgJcI6LnQtsIWk9Ptt2Rjz3pjGg1rFaPGJFzTtvuSpNwxdEKIXjLSlIxzlG5inKWVtvSEnAkD6esY9BAGicHYyXPtH39thAox0Q4ZQEbWBpgCc6Uw/TkTmHjEI6m2SJZhGHkcpRrI/QgqhyObk+MJ80E8vUsMI46b23y+R6jGlVXQc3VOOL8xi6D+eYC02Obmm9uAiB5ugB3NO0lpoN+msxwizA56ODrKPqZRRXXbs9KfKT++zQ0xV60A1PjtCFkhPBqugJV03dba6CAgyl8d+e77xE/PC+a9NaIog+llP+3Rhu3+EyqZBlx+GCG6aCiFGQKBgQC9X36bnn9F6LPcsUA9Trd4G0RL01cRgfMlDZXXoCGCIh4Ih1593MZ5GpyTu2hXgKX5m/Nl4Rmxh9Rf9H27IUxP/7QGDRyTrirVNHsibElMds9HSkHbFHHE1UzGM7tsuhUuMJVXuEuu5V4ZVco0VCsrxoOo4iUq5C1cmqj+BxAg+wKBgQCuOvK/dVn9Km2z2DeEoIfVy860uDLLRcxJfGUjiaGxJ/TX59xTgONmuSfmJ+HN/yYonYQg2wlabDIDopLYzv47voQXYYE5vk8XDaP8B1qzWPWBBhJLBdWxCZ+G9oBp5Z+qImfVpF1hwJL+i7RfXzJ3iqgtQnD7L8ZA1tHg/qND5wKBgDPbJHZErqFcGXSDbQ3cskzHWqpQ0vTOWuB4meGTzdkusn6vYuagQBEFgn7JwMbFSa3SUJJDU5/TjApCjvNhKN53LLrDeGHQPZZoapeAg4nWtufUo7f0HztBFWbo35fkVMxzLVH5+2+HC39wiJBPxAqWr5LdakQWe31yNHhrQrVlAoGAfCTinMtYUIyv2Cqi/GyJ/SuYYKqy/qYANwhc5GKIBLwMuN6r3hADeElMBbG7MlWwcdYytFp+IqDfTjnD2po7CoToh+Rg+K0Cm9GLW+Q9m3cyjzmtRSmmJtxNgBQaUdpc1xRm2hKguytFUfxnjGE/i3AP/xfNex2CbObkayXlXVcCgYBtX6O5r4KmS49Xg2tLJEKfuVZc4wELqIFozUxq01U/vzNDEpvApwr6i0NVyy3DwUJVmBv3f/rgN00nk3kwt6pBo2L/wCd3n9REsy62KGyMij4Bui8KGkCSs2Nd2Dzn7vlsjs6ILXIEDGgSqxn/k+vL0HVgyO83Y1wjHW31lqVLJQ==";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA8BufToOeN8VmPL6OCSU2qYV1Ai2Tw8L8sp7C9EbbZCpBc9FBIMpHQMiCvBGlds+aUJkBgBiR5FsByZ1o5RkPOi4EtbD9yeMfz2UzFWo0+3l6t732NOmrtWIDOfHO6gXuV6FiRP3UxjDrykOqKdGPQo8iiduvvpf2Q0fZrViAboDZIJJtaSl2kbRF4w+HhFT81XSHpAY0qTGWf/USWoVc0uNrMyF/WTM3AtqFvsXEh7ymKeMIBrXP4h6COpKoraJA1rlGMECRniIhUM5UrY2Btz4Xj5hBPRtMtn+ZKCU9lyPbXdlG0w4mdB2tTYbbB23jafXeWW09NiRIoT9FKJfAgwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8080/FruitsMark/order/notify_url";
/*
 * http://peqyx365.cn:8080/FruitsMark/order/notify_url
 * http://peqyx365.cn:8080/FruitsMark/order/return_url
 * */
	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/FruitsMark/order/return_url";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝沙箱网关（不同于支付宝网关，注意）
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

