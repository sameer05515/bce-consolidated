package com.p.interview.mgmt.backup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Vector;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.p.interview.mgmt.pojo.CategoryDTO;
import com.p.interview.mgmt.rpc.InterviewRPC;
//import com.p.service.pojo.Topic;

public class MainTest {

	public static String jsonDataDirectory = "C:\\Users\\premendra.kumar\\Desktop\\DUMP\\interview-json-data\\";
	public static final String categoryDataJsonFileName = "category-data.json";
	private static InterviewRPC objInterviewRPC = new InterviewRPC();

	public static void main(String[] args) {
		createCategoryJSON(jsonDataDirectory + categoryDataJsonFileName);
	}

	public static String createCategoryJSON(String categoryDataJsonFileLocation) {
		Vector<CategoryDTO> list = new Vector<>();
		try {
			list = objInterviewRPC.fetchAllCategories();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type type = new TypeToken<Vector<CategoryDTO>>() {
		}.getType();
		String json = gson.toJson(list, type);
		System.out.println(json);
		try {
			Files.write(Paths.get(categoryDataJsonFileLocation), json.getBytes(), StandardOpenOption.CREATE,
					StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("############################################## " + categoryDataJsonFileName
				+ " file created at given " + "\"" + jsonDataDirectory + "\""
				+ " location #########################################################");

		return "############################################## " + categoryDataJsonFileName + " file created at given "
				+ "\"" + jsonDataDirectory + "\""
				+ " location #########################################################";

	}

	private static void consumeCategoryJSON(String categoryDataJsonFileLocation) {
		Vector<CategoryDTO> list = new Vector<>();
		try {
			list = objInterviewRPC.fetchAllCategories();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type type = new TypeToken<Vector<CategoryDTO>>() {
		}.getType();
		String json = gson.toJson(list, type);
		System.out.println(json);
		try {
			Files.write(Paths.get(categoryDataJsonFileLocation), json.getBytes(), StandardOpenOption.CREATE,
					StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(
				"#######################################################################################################");

		// String content = "";
		// try {
		// content = new String(Files.readAllBytes(Paths.get(filePath)));
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		//
		// List<Group> fromJson = gson.fromJson(content, type);
		//
		// for (Group task : fromJson) {
		// System.out.println(task.getId() + " , ");
		// try {
		// DAOFactory.getGroupSessionInterface().create(task);
		// } catch (RestServiceException e) {
		// e.printStackTrace();
		// }
		// }

	}

}
