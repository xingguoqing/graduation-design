package com.xgq.service;

import com.xgq.po.AddrPo;
import dto.PageDto;
import dto.PageResultDto;

/**
 * @author xingguoqing
 * @date 2018/2/14 上午9:48
 */
public interface IAddrService {


    PageResultDto selPageAddrs(Long userId, PageDto pageDto);

    void setDefaultAddr(Long addrId);

    void delAddr(Long addrId);

    void updateAddr(AddrPo addrPo);

    void addAddr(AddrPo addrPo);
}
