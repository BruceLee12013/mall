package com.jayden.mall.model.vo;

import com.jayden.mall.model.pojo.OmsOrder;
import com.jayden.mall.model.pojo.OmsOrderItem;
import com.jayden.mall.model.pojo.OmsOrderOperateHistory;

import java.util.List;

public class OmsOrderDetail extends OmsOrder {
    private List<OmsOrderItem> orderItemList;
    private List<OmsOrderOperateHistory> historyList;

    public List<OmsOrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OmsOrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public List<OmsOrderOperateHistory> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<OmsOrderOperateHistory> historyList) {
        this.historyList = historyList;
    }
}
