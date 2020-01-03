package com.gfs.shardingjdbcdemo.config;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

@Slf4j
public class CreateDateRangeShardingAlgorithm implements RangeShardingAlgorithm<String> {

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");

    /**
     * Sharding.
     *
     * @param availableTargetNames available data sources or tables's names
     * @param shardingValue        sharding value
     * @return sharding results for data sources or tables's names
     */
    @SneakyThrows
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<String> shardingValue) {
        String lower = shardingValue.getValueRange().lowerEndpoint();
        String upper = shardingValue.getValueRange().upperEndpoint();
        Date lowerDate = DateUtils.parseDate(lower, "yyyy-MM-dd HH:mm:ss");
        Date upperDate = DateUtils.parseDate(upper, "yyyy-MM-dd HH:mm:ss");
        Date tempDate = lowerDate;
        Collection<String> result = Lists.newArrayList();
        final String tableName = shardingValue.getLogicTableName();
        final String spilt = "_";
        while (upperDate.after(tempDate)) {
            result.add(tableName + spilt + sdf.format(tempDate));
            tempDate = DateUtils.addMonths(tempDate, 1);
        }
        result.add(tableName + spilt + sdf.format(tempDate));
        return result;
    }
}
