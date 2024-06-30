package dw.gameshop_jwt.controller;

import dw.gameshop_jwt.dto.BaseResponse;
import dw.gameshop_jwt.dto.PurchaseDto;
import dw.gameshop_jwt.enumstatus.ResultCode;
import dw.gameshop_jwt.model.Purchase;
import dw.gameshop_jwt.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PurchaseController {
    @Autowired
    PurchaseService purchaseService;

    @PostMapping("/products/purchase")
    public ResponseEntity<BaseResponse<PurchaseDto>> savePurchase(@RequestBody Purchase purchase) {
        return new ResponseEntity<>(
                new BaseResponse(ResultCode.SUCCESS.name(),
                        purchaseService.savePurchase(purchase),
                        ResultCode.SUCCESS.getMsg())
                , HttpStatus.CREATED);
    }

    @PostMapping("/products/purchaselist")
    public ResponseEntity<BaseResponse<List<PurchaseDto>>> savePurchaseList(@RequestBody List<Purchase> purchaseList) {
        return new ResponseEntity<>(
                new BaseResponse(ResultCode.SUCCESS.name(),
                        purchaseService.savePurchaseList(purchaseList),
                        ResultCode.SUCCESS.getMsg())
                , HttpStatus.CREATED);
    }

    @GetMapping("/products/purchase")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<BaseResponse<List<PurchaseDto>>> getAllPurchases() {
        return new ResponseEntity<>(
                new BaseResponse(ResultCode.SUCCESS.name(),
                        purchaseService.getAllPurchases(),
                        ResultCode.SUCCESS.getMsg())
                , HttpStatus.OK);
    }

    @GetMapping("/products/purchase/id/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<BaseResponse<List<PurchaseDto>>> getPurchaseListByUser(@PathVariable String userId) {
        return new ResponseEntity<>(
                new BaseResponse(ResultCode.SUCCESS.name(),
                        purchaseService.getPurchaseListByUser(userId),
                        ResultCode.SUCCESS.getMsg())
                , HttpStatus.OK);
    }

    @GetMapping("/products/purchase/name/{userName}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<BaseResponse<List<PurchaseDto>>> getPurchaseListByUserName(
            @PathVariable String userName) {
        return new ResponseEntity<>(
                new BaseResponse(ResultCode.SUCCESS.name(),
                        purchaseService.getPurchaseListByUserName(userName),
                        ResultCode.SUCCESS.getMsg())
                , HttpStatus.OK);
    }

    @GetMapping("/products/purchase/current")
    public ResponseEntity<BaseResponse<List<PurchaseDto>>> getPurchaseListByCurrentUser() {
        return new ResponseEntity<>(
                new BaseResponse(ResultCode.SUCCESS.name(),
                        purchaseService.getPurchaseListByCurrentUser(),
                        ResultCode.SUCCESS.getMsg())
                , HttpStatus.OK);
    }
}









