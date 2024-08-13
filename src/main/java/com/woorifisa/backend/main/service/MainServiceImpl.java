package com.woorifisa.backend.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woorifisa.backend.common.dto.PaymentDTO;
import com.woorifisa.backend.main.repository.MainRepository;

import jakarta.transaction.Transactional;

@Service
public class MainServiceImpl implements MainService {

    @Autowired
    private MainRepository mainRepository;

    // private ModelMapper modelMapper = new ModelMapper();

    @Override
    @Transactional
    public String insertCard(PaymentDTO dto) {
        int result = mainRepository.insertCard(dto.getMemNum(), dto.getPayCard(), dto.getPayExp(),
                dto.getPayCvc(), dto.getPayPw());

        if(result == 1){
            return "payment insert success";
        }
        return "payment insert fail";
    }
}
