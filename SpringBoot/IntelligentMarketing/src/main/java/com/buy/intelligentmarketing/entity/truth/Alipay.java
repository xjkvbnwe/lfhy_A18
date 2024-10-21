package com.buy.intelligentmarketing.entity.truth;

public class Alipay {
    // 作为身份标识的应用ID
    public static String app_id = "2021000122606028";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key  = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCC2HexjorIGdU0p2LRafml/Riy5XxSOQPns+7CV7Gtj6xdmBMQbpWhuLyg9v++KE4AYAcv05/czPsOf5d/3a24O7ur6r7PIX0mQQnljysCgYfmMWrrUDtjjxuWKKcqWXuHTnYIKYzHB9w03NmVhoHA4UFDMSabvZeMoB5qJNNDJLbFkvEn7Hy/pirdC1AyrzM/V4GpSPhpkFWcWujBeCgV6nhdCxw83wq1dnVhYV3leWwbzNB12Z/fhtaFzI/Xa16OhpdWpUh2mGEoI+HaNmVGXL6hPF/LohpFaeNATak9TRgV97lxoQDEASXExc6gNbjWxXCccJ45eWdoY4AXcYoDAgMBAAECggEAODjLNYAKIfD3q6hAzbq7ipc2BaiVwr687tJhxR/pOKqDZbPyfR0HLnYylveK7CplWcPpUqOcDUQGnDQ25Lpb6s2/1h8H3fiFzeokVn8t++Wsfzy8tF/FJQJY2Cyfp5LLgVg1X0x7MQovlIdQ9u7U0gv3/ucfr8vHUqV6bCFmpXJ8bv+QCIXHfNogl/iATNA9Pn8TkGGNjLkkEveBFcwph07L6WmhSPSpB6t9cLDzyPBmUFnFQKYFmKcMtRPObB0p3ROv9Lb1rfQRJ2W8dUyBMXsuPu6IPU7e6LdexMMO4Q1Q3YCGwUT2/c53aRdBY73Ja3uPfuFDp3MM+bA2lPIAAQKBgQD4R/7/qyVgwfi75ivb+VcrUh0i158FMES6OCZlBuncDnmHuk/DeyGIM+kjY9twBhRQQRcE7MBgVADBAt9LAACN3DG0HMK9gjTXKMhjNE6Hw2o85q6c2exReic63f1K4JIsjm7OksTkvuR6211Fg0Fxc3/rPk7HAzoZSceAypcWQwKBgQCG6dUdodDazamsw3HZ/m4Mcn9xS/HSzZkghIlHGUWTamuHZbxpwGCL2ThUD7g3PL0dl8zfE8KAI43jzRqecTPsrR4UBb/Ugo4FF+Bnth16nuhT0ZCa+2QSwumST2Nj2iWFUm5n8fQIy3Zdm0+2ilfw50yMZmqLfw8elehbwyzhQQKBgQCqDeYCh97aHwGW8VgpGucxrNR4AOfSTfVE9yCVId8b8m4bIOlxIu0ctTGCI+sxF3jiXzK9FMvPLro1eRon7KB0idUhYHGiC4TVugFGsZT4OEiT3gzLMhhC1j/4egP7PUXhbs1GgBCL/JPR8l44Nmakpk9/ePXMKdGpcHdNJ3XEsQKBgAcXgZlmKSlnSDUJrRFte3FyD1rwSAzxu8vmy6SOIocmP/LFGUthznFrgLeCgAd/TN2iXiWXz1CXDd1cqbqMbtNRIlShHL1aA4cnAyfQfeo1Mjc9u+E5t7/4uPh07d+Q1woUFDOol1BjCZzQDjelPAwwzGhQ9ebRTgUokidIsjJBAoGAeIl311cw2GD1v0d3i/oUlod9jNl1mjRtQPX6PCyq0igraxk36OFsFS282707dlPN9y0cFfGmqOnHZPzrFuSBS6+XJR1XM0QhFIBLUWeXOVRI30tcOSDGqds4BdQDEx8ObqQPGtQWYr2vsBCrJzpAIPO7ozekD5VXiFsTypZ72UM=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoM8/r5ByJXux9Zf98mhGRqPGJp1Ou82El3TfK4KahyrZ6x1wuwwwDq6fukDrr9ODgRis/7Fq5oAob87sPzCA17f6JZ+G/wJdvgA9YSoVqi04R8dsUwp0+zSbN+yPx7iqt9W/qcZXQ4iAnXQ57ITeQojnAKae2ehmh63SxkTclPGJgt7B5Xvgr0DZZBiSONQRYqS6VlkBiW+wGFlY4KFIS29CSQUcMZ/Mz1+/6Up7u+MHulg/J7QfBqfggykivOrWa3gwUdtkLvx5uRE5zhEH3ggYM4L8XAwiYn63D0aaPL/RSeMfNPHEuuqZs7+BFEeBraYBS9ntE/BsBEjQ+F4acwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://www.dream-y.top:8888/Applies/payResult";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://www.baidu.com";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
}
