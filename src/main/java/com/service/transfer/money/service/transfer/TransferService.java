package com.service.transfer.money.service.transfer;

import com.service.transfer.money.entity.operation.Operation;

import java.util.UUID;

public interface TransferService {
    UUID transfer(Operation operation);

    UUID confirmOperation(String operationId, String code);
}
