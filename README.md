# SpringBoot-AliPay
一、简单了解
我们这节来搞一下第三方支付。面试的时候都会问一些关于第三方支付的问题。其实实现代码没多少，更多的是业务场景和支付宝的支付流程。
这是手机网站支付产品介绍https://docs.open.alipay.com/203
1、用户点击下单按钮，触发form表单或ajax表单触动请求。
2、商户系统生成订单信息并提交给支付宝(请求支付宝支付)
3、提交后支付宝会自动唤醒手机里的支付宝客户端(没有支付宝就会网页支付)
4、支付成功后支付宝会跳转到商户系统，验证是否支付成功并显示给用户订单信息。
流程图：

(由于水印挡住了框框里的字，我就在上边写了一遍-.-.画图经验不足，勿喷。)
二、小试牛刀
首先我们要接入支付宝支付肯定需要一个开发者账号。当然，没苹果那么复杂。自己的支付宝账号就可以。(关于如何注册的流程请看楼上的文章)
首先引入支付宝pom文件
    com.alipay.sdk    alipay-sdk-java    3.0.1

AliPayConfig类

package com.example.springboot_alipay.config;

/**
 * @author: MrWang
 * @date: 2018/9/14
 */


public class AliPayConfig {
    // 商户appid
    public static String APPID = "填写您的APPID";
    // 私钥 pkcs8格式的
    public static String RSA_PRIVATE_KEY = "MIeeeIEvQ11IBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCw00y/iz0Xzn/yrwUCEti9QOafx1fxkHt19I9xY9RDSFOjwWSTx0VhleQYrOBe8OVDSZimtLvpli+Dj+dKY0x92CwfZUV9ZUagRui6GHN9aNGyZcM2H8v5ISx0xJ4+lxuvcD+slB7BSnOl4gBTaTzoYyfBU4z/bojpOEky5A9/DFWHM+A6DT6sSy/hGaWJnNGsXYpXvZ8qrNWU8bggWEx8YhnBB7Hh/bYZls+uOIYVjujAx7D15P6CbENcoV9aqQRKgkneGzvWq2tY40ONTfypcHZiex2jf7J9MkRH7beWfsG0cBqudQXnaCOUdpYLYfm7qBPjgOJ0Hhm/GxoV/yEBAgMBAAECggEACIEhH9VCivKTlMh2JTNi2TdV4DlmMWgRiArLG1kBzHqeCzIx9CmRXLAo2Eb4Q5iybhoMDXiD0Sg4AMvbA+jitOrAtIHaZb2JOAwxBUJHy7BO5ZWfXesJlGHUEYmjUr9RxY5QgaxeDk3WYhQMj/Zvx319W/74uRBH5W+flaMCW8nXlH/YohcjadWbXkTp2f/JmizNtHlln35E4oAVFIFgzFM4Hu/457J/RrPRHWelaK2NoHo7Ik/SH+YyaKXI3h3UtoBI22NP0LjN3HzE8Fas+4DbseDvaBQcguNOECNI3UzfOaixnTMif32Leij2q0id6dXbCL2x3PqOh5FpeYTRQQKBgQDgcEdzAuekHWEbj6rtH3zNVpw3ZrE65ZvWg9wM+sUse0ohs1sIN6DYx5C/2cscPeEclIajYLmMCGxlFebKlvxp5+4XDN933JTWveJMcjpsLBglcVP5LM5/TgyloQdb2vKoD2IM8jqdVf/yht8+zQb7bta8o0NNLlMQO8/vhICivwKBgQDJsPXBhm86URsJm3sORPTXV9V50pVRWtd2jrtjTVWePdfuyCTg67X2kCXqC5gRc2CdjRPtYEzfqBr4XCedi0CD8R3/MGhWfe+19meCYueZaLp+M7NiG3Em65aZLKNaYrdD/2BOOzFWKXGyYGXkixVfkBXRhGea5ziPMCF/cnHsPwKBgQCIAwMJq+T9C+Dhdvs6oC5dsfMRj+Yvrx31rgsYwo/jGQQf0DWGkU+kj6wk17PC9a0KSOge8mr/dctapx8p/r4Q4EDlLyPt23tHd+NAOKk3DLHFXxxokMLDW2VGqoNvIwyS1F+zQMhMuOVW8G0x/cYRcWYpAzex+jTUTasBl+bsuQKBgFlMGALO2oAfWLEwPZNbxGtzX/jH24Rhi/PZze1xV+7vwqLdyjYrJi08NQ0ihPl089nNlH0MnfyIGSjM5F1EQhQotPc/H3Lr3y3ZIor/7zpku+0URt+w9rAcrlizAsAJ5MoeTy1T18wmHUi0lWhSi+v7hy10ScqzhL8guDQNH5NRAoGAGwNPLgMJ2Ab6lcvLJ0VYbWUx+RQe8m/an2Lcrvr9v2hqH1WEwYG7SmYl2czUycBu/TBipltaRpzL7k5owju9hoXn9e6UeYGc76b8gp3RzhHWz3lYPgmw83gG6shJfR/Hib4JWIsGfGU7D58xY1UpbFxxjFw4rp9Gjz5VWYwtbaE=";
    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://wgkkd7.natappfree.cc/alipay/notify";
    // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    public static String return_url = "http://wgkkd7.natappfree.cc/alipay/web/returnUrl";
    // 请求网关地址
    public static String URL = "https://openapi.alipaydev.com/gateway.do";
    // 编码
    public static String CHARSET = "UTF-8";
    // 返回格式
    public static String FORMAT = "json";
    // 支付宝公钥
    public static String ALIPAY_PUBLIC_KEY = "eeMIIB2121IjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtCC8/qNbkHzCfaYaiBRw6/grSYnUN8b1JUmJm3OshLoVSEJS7rhuFeRlahqp1sDRsHoU+7ndfqgDDtR9FoCSwniyVx1NbXRJEqh3patoWHcVekJ3/FRt/YkpdBo+x9VMvFmD1DEJrRtVoVn+E307JJZjgfXVN4ZP93ufnRBH8eIMLUXwjI+lu2qG+tXnP4pcvxzTYiqFqXlOnk2zswb2chchpmB631RzbHh6ParLRiUowWQjp9lF0R65JIlNtII+q6c2qiPia86CEoPrfqR9ZvRYzdDoYHbzMuAZfOkcEqyIN6LKlRS7l/FYReWRN2ZAPIz0pM5YKqQxeyB1IFR4UQIDAQAB";
    // 日志记录目录
    public static String log_path = "/log";
    // RSA2
    public static String SIGNTYPE = "RSA2";
}



TemplatesConfig类
com.example.springboot_alipay.config;

org.springframework.context.annotation.;
org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

TemplatesConfig WebMvcConfigurerAdapter {

    addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler().addResourceLocations();
    }
}


AliPayController类(支付类)
package com.example.springboot_alipay.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeCloseModel;
import com.alipay.api.domain.AlipayTradeFastpayRefundQueryModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeCloseRequest;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.example.springboot_alipay.config.AliPayConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

/**
 * @author: MrWang
 * @date: 2018/9/14
 */

@Controller
@RequestMapping("/alipay")
public class AliPayController {
    @Autowired
    private AlipayClient alipayClient;

    @RequestMapping("/")
    public String index() {
        return "/index";
    }


    @PostMapping("/alipage")
    public void gotoPayPage(HttpServletResponse response) throws AlipayApiException, IOException {
        String productCode = "QUICK_WAP_WAY";
        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
        model.setOutTradeNo(UUID.randomUUID().toString());
        model.setSubject("IPhoneXsMax");
        model.setTotalAmount("9958");
        model.setBody("支付测试，共9958元");
        model.setTimeoutExpress("2m");
        model.setProductCode(productCode);
        AlipayTradeWapPayRequest wapPayRequest = new AlipayTradeWapPayRequest();
        wapPayRequest.setReturnUrl(AliPayConfig.return_url);
        wapPayRequest.setNotifyUrl(AliPayConfig.notify_url);
        wapPayRequest.setBizModel(model);
        // 调用SDK生成表单, 并直接将完整的表单html输出到页面
        String form = alipayClient.pageExecute(wapPayRequest).getBody();
        System.out.println(form);
        response.setContentType("text/html;charset=" + AliPayConfig.CHARSET);
        response.getWriter().write(form);
        response.getWriter().flush();
        response.getWriter().close();


    }

    /**
     * 支付宝页面跳转同步通知页面
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     * @throws AlipayApiException
     */
    @RequestMapping("/web/returnUrl")
    public String returnUrl(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, AlipayApiException, UnsupportedEncodingException {
        response.setContentType("text/html;charset=" + AliPayConfig.CHARSET);

        //获取支付宝GET过来反馈信息
        Map params = new HashMap<>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean verifyResult = AlipaySignature.rsaCheckV1(params, AliPayConfig.ALIPAY_PUBLIC_KEY, AliPayConfig.CHARSET, AliPayConfig.SIGNTYPE);
        if (verifyResult) {
            //验证成功
            //请在这里加上商户的业务逻辑程序代码，如保存支付宝交易号
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

            return "wapPaySuccess";

        } else {
            return "wapPayFail";

        }
    }

    /**
     * 退款
     * @param orderNo 商户订单号
     * @return
     * @throws AlipayApiException
     */

    @PostMapping("/refund")
    @ResponseBody
    public String refund(String orderNo) throws AlipayApiException {
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();

        AlipayTradeRefundModel model = new AlipayTradeRefundModel();
        // 商户订单号
        model.setOutTradeNo(orderNo);
        // 退款金额
        model.setRefundAmount("0.01");
        // 退款原因
        model.setRefundReason("无理由退货");
        // 退款订单号(同一个订单可以分多次部分退款，当分多次时必传)
//        model.setOutRequestNo(UUID.randomUUID().toString());
        alipayRequest.setBizModel(model);

        AlipayTradeRefundResponse alipayResponse = alipayClient.execute(alipayRequest);
        System.out.println(alipayResponse.getBody());

        return alipayResponse.getBody();
    }

    /**
     * 退款查询
     * @param orderNo       商户订单号
     * @param refundOrderNo 请求退款接口时，传入的退款请求号，如果在退款请求时未传入，则该值为创建交易时的外部订单号
     * @return
     * @throws AlipayApiException
     */
    @GetMapping("/refundQuery")
    @ResponseBody
    public String refundQuery(String orderNo, String refundOrderNo) throws AlipayApiException {
        AlipayTradeFastpayRefundQueryRequest alipayRequest = new AlipayTradeFastpayRefundQueryRequest();

        AlipayTradeFastpayRefundQueryModel model = new AlipayTradeFastpayRefundQueryModel();
        model.setOutTradeNo(orderNo);
        model.setOutRequestNo(refundOrderNo);
        alipayRequest.setBizModel(model);
        AlipayTradeFastpayRefundQueryResponse alipayResponse = alipayClient.execute(alipayRequest);
        System.out.println(alipayResponse.getBody());
        return alipayResponse.getBody();
    }

    /**
     * 关闭交易
     * @param orderNo
     * @return
     * @throws AlipayApiException
     */
    @PostMapping("/close")
    @ResponseBody
    public String close(String orderNo) throws AlipayApiException {
        AlipayTradeCloseRequest alipayRequest = new AlipayTradeCloseRequest();
        AlipayTradeCloseModel model = new AlipayTradeCloseModel();
        model.setOutTradeNo(orderNo);
        alipayRequest.setBizModel(model);
        AlipayTradeCloseResponse alipayResponse = alipayClient.execute(alipayRequest);
        System.out.println(alipayResponse.getBody());
        return alipayResponse.getBody();
    }
}

AilPayServiceController类(通用类)
package com.example.springboot_alipay.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.example.springboot_alipay.config.AliPayConfig;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author: MrWang
 * @date: 2018/9/14
 */

@RequestMapping("/alipay")
public class AilPayServiceController {
    /**
     * 支付异步通知
     *
     * https://docs.open.alipay.com/194/103296
     */
    @RequestMapping("/notify")
    public String notify(HttpServletRequest request) throws AlipayApiException, UnsupportedEncodingException {
        // 一定要验签，防止黑客篡改参数
        Map parameterMap = request.getParameterMap();
        StringBuilder notifyBuild = new StringBuilder("/****************************** alipay notify ******************************/\n");
        parameterMap.forEach((key, value) -> notifyBuild.append(key + "=" + value[0] + "\n") );
        //log.info(notifyBuild.toString());

        // https://docs.open.alipay.com/54/106370
        // 获取支付宝POST过来反馈信息
        Map params = new HashMap<>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }

        boolean flag = AlipaySignature.rsaCheckV1(params, AliPayConfig.ALIPAY_PUBLIC_KEY, AliPayConfig.CHARSET,AliPayConfig.SIGNTYPE);

        if (flag) {
            /**
             * TODO 需要严格按照如下描述校验通知数据的正确性
             *
             * 商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
             * 并判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
             * 同时需要校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
             *
             * 上述有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。
             * 在上述验证通过后商户必须根据支付宝不同类型的业务通知，正确的进行不同的业务处理，并且过滤重复的通知结果数据。
             * 在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。
             */

            //交易状态
            String tradeStatus = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

            // TRADE_FINISHED(表示交易已经成功结束，并不能再对该交易做后续操作);
            // TRADE_SUCCESS(表示交易已经成功结束，可以对该交易做后续操作，如：分润、退款等);
            if(tradeStatus.equals("TRADE_FINISHED")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，
                // 并判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），并执行商户的业务程序
                //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //如果签约的是可退款协议，退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
                //如果没有签约可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。
            } else if (tradeStatus.equals("TRADE_SUCCESS")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，
                // 并判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），并执行商户的业务程序
                //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //如果签约的是可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。

            }

            return "success";
        }

        return "fail";
    }

}

SpringbootAlipayApplication(启动类)

com.example.springboot_alipay;

com.alipay.api.AlipayClient;
com.alipay.api.DefaultAlipayClient;
com.example.springboot_alipay.config.AliPayConfig;
org.springframework.boot.SpringApplication;
org.springframework.boot.autoconfigure.;
org.springframework.context.annotation.;

SpringbootAlipayApplication {

    main(String[] args) {
        SpringApplication.(SpringbootAlipayApplication., args);
    }

    AlipayClient alipayClient(){
        DefaultAlipayClient(AliPayConfig.,
                AliPayConfig.,
                AliPayConfig.,
                AliPayConfig.,
                AliPayConfig.,
                AliPayConfig.,
                AliPayConfig.);
    }



}


大部分代码都是从Demo上摘下来的。复制上就能用。
