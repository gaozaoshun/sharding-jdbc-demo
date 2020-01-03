package com.gfs.shardingjdbcdemo.config;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

@Slf4j
public class CreateDatePreciseShardingAlgorithm implements PreciseShardingAlgorithm<String> {

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
    /**
     * Sharding.
     *
     * @param availableTargetNames available data sources or tables's names
     * @param shardingValue        sharding value  uc_sys_evaluationlog_201901
     * @return sharding result for data source or table's name
     */
    @SneakyThrows
    @Override
    public String doSharding(final Collection<String> availableTargetNames, final PreciseShardingValue<String> shardingValue) {
        log.info("availableTargetNames:{}", JSON.toJSONString(availableTargetNames));
        log.info("shardingValue:{}", JSON.toJSONString(shardingValue));
        Date date = DateUtils.parseDate(shardingValue.getValue(),"yyyy-MM-dd HH:mm:ss");
        String realDate = sdf.format(date);
        final String tableName = shardingValue.getLogicTableName();
        final String split = "_";
        return tableName + split + realDate;
    }
}
