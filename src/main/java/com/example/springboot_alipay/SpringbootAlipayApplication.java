package com.example.springboot_alipay;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.example.springboot_alipay.config.AliPayConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootAlipayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAlipayApplication.class, args);
    }

    @Bean
    public AlipayClient alipayClient(){
        return new DefaultAlipayClient(AliPayConfig.URL,
                AliPayConfig.APPID,
                AliPayConfig.RSA_PRIVATE_KEY,
                AliPayConfig.FORMAT,
                AliPayConfig.CHARSET,
                AliPayConfig.ALIPAY_PUBLIC_KEY,
                AliPayConfig.SIGNTYPE);
    }



}
