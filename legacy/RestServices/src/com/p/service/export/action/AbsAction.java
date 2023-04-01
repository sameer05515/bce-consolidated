package com.p.service.export.action;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.p.service.exception.RestServiceException;
import com.p.service.export.main.Action;
import com.p.service.export.util.ConflictException;
import com.p.service.export.util.NoActionsProvidedException;
import com.p.service.export.util.RequiredActionMissingException;

public abstract class AbsAction<E> {

	private final List<Action> actions;
	private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private final Type type = new TypeToken<List<E>>() {
	}.getType();

	public AbsAction(Action... actions) throws NoActionsProvidedException {
		if (actions == null || actions.length < 1) {

			String OR = " or ";
			StringBuilder sb = new StringBuilder("Provide action what to do ? ");
			/** Convert enum to set and apply forEach() */
			Arrays.asList(Action.values()).forEach(action -> sb.append(action + "?" + OR));

			System.out.println(sb.toString().substring(0, sb.length() - OR.length()));

			throw new NoActionsProvidedException(sb.toString().substring(0, sb.length() - OR.length()));
		}

		List<Action> acts = Arrays.asList(actions);

		StringBuilder sb = new StringBuilder("Found actions : ");
		int counter = 0;
		for (Action a : acts) {
			sb.append(" (" + (++counter) + ") " + a);
		}
		System.out.println(sb.toString());

		this.actions = acts;
	}

	public Gson getGson() {
		return gson;
	}

	public Type getType() {
		return type;
	}

	public List<Action> getActions() {
		return this.actions;
	};

	public abstract void writeToJson(String filePath)
			throws RestServiceException, IOException, RequiredActionMissingException;

	public abstract List<E> readFromSource(String filePath)
			throws RestServiceException, IOException, RequiredActionMissingException;

	public abstract List<E> findConflicts(String filePath)
			throws RestServiceException, IOException, RequiredActionMissingException;

	public abstract void addToTarget(String filePath)
			throws RestServiceException, IOException, ConflictException, RequiredActionMissingException;

}
