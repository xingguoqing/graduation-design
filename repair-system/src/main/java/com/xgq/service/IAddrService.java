package com.xgq.service;

import com.xgq.dto.AddrDto;
import com.xgq.dto.PartDirectoryDto;
import com.xgq.po.AddrPo;
import dto.PageDto;
import dto.PageResultDto;

import java.util.List;

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

    AddrPo selAddrByid(Long id);

    String getCountryById(Long countryId);

    String getCityById(Long cityId);

    List<PartDirectoryDto> selAddrCountry();

    List<PartDirectoryDto> selAddrCity(Long countryId);

    List<AddrDto> selAddrs(Long userId);
}
