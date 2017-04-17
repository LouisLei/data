package com.lakala.ipos.receivelog.service.impl;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lakala.ipos.appmalldata.service.AppmallDataService;
import com.lakala.ipos.common.util.Constants;
import com.lakala.ipos.common.util.FastjsonUtil;
import com.lakala.ipos.common.util.GzipUtils;
import com.lakala.ipos.devinfodata.service.DevinfoDataService;
import com.lakala.ipos.iposmanager.service.IposManagerDataService;
import com.lakala.ipos.paymentdata.service.PaymentDataService;
import com.lakala.ipos.receivelog.model.ReceivedLog;
import com.lakala.ipos.receivelog.service.CommonService;
import com.lakala.ipos.receivelog.service.ReceivedLogService;

@Service("mqCommonService")
public class MQServiceImpl implements CommonService {
	private static final Logger logger = LogManager.getLogger(MQServiceImpl.class);

	@Autowired
	@Qualifier("mysqlReceivedLogService")
	private ReceivedLogService receivedLogService;

	@Autowired
	@Qualifier("mysqlPaymentLogService")
	private PaymentDataService paymentLogService;

	@Autowired
	@Qualifier("mysqlAppmallLogService")
	private AppmallDataService appmallLogService;

	@Autowired
	@Qualifier("mysqlDeviceLogService")
	private DevinfoDataService devinfoDataService;

	@Autowired
	@Qualifier("mysqlIposManagerDataService")
	private IposManagerDataService iposManagerDataService;

	/**
	 * 功能: 1.解析各业务类型的json数组 2.将数据插入业务类型对应的表中
	 * 
	 * @param id
	 *            接收表的id
	 * @param resutlMap
	 *            业务类型对应的接送数组
	 * @throws Exception
	 */
	private void transferHandler(Long id, Map<String, String> resutlMap, String sn) throws Exception {
		if (resutlMap == null || resutlMap.size() <= 0)
			return;
		for (Entry<String, String> entry : resutlMap.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			switch (key) {
			case Constants.DEVINFO_CODE:// 基础设备信息数据转换
				devinfoDataService.transferDeviceLog(value, id, sn);
				break;
			case Constants.PAYMENT_CODE:// 收单数据转换
				paymentLogService.transferPaymentLog(value, id, sn);
				break;
			case Constants.APPMALL_CODE:// 应用商城数据转换
				appmallLogService.transferAppmallLog(value, id, sn);
				break;
			case Constants.MANAGER_CODE:// IPOS管家数据转换
				iposManagerDataService.transferIposManagerLog(value, id, sn);
				break;
			default:
				break;
			}
		}
	}

	/**
	 * 功能: 1.解析消息json字符串，并将其插入接收表中 2.将dataPak解压缩成json字符串
	 * 3.提取json串中各业务类型的数据，并插入业务类型对应的表中
	 * 
	 * @param message
	 *            接收的压缩包
	 * @throws Exception
	 */
	@Override
	public void transfer(String message) {
		try {
			logger.info("数据处理中...");
			FastjsonUtil<String> fastjsonUtil = new FastjsonUtil<String>(String.class);
			ReceivedLog receivedLog = receivedLogService.transferReceivedLog(message);
			if (receivedLog == null) {
				return;
			}
			String dataPkgGzip = receivedLog.getDataPak();
			if (dataPkgGzip == null || "".equalsIgnoreCase(dataPkgGzip)) {
				return;
			}
//			logger.info("未解压缩后的数据："+dataPkgGzip);//测试：打印解压缩数据
			String dataPkg = GzipUtils.uncompressMessage(dataPkgGzip);
//			logger.info("解压缩后的数据："+dataPkg);//测试：打印解压缩数据
			Map<String, String> resutlMap = fastjsonUtil.parseChildJsonToMap(dataPkg);// 解析Json数组字符串
			transferHandler(receivedLog.getId(), resutlMap, receivedLog.getSerialNo());
		} catch (Exception e) {
			logger.error("异常信息", e);
		} finally {
			logger.info("数据处理完成！");
		}
	}

}
