package com.woorifisa.backend.common.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.woorifisa.backend.common.dto.PaymentDTO;
import com.woorifisa.backend.common.dto.ProductDTO;
import com.woorifisa.backend.common.dto.ReviewDTO;
import com.woorifisa.backend.common.dto.SubscriptionDTO;
import com.woorifisa.backend.common.entity.Payment;
import com.woorifisa.backend.common.entity.Product;
import com.woorifisa.backend.common.entity.Review;
import com.woorifisa.backend.common.entity.Subscription;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Product와 ProductDTO 간의 매핑 규칙
        modelMapper.addMappings(new PropertyMap<Product, ProductDTO>() {
            @Override
            protected void configure() {
                map(source.getMemNum().getMemNum(), destination.getMemNum());
                // 추가적인 매핑 규칙 필요 시 추가
            }
        });

        // Payment와 PaymentDTO 간의 매핑 규칙
        modelMapper.addMappings(new PropertyMap<Payment, PaymentDTO>() {
            @Override
            protected void configure() {
                map(source.getMemNum().getMemNum(), destination.getMemNum());
                // 추가적인 매핑 규칙 필요 시 추가
            }
        });

        // Subscription와 SubscriptionDTO 간의 매핑 규칙
        modelMapper.addMappings(new PropertyMap<Subscription, SubscriptionDTO>() {
            @Override
            protected void configure() {
                map(source.getMemNum().getMemNum(), destination.getMemNum());
                map(source.getProdNum().getProdNum(), destination.getProdNum());
                map(source.getPayNum().getPayNum(), destination.getPayNum());
                // 추가적인 매핑 규칙 필요 시 추가
            }
        });

        // Review와 ReviewDTO 간의 매핑 규칙
        modelMapper.addMappings(new PropertyMap<Review, ReviewDTO>() {
            @Override
            protected void configure() {
                map(source.getMemNum().getMemNum(), destination.getMemNum());
                map(source.getProdNum().getProdNum(), destination.getProdNum());
            }
        });

        return modelMapper;
    }
}
