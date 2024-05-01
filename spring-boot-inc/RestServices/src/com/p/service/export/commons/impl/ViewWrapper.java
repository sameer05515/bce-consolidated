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
import com.p.service.pojo.View;
import com.p.sevice.common.DAOFactory;

public class ViewWrapper implements Doer {

	@Override
	public void create(String filePath) throws RestServiceException, IOException {
		List<View> list = new ArrayList<View>();

		list = DAOFactory.getViewSessionInterface().getAll();

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type type = new TypeToken<List<View>>() {
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
		Type type = new TypeToken<List<View>>() {
		}.getType();

		content = new String(Files.readAllBytes(Paths.get(filePath)));

		List<View> fromJson = gson.fromJson(content, type);

		for (View task : fromJson) {
			System.out.print(task.getId() + " , ");
			int i=DAOFactory.getViewSessionInterface().create(task);
			System.out.println(i + " , ");
		}

	}

}
