package com.ruoyi.common.core.page;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

/**
 * 表格分页数据对象
 *
 * @author liuzhuoming
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableDataInfoT<T> {
    /**
     * 总记录数
     */
    private long total = 0;
    /**
     * 列表数据
     */
    private List<T> rows = Collections.emptyList();

    public TableDataInfoT(IPage<T> iPage) {
        this.total = iPage.getTotal();
        this.rows = iPage.getRecords();
    }
}
