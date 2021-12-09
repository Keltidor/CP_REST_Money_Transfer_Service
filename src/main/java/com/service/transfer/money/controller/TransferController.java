package com.service.transfer.money.controller;

import com.service.transfer.money.dto.ConfirmOperationDTO;
import com.service.transfer.money.dto.OperationDTO;
import com.service.transfer.money.dto.ResponseTransferDTO;
import com.service.transfer.money.entity.operation.Operation;
import com.service.transfer.money.exception.ErrorInputData;
import com.service.transfer.money.exception.ErrorTransfer;
import com.service.transfer.money.service.transfer.TransferServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.UUID;

@CrossOrigin(maxAge = 3600)
@RestController("/")
public class TransferController {

    private final TransferServiceImpl service;

    public TransferController(TransferServiceImpl service) {
        this.service = service;
    }

    @PostMapping("transfer")
    public ResponseTransferDTO transfer(@RequestBody @Valid OperationDTO operationDTO) {
        Operation operation = Operation.getOperationFromOperationDTO(operationDTO);
        UUID operationId = service.transfer(operation);
        return new ResponseTransferDTO(operationId);
    }

    @PostMapping("confirmOperation")
    public ResponseTransferDTO confirmOperation(@RequestBody ConfirmOperationDTO confirmOperationDTO) {
        UUID operationId = service.confirmOperation(confirmOperationDTO.getOperationId(), confirmOperationDTO.getCode());
        return new ResponseTransferDTO(operationId);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ErrorInputData.class)
    ResponseTransferDTO invalidCredentials(ErrorInputData e) {
        return new ResponseTransferDTO(e.getLocalizedMessage(), 0);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ErrorTransfer.class)
    ResponseTransferDTO unauthorizedUser(ErrorTransfer e) {
        return new ResponseTransferDTO(e.getLocalizedMessage(), 0);
    }
}
