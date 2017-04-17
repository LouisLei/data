package com.lakala.ipos.service.impl;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lakala.ipos.document.IposmgrDocument;
import com.lakala.ipos.document.IposmgrEventDocument;
import com.lakala.ipos.repository.IposmgrRepository;
import com.lakala.ipos.service.IposmgrService;
import com.lakala.ipos.util.GzipUtils;

/**
 * IPOS管家消息处理接口实现
 * 
 * @author liguangsheng
 * @date 2016-06-28
 */
@Service("iposmgrService")
public class IposmgrServiceImpl implements IposmgrService {
	private static final Logger logger = LoggerFactory.getLogger(IposmgrServiceImpl.class);

	@Autowired
	private IposmgrRepository repo;

	@Override
	public void transfer(String message) {
		try {
			JSONObject json = JSON.parseObject(message);
			logger.debug(json.getString("recvTime"));
			String dataPakGzip = json.getString("dataPak");
			String dataPak = GzipUtils.uncompressMessage(dataPakGzip);
			JSONArray dataArray = JSON.parseArray(dataPak);
			for (int i = 0; i < dataArray.size(); i++) {
				JSONObject data = dataArray.getJSONObject(i);
				String actionId = data.getString("actionId");
				String eventId = data.getString("eventId");

				IposmgrDocument doc = null;
				doc = repo.findByActionId(actionId);
				if (doc == null) {
					doc = JSON.toJavaObject(data, IposmgrDocument.class);
					doc.setSentTime(Timestamp.valueOf(json.getString("sentTime")));
					doc.setRecvTime(Timestamp.valueOf(json.getString("recvTime")));
					Timestamp current = new Timestamp(System.currentTimeMillis());
					doc.setCreateTime(current);
					doc.setUpdateTime(current);
					repo.insert(doc);
				} else {
					repo.updateUpdateTime(actionId);
				}

				data.put("timeStamp", Timestamp.valueOf(data.getString("timeStamp")));
				IposmgrEventDocument edoc = JSON.toJavaObject(data, IposmgrEventDocument.class);
				repo.addEvent(actionId, eventId, edoc);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.toString());
		}
	}

}
