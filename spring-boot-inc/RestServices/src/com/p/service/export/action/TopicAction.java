package com.p.service.export.action;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.p.service.exception.RestServiceException;
import com.p.service.export.main.Action;
import com.p.service.export.util.ConflictException;
import com.p.service.export.util.NoActionsProvidedException;
import com.p.service.export.util.RequiredActionMissingException;
import com.p.service.pojo.Topic;
import com.p.sevice.common.DAOFactory;

public class TopicAction extends AbsAction<Topic> {

	// @Override
	// public List<Action> getActions(Action... actions) {
	//
	// }

	public TopicAction(Action[] actions) throws NoActionsProvidedException {
		super(actions);
	}

	@Override
	public void writeToJson(String filePath) throws RestServiceException, IOException, RequiredActionMissingException {

		List<Action> acts = getActions();

		if (acts.contains(Action.WRITE_TO_JSON)) {
			List<Topic> list = new ArrayList<Topic>();
			// try {
			list = DAOFactory.getTopicSessionInterface().getAll();
			// } catch (RestServiceException e) {
			// e.printStackTrace();
			// }

			Gson gson = getGson();
			Type type = getType();
			String json = gson.toJson(list, type);
			System.out.println(json);
			// try {
			Files.write(Paths.get(filePath), json.getBytes(), StandardOpenOption.CREATE,
					StandardOpenOption.TRUNCATE_EXISTING);
			// } catch (IOException e) {
			// e.printStackTrace();
			// }
		} else {
			throw new RequiredActionMissingException(
					" Add Action : " + Action.WRITE_TO_JSON + " to write existing Topics in DB to provided JSON ");
		}

	}

	@Override
	public List<Topic> findConflicts(String filePath)
			throws RestServiceException, IOException, RequiredActionMissingException {
		List<Action> acts = getActions();

		List<Topic> conflicts = new ArrayList<>();

		if (acts.contains(Action.FIND_CONFLICTS)) {

			List<Topic> readListFromSource = readFromSource(filePath);

			if (readListFromSource.size() >= 0) {
				List<Topic> list = new ArrayList<Topic>();
				// try {
				list = DAOFactory.getTopicSessionInterface().getAll();

				for (Topic fromSourceJson : readListFromSource) {
					for (Topic fromTargetDB : list) {
						if (fromSourceJson.getUniqueStrid().equals(fromTargetDB.getUniqueStrid())) {
							conflicts.add(fromSourceJson);

						}
					}
				}
			}

		} else {
			throw new RequiredActionMissingException(" Add Action : " + Action.FIND_CONFLICTS
					+ " to check conflicted Topics of provided JSON with existing Topics in  DB");
		}

		return conflicts;
	}

	@Override
	public void addToTarget(String filePath)
			throws RestServiceException, IOException, ConflictException, RequiredActionMissingException {

		List<Action> acts = getActions();

		List<Topic> conflictedTopics = findConflicts(filePath);

		if (conflictedTopics.size() > 0 && !acts.contains(Action.OVERWRITE)) {
			throw new ConflictException(
					"Found some vlaues having duplicate uniqueStringID and Overwrite permission not found in given commands");
		}

		if (acts.contains(Action.ADD_TO_TARGET)) {
			String content = "";
			// try {
			content = new String(Files.readAllBytes(Paths.get(filePath)));
			// } catch (IOException e) {
			// e.printStackTrace();
			// }

			Gson gson = getGson();
			Type type = getType();
			List<Topic> fromJson = gson.fromJson(content, type);

			for (Topic task : fromJson) {
				System.out.println(task.getId() + " , ");
				try {
					DAOFactory.getTopicSessionInterface().create(task);
				} catch (RestServiceException e) {
					e.printStackTrace();
				}
			}
		} else {
			throw new RequiredActionMissingException(
					" Add Action : " + Action.ADD_TO_TARGET + " to add Topics of provided JSON into DB");
		}

	}

	@Override
	public List<Topic> readFromSource(String filePath)
			throws RestServiceException, IOException, RequiredActionMissingException {
		List<Action> acts = getActions();

		if (acts.contains(Action.READ_FROM_SOURCE)) {
			String content = "";
			// try {
			content = new String(Files.readAllBytes(Paths.get(filePath)));
			// } catch (IOException e) {
			// e.printStackTrace();
			// }

			Gson gson = getGson();
			Type type = getType();
			List<Topic> fromJson = gson.fromJson(content, type);

			return fromJson;

		} else {
			throw new RequiredActionMissingException(
					" Add Action : " + Action.READ_FROM_SOURCE + " to read provided JSON");
		}

	}

}
