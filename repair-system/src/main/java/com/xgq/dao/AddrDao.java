package com.xgq.dao;

import com.xgq.dto.AddrDto;
import com.xgq.dto.PartDirectoryDto;
import com.xgq.mapper.IAddrMapper;
import com.xgq.po.AddrPo;
import dto.PageDto;
import dto.PageResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xingguoqing
 * @date 2018/2/14 上午9:51
 */
@Repository
public class AddrDao {

    @Autowired
    IAddrMapper addrMapper;

    public int selectCount(Long userId) {
        return addrMapper.selectCount(userId);
    }

    public List<AddrDto> selectPageList(Long userId, PageDto pageDto) {
        return addrMapper.selectPageList(userId,pageDto);
    }

    public AddrPo getAddrById(Long addrId) {
        return addrMapper.getAddrById(addrId);
    }

    public void updateDefaultById(Long addrId) {
        addrMapper.updateDefaultById(addrId);
    }

    public void cancleDefaultByUserId(Long userId) {
        addrMapper.cancleDefaultByUserId(userId);
    }

    public void delAddr(Long addrId) {
        addrMapper.delAddr(addrId);
    }

    public void updateAddrById(AddrPo addr) {
        addrMapper.updateAddrById(addr);
    }

    public void addAddr(AddrPo addrPo) {
        addrMapper.addAddr(addrPo);
    }

    public AddrPo selAddrByid(Long id) {
        return addrMapper.selAddrByid(id);
    }

    public String getCountryById(Long countryId) {
        return addrMapper.getCountryById(countryId);
    }

    public String getCityById(Long cityId) {
        return addrMapper.getCityById(cityId);
    }

    public List<PartDirectoryDto> selAddrCountry() {
        return addrMapper.selAddrCountry();
    }

    public List<PartDirectoryDto> selAddrCity(Long countryId) {
        return addrMapper.selAddrCity(countryId);
    }

    public List<AddrDto> selAddrs(Long userId) {
        return addrMapper.selAddrs(userId);
    }
}
