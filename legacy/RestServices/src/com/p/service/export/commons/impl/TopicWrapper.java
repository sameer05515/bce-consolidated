package com.p.service.export.commons.impl;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.p.service.exception.RestServiceException;
import com.p.service.export.commons.Doer;
import com.p.service.pojo.Topic;
import com.p.sevice.common.DAOFactory;

public class TopicWrapper implements Doer {

	@Override
	public void create(String filePath) throws RestServiceException, IOException {

		List<Topic> list = new ArrayList<Topic>();

		list = DAOFactory.getTopicSessionInterface().getAll();

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type type = new TypeToken<List<Topic>>() {
		}.getType();
		String json = gson.toJson(list, type);
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
		Type type = new TypeToken<List<Topic>>() {
		}.getType();

		content = new String(Files.readAllBytes(Paths.get(filePath)));

		List<Topic> fromJson = gson.fromJson(content, type);

		for (Topic task : fromJson) {
			System.out.print(task.getId() + " , ");
			int i=DAOFactory.getTopicSessionInterface().create(task);
			System.out.println(i + " , ");
		}

	
	}

}
