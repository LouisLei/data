package com.lakala.ipos.service.impl;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lakala.ipos.document.DevinfoDocument;
import com.lakala.ipos.repository.DevinfoRepository;
import com.lakala.ipos.service.DevinfoService;
import com.lakala.ipos.util.GzipUtils;

/**
 * 设备信息消息处理接口实现
 * 
 * @author liguangsheng
 * @date 2016-06-28
 */
@Service("devinfoService")
public class DevinfoServiceImpl implements DevinfoService {
	private static final Logger logger = LoggerFactory.getLogger(DevinfoServiceImpl.class);

	@Autowired
	private DevinfoRepository repo;

	@Override
	public void transfer(String message) {
		try {
			JSONObject json = JSON.parseObject(message);
			logger.debug(json.getString("recvTime"));
			String dataPakGzip = json.getString("dataPak");
			String dataPak = GzipUtils.uncompressMessage(dataPakGzip);
			JSONArray dataArray = JSON.parseArray(dataPak);

			for (int i = 0; i < dataArray.size(); i++) {
				JSONObject obj = dataArray.getJSONObject(i);

				DevinfoDocument doc = JSON.toJavaObject(obj, DevinfoDocument.class);
				doc.setSn(json.getString("serialNo"));
				doc.setSentTime(Timestamp.valueOf(json.getString("sentTime")));
				doc.setRecvTime(Timestamp.valueOf(json.getString("recvTime")));
				Timestamp current = new Timestamp(System.currentTimeMillis());
				doc.setCreateTime(current);
				doc.setUpdateTime(current);

				repo.insert(doc);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.toString());
		}
	}
}
