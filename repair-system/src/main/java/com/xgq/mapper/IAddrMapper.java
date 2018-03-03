package com.xgq.mapper;

import com.xgq.dto.AddrDto;
import com.xgq.dto.PartDirectoryDto;
import com.xgq.po.AddrPo;
import dto.PageDto;
import dto.PageResultDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xingguoqing
 * @date 2018/2/14 上午9:52
 */
@Mapper
public interface IAddrMapper {


    int selectCount(@Param("userId") Long userId);

    List<AddrDto> selectPageList(@Param("userId") Long userId, @Param("page") PageDto pageDto);

    AddrPo getAddrById(@Param("addrId") Long addrId);

    void updateDefaultById(@Param("addrId") Long addrId);

    void cancleDefaultByUserId(@Param("userId") Long userId);

    void delAddr(@Param("addrId") Long addrId);

    void updateAddrById(@Param("addr") AddrPo addr);

    void addAddr(@Param("addr") AddrPo addrPo);

    AddrPo selAddrByid(@Param("addrId") Long id);

    String getCountryById(@Param("countryId") Long countryId);

    String getCityById(@Param("cityId") Long cityId);

    List<PartDirectoryDto> selAddrCountry();

    List<PartDirectoryDto> selAddrCity(@Param("countryId") Long countryId);

    List<AddrDto> selAddrs(@Param("userId") Long userId);
}
