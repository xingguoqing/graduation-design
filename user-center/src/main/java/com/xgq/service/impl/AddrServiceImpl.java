package com.xgq.service.impl;

import com.xgq.dao.AddrDao;
import com.xgq.dao.PartDirectoryDao;
import com.xgq.dto.AddrDto;
import com.xgq.dto.PartDirectoryDto;
import com.xgq.errorcode.AddrErrorCode;
import com.xgq.po.AddrPo;
import com.xgq.po.PartDirectoryPo;
import com.xgq.service.IAddrService;
import dto.PageDto;
import dto.PageResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.exception.BusinessRuntimeException;

import java.util.List;

/**
 * @author xingguoqing
 * @date 2018/2/14 上午9:50
 */
@Service
public class AddrServiceImpl implements IAddrService{

    @Autowired
    AddrDao addrDao;


    @Autowired
    PartDirectoryDao partDirectoryDao;


    @Override
    public PageResultDto selPageAddrs(Long userId, PageDto pageDto) {
        int count = addrDao.selectCount(userId);
        List<AddrDto> addrDtos = addrDao.selectPageList(userId, pageDto);
        PageResultDto pageResultDto = new PageResultDto();
        pageResultDto.setTotal(count);
        pageResultDto.setRows(addrDtos);
        return pageResultDto;
    }

    @Override
    @Transactional
    public void setDefaultAddr(Long addrId) {
        AddrPo addrPo = addrDao.getAddrById(addrId);
        if(addrPo == null){
            BusinessRuntimeException.wrapBusiException(AddrErrorCode.ADDR_NOT_EXIST);
        }
        Long userId = addrPo.getUserId();
        addrDao.cancleDefaultByUserId(userId);
        addrDao.updateDefaultById(addrId);
    }

    @Override
    public void delAddr(Long addrId) {
        AddrPo addrPo = addrDao.getAddrById(addrId);
        if(addrPo == null){
            BusinessRuntimeException.wrapBusiException(AddrErrorCode.ADDR_NOT_EXIST);
        }
        addrDao.delAddr(addrId);
    }

    @Override
    public void updateAddr(AddrPo addrPo) {
        AddrPo addr = addrDao.getAddrById(addrPo.getId());
        if(addr == null){
            BusinessRuntimeException.wrapBusiException(AddrErrorCode.ADDR_NOT_EXIST);
        }
        PartDirectoryPo poCountry = partDirectoryDao.getNameById(addrPo.getCountryId());
        if(poCountry == null){
            BusinessRuntimeException.wrapBusiException(AddrErrorCode.COUNTRY_NOT_EXIST);
        }
        PartDirectoryPo poCity = partDirectoryDao.getNameById(addrPo.getCityId());
        if(poCity == null){
            BusinessRuntimeException.wrapBusiException(AddrErrorCode.CITY_NOT_EXIST);
        }
        if(!poCity.getParentId().equals(poCountry.getId())){
            BusinessRuntimeException.wrapBusiException(AddrErrorCode.COUNTRY_CITY_NOT_MACH);
        }
        addrDao.updateAddrById(addrPo);

    }

    @Override
    public void addAddr(AddrPo addrPo) {
        PartDirectoryPo poCountry = partDirectoryDao.getNameById(addrPo.getCountryId());
//        if(poCountry == null){
//            BusinessRuntimeException.wrapBusiException(AddrErrorCode.COUNTRY_NOT_EXIST);
//        }
//        PartDirectoryPo poCity = partDirectoryDao.getNameById(addrPo.getCityId());
//        if(poCity == null){
//            BusinessRuntimeException.wrapBusiException(AddrErrorCode.CITY_NOT_EXIST);
//        }
////        if(!poCity.getParentId().equals(poCountry.getId())){
////            BusinessRuntimeException.wrapBusiException(AddrErrorCode.COUNTRY_CITY_NOT_MACH);
////        }
        addrDao.addAddr(addrPo);
    }

    @Override
    public AddrPo selAddrByid(Long id) {
        return addrDao.selAddrByid(id);
    }

    @Override
    public String getCountryById(Long countryId) {
        return addrDao.getCountryById(countryId);
    }

    @Override
    public String getCityById(Long cityId) {
        return addrDao.getCityById(cityId);
    }

    @Override
    public List<PartDirectoryDto> selAddrCountry() {
        return addrDao.selAddrCountry();
    }

    @Override
    public List<PartDirectoryDto> selAddrCity(Long countryId) {
        return addrDao.selAddrCity(countryId);
    }

    @Override
    public List<AddrDto> selAddrs(Long userId) {
        return addrDao.selAddrs(userId);
    }
}
