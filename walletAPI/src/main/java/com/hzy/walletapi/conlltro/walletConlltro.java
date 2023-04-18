package com.hzy.walletapi.conlltro;

import com.hzy.walletapi.entity.Wallet;
import com.hzy.walletapi.entity.WalletRecord;
import com.hzy.walletapi.service.WalletRecordService;
import com.hzy.walletapi.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @title: walletConlltro
 * @Author zxwyhzy
 * @Date: 2023/4/17 15:36
 * @Version 1.0
 */

@RestController
@RequestMapping("/wallet")
public class walletConlltro {

    @Autowired
    WalletService walletService;

    @Autowired
    WalletRecordService walletRecordService;


    /**
     * 查询余额
     * @return 用户余额
     */
    @GetMapping("/{id}")
    public Result test01(@PathVariable("id") int userId){

      return  walletService.getUserWalletBalance(userId);
    }

    /**
     * 消费100
     * @return 消费是否成功
     */
    @PostMapping("/test02")
    public Result test02(@RequestBody Wallet wallet){
        return walletService.userPurchase(wallet.getUserId(),wallet.getBalance());
    }

    /**
     * 退款20
     * @return 是否成功
     */
    @PostMapping("/test03")
    public Result test03(@RequestBody WalletRecord walletRecord){
        return walletService.userRefund(walletRecord.getUserId(),walletRecord.getAmount(),walletRecord.getId());
    }

    @GetMapping("/test04")
    public Result test03(int id){
        return walletRecordService.getUserTransactionHistory(id);
    }
}
