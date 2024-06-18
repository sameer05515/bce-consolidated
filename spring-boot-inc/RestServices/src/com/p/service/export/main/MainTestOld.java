package com.p.service.export.main;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.p.service.exception.RestServiceException;
import com.p.service.pojo.Group;
import com.p.service.pojo.GroupViewRelation;
//import com.p.eximport.main.pojo.Topic;
import com.p.service.pojo.Topic;
import com.p.service.pojo.TopicGroupRelation;
import com.p.service.pojo.View;
import com.p.service.pojo.ViewTopicRelation;
import com.p.sevice.common.DAOFactory;

/**
 * Export data<br/>
 * 
 * Action[] acts = { Action.WRITE_TO_JSON };<br/>
 * 
 * ########################<br/>
 * 
 * Import Data<br/>
 * 
 * Validation<br/>
 * 
 * 1. <b>No duplicate uniqueStringID</b><br/>
 * <br/>
 * 
 * 1.1. No duplicate view uniqueStringID , if yes <br/>
 * 
 * a). <b>Phase 1</b> Ask to rename. stop export.<br/>
 * 
 * b). <b>Phase 2</b> send list of duplicate view uniqueStringID and conflicted
 * view data to compare. ask for override, leave? in case of leave, the related
 * view will not be added in target database<br/>
 * 
 * 1.2. No duplicate group uniqueStringID <br/>
 * 
 * 1.3. No duplicate topic uniqueStringID <br/>
 * 
 * 
 * 
 */
public class MainTestOld {

	public static String jsonDataDirectory = "C:/Users/premendra.kumar/Desktop/json-data/";

	

	public static void main(String[] args) {
		// method1();

		Action[] acts = {/* Action.WRITE_TO_JSON, Action.OVERWRITE, Action.FIND_CONFLICTS, Action.READ_FROM_SOURCE */};

		createAndConsumeTopicJSON(jsonDataDirectory + "topics-data.json", acts);
		createAndConsumeGroupJSON(jsonDataDirectory + "group-data.json", acts);
		createAndConsumeViewJSON(jsonDataDirectory + "view-data.json");
		createAndConsumeTopicGroupRelationJSON(jsonDataDirectory + "topic-group-data.json", acts);
		createAndConsumeGroupViewRelationJSON(jsonDataDirectory + "group-view-data.json", acts);
		createAndConsumeViewTopicRelationJSON(jsonDataDirectory + "view-topics-data.json", acts);
	}

	private static void method1() {
		List<Task> list = new ArrayList<Task>();
		for (int i = 0; i < 20; i++) {
			list.add(new Task(i, "Test1", "Test2", Task.Status.ASSIGNED, 10));
		}
		Gson gson = new Gson();
		Type type = new TypeToken<List<Task>>() {
		}.getType();
		String json = gson.toJson(list, type);
		System.out.println(json);
		List<Task> fromJson = gson.fromJson(json, type);

		for (Task task : fromJson) {
			System.out.println(task);
		}
	}

	private static void createAndConsumeTopicJSON(String filePath, Action... actions) {

		if (actions == null || actions.length < 1) {
			System.out.println("Provide action what to do ? Create or Consume??");
		}

		List<Action> acts = Arrays.asList(actions);

		if (acts.contains(Action.WRITE_TO_JSON)) {
			List<Topic> list = new ArrayList<Topic>();
			try {
				list = DAOFactory.getTopicSessionInterface().getAll();
			} catch (RestServiceException e) {
				e.printStackTrace();
			}

			Class<Topic> c = Topic.class;
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			Type type = new TypeToken<List<Topic>>() {
			}.getType();
			String json = gson.toJson(list, type);
			System.out.println(json);
			try {
				Files.write(Paths.get(filePath), json.getBytes(), StandardOpenOption.CREATE,
						StandardOpenOption.TRUNCATE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		System.out.println(
				"#######################################################################################################");

		if (acts.contains(Action.ADD_TO_TARGET)) {
			String content = "";
			try {
				content = new String(Files.readAllBytes(Paths.get(filePath)));
			} catch (IOException e) {
				e.printStackTrace();
			}

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			Type type = new TypeToken<List<Topic>>() {
			}.getType();
			List<Topic> fromJson = gson.fromJson(content, type);

			for (Topic task : fromJson) {
				System.out.println(task.getId() + " , ");
				try {
					DAOFactory.getTopicSessionInterface().create(task);
				} catch (RestServiceException e) {
					e.printStackTrace();
				}
			}
		}

	}

	private static void createAndConsumeGroupJSON(String filePath, Action... actions) {

		if (actions == null || actions.length < 1) {
			System.out.println("Provide action what to do ? Create or Consume??");
		}

		List<Action> acts = Arrays.asList(actions);

		if (acts.contains(Action.WRITE_TO_JSON)) {

			List<Group> list = new ArrayList<Group>();
			try {
				list = DAOFactory.getGroupSessionInterface().getAll();
			} catch (RestServiceException e) {
				e.printStackTrace();
			}
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			Type type = new TypeToken<List<Group>>() {
			}.getType();
			String json = gson.toJson(list, type);
			System.out.println(json);
			try {
				Files.write(Paths.get(filePath), json.getBytes(), StandardOpenOption.CREATE,
						StandardOpenOption.TRUNCATE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		System.out.println(
				"#######################################################################################################");

		if (acts.contains(Action.ADD_TO_TARGET)) {

			String content = "";
			try {
				content = new String(Files.readAllBytes(Paths.get(filePath)));
			} catch (IOException e) {
				e.printStackTrace();
			}

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			Type type = new TypeToken<List<Group>>() {
			}.getType();
			List<Group> fromJson = gson.fromJson(content, type);

			for (Group task : fromJson) {
				System.out.println(task.getId() + " , ");
				try {
					DAOFactory.getGroupSessionInterface().create(task);
				} catch (RestServiceException e) {
					e.printStackTrace();
				}
			}
		}

	}

	private static void createAndConsumeViewJSON(String filePath, Action... actions) {

		if (actions == null || actions.length < 1) {
			System.out.println("Provide action what to do ? Create or Consume??");
		}

		List<Action> acts = Arrays.asList(actions);

		if (acts.contains(Action.WRITE_TO_JSON)) {

			List<View> list = new ArrayList<View>();
			try {
				list = DAOFactory.getViewSessionInterface().getAll();
			} catch (RestServiceException e) {
				e.printStackTrace();
			}
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			Type type = new TypeToken<List<View>>() {
			}.getType();
			String json = gson.toJson(list, type);
			System.out.println(json);
			try {
				Files.write(Paths.get(filePath), json.getBytes(), StandardOpenOption.CREATE,
						StandardOpenOption.TRUNCATE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		System.out.println(
				"#######################################################################################################");

		if (acts.contains(Action.ADD_TO_TARGET)) {

			String content = "";
			try {
				content = new String(Files.readAllBytes(Paths.get(filePath)));
			} catch (IOException e) {
				e.printStackTrace();
			}

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			Type type = new TypeToken<List<View>>() {
			}.getType();
			List<View> fromJson = gson.fromJson(content, type);

			for (View task : fromJson) {
				System.out.println(task.getId() + " , ");
				try {
					DAOFactory.getViewSessionInterface().create(task);
				} catch (RestServiceException e) {
					e.printStackTrace();
				}
			}
		}

	}

	private static void createAndConsumeTopicGroupRelationJSON(String filePath, Action... actions) {

		if (actions == null || actions.length < 1) {
			System.out.println("Provide action what to do ? Create or Consume??");
		}

		List<Action> acts = Arrays.asList(actions);

		if (acts.contains(Action.WRITE_TO_JSON)) {
			List<TopicGroupRelation> list = new ArrayList<TopicGroupRelation>();
			try {
				list = DAOFactory.getTopicGroupRelationSessionInterface().getAll();
			} catch (RestServiceException e) {
				e.printStackTrace();
			}

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			Type type = new TypeToken<List<TopicGroupRelation>>() {
			}.getType();
			String json = gson.toJson(list, type);
			System.out.println(json);
			try {
				Files.write(Paths.get(filePath), json.getBytes(), StandardOpenOption.CREATE,
						StandardOpenOption.TRUNCATE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		System.out.println(
				"#######################################################################################################");

		if (acts.contains(Action.ADD_TO_TARGET)) {

			String content = "";
			try {
				content = new String(Files.readAllBytes(Paths.get(filePath)));
			} catch (IOException e) {
				e.printStackTrace();
			}
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			Type type = new TypeToken<List<TopicGroupRelation>>() {
			}.getType();
			List<TopicGroupRelation> fromJson = gson.fromJson(content, type);

			for (TopicGroupRelation task : fromJson) {
				System.out.println(task.getId() + " , ");
			}
		}

	}

	private static void createAndConsumeGroupViewRelationJSON(String filePath, Action... actions) {

		if (actions == null || actions.length < 1) {
			System.out.println("Provide action what to do ? Create or Consume??");
		}

		List<Action> acts = Arrays.asList(actions);

		if (acts.contains(Action.WRITE_TO_JSON)) {
			List<GroupViewRelation> list = new ArrayList<GroupViewRelation>();
			try {
				list = DAOFactory.getGroupViewRelationSessionInterface().getAll();
			} catch (RestServiceException e) {
				e.printStackTrace();
			}

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			Type type = new TypeToken<List<GroupViewRelation>>() {
			}.getType();
			String json = gson.toJson(list, type);
			System.out.println(json);
			try {
				Files.write(Paths.get(filePath), json.getBytes(), StandardOpenOption.CREATE,
						StandardOpenOption.TRUNCATE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		System.out.println(
				"#######################################################################################################");

		if (acts.contains(Action.ADD_TO_TARGET)) {
			String content = "";
			try {
				content = new String(Files.readAllBytes(Paths.get(filePath)));
			} catch (IOException e) {
				e.printStackTrace();
			}

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			Type type = new TypeToken<List<GroupViewRelation>>() {
			}.getType();
			List<GroupViewRelation> fromJson = gson.fromJson(content, type);

			for (GroupViewRelation task : fromJson) {
				System.out.println(task.getId() + " , ");
			}
		}

	}

	private static void createAndConsumeViewTopicRelationJSON(String filePath, Action... actions) {

		if (actions == null || actions.length < 1) {
			System.out.println("Provide action what to do ? Create or Consume??");
		}

		List<Action> acts = Arrays.asList(actions);

		if (acts.contains(Action.WRITE_TO_JSON)) {
			List<ViewTopicRelation> list = new ArrayList<ViewTopicRelation>();
			try {
				list = DAOFactory.getViewTopicRelationSessionInterface().getAll();
			} catch (RestServiceException e) {
				e.printStackTrace();
			}

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			Type type = new TypeToken<List<ViewTopicRelation>>() {
			}.getType();
			String json = gson.toJson(list, type);
			System.out.println(json);
			try {
				Files.write(Paths.get(filePath), json.getBytes(), StandardOpenOption.CREATE,
						StandardOpenOption.TRUNCATE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		System.out.println(
				"#######################################################################################################");

		if (acts.contains(Action.ADD_TO_TARGET)) {
			String content = "";
			try {
				content = new String(Files.readAllBytes(Paths.get(filePath)));
			} catch (IOException e) {
				e.printStackTrace();
			}

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			Type type = new TypeToken<List<ViewTopicRelation>>() {
			}.getType();
			List<ViewTopicRelation> fromJson = gson.fromJson(content, type);

			for (ViewTopicRelation task : fromJson) {
				System.out.println(task.getId() + " , ");
			}
		}

	}
}