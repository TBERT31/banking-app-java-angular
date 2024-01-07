package com.tbert31.banking.services;

import com.tbert31.banking.dto.TransactionDto;
import com.tbert31.banking.models.Transaction;

import java.util.List;

public interface TransactionService extends AbstractService<TransactionDto> {

    List<TransactionDto> findAllByUserId(Integer userId);
}
