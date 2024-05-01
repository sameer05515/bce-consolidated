//package com.p.interview.mgmt.xml;
//
//public enum ProjectConfigConstants implements XMLBasicMethodsRequired {
//
//	/**
//	 * Project config node
//	 * */
//	ROOT("interview-mgmt-root", Type.NODE),
//
//	/**
//	 * Category list node
//	 * */
//	CATEGORY_LIST("interview-mgmt-category-list", Type.NODE);
//
//	private String name;
//	private Type type;
//
//	ProjectConfigConstants(String name, Type type) {
//		this.name = name;
//		this.type = type;
//	}
//
////	@Override
//	public String getName() {
//		return name;
//	}
//
////	@Override
//	public Type getType() {
//		return type;
//	}
//
//	// ----------- XML Element types -----------
//
//	public enum Type {
//		NODE("node"), ATTRIBUTE("attribute"), COMMENT("comment"), CDATA("cdata"), SUB_NODE(
//				"subNode");
//
//		Type(String name) {
//			this.name = name;
//		}
//
//		private String name;
//
//		public String getName() {
//			return name;
//		}
//	}
//
//	// ----------- Category -------------
//	public enum Category implements XMLBasicMethodsRequired {
//		node("interview-mgmt-category", Type.NODE), ID("id", Type.CDATA), CATEGORY_NAME(
//				"name", Type.CDATA), QUESTIONS_LIST("interview-mgmt-category-question-list",
//				Type.SUB_NODE);
//
//		private String name;
//		private Type type;
//
//		Category(String name, Type type) {
//			this.name = name;
//			this.type = type;
//		}
//
////		@Override
//		public String getName() {
//			return name;
//		}
//
////		@Override
//		public Type getType() {
//			return type;
//		}
//	}
//
//	public enum Question implements XMLBasicMethodsRequired {
//		node("interview-mgmt-category-question", Type.NODE), ID("id", Type.CDATA), LINKED_CAT_ID(
//				"linked-cat-id", Type.CDATA), QUESTION_NAME("question",
//				Type.CDATA), ANSWERS_LIST("interview-mgmt-category-question-answer-list", Type.SUB_NODE);
//
//		private String name;
//		private Type type;
//
//		Question(String name, Type type) {
//			this.name = name;
//			this.type = type;
//		}
//
////		@Override
//		public String getName() {
//			return name;
//		}
//
////		@Override
//		public Type getType() {
//			return type;
//		}
//	}
//
//	public enum Answer implements XMLBasicMethodsRequired {
//		node("interview-mgmt-category-question-answer", Type.NODE), ID("id", Type.CDATA), LINKED_CAT_ID(
//				"linked-cat-id", Type.CDATA), LINKED_QUES_ID("linked-ques-id",
//				Type.CDATA), ANSWER_NAME("answer", Type.CDATA);
//
//		private String name;
//		private Type type;
//
//		Answer(String name, Type type) {
//			this.name = name;
//			this.type = type;
//		}
//
////		@Override
//		public String getName() {
//			return name;
//		}
//
////		@Override
//		public Type getType() {
//			return type;
//		}
//	}
//
//}
