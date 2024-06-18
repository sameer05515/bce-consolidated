package com.p.service.export.commons.impl;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.p.service.exception.RestServiceException;
import com.p.service.export.commons.Doer;
import com.p.service.pojo.TopicGroupRelation;
import com.p.service.vo.TopicGroupRelationResourceVO;
import com.p.sevice.common.DAOFactory;

public class TopicGroupWrapper implements Doer {

	@Override
	public void create(String filePath) throws RestServiceException, IOException {
		List<TopicGroupRelation> list = new ArrayList<TopicGroupRelation>();

		list = DAOFactory.getTopicGroupRelationSessionInterface().getAll();
		List<TopicGroupRelationResourceVO> listTopicGroupRelationResourceVO = new LinkedList<TopicGroupRelationResourceVO>();
		Set<Integer> topicIdSet = new HashSet<Integer>();
		for (TopicGroupRelation tg : list) {
			topicIdSet.add(tg.getTopics().getId());
		}

		for (int topicId : topicIdSet) {
			TopicGroupRelationResourceVO obj = new TopicGroupRelationResourceVO();
			obj.getTopicIdList().add(topicId);
			for (TopicGroupRelation tg : list) {
				if (topicId == tg.getTopics().getId()) {
					obj.getGroupIdList().add(tg.getGroups().getId());
				}
			}
			listTopicGroupRelationResourceVO.add(obj);
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type type = new TypeToken<List<TopicGroupRelationResourceVO>>() {
		}.getType();
		String json = gson.toJson(listTopicGroupRelationResourceVO, type);
		System.out.println(json);

		Files.write(Paths.get(filePath), json.getBytes(), StandardOpenOption.CREATE,
				StandardOpenOption.TRUNCATE_EXISTING);

	}

	@Override
	public void consume(String filePath) throws RestServiceException, IOException {
		System.out.println(
				"#######################################################################################################");

		String content = "";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type type = new TypeToken<List<TopicGroupRelationResourceVO>>() {
		}.getType();

		content = new String(Files.readAllBytes(Paths.get(filePath)));

		List<TopicGroupRelationResourceVO> fromJson = gson.fromJson(content, type);

		for (TopicGroupRelationResourceVO tg : fromJson) {
//			System.out.print(task.getId() + " , ");
//			List<Integer> topicList = new LinkedList<Integer>();
//			topicList.add(task.getTopics().getId());
//			List<Integer> groupList = new LinkedList<Integer>();
//			groupList.add(task.getGroups().getId());
			int i = DAOFactory.getTopicGroupRelationSessionInterface().addTopicsToGroups(tg.getTopicIdList(), tg.getGroupIdList());
			System.out.println(i + " , ");
		}

	}

}
