CREATE TABLE `t_category` (
  `cat_id` int(11) NOT NULL,
  `cat_name` longtext,
  `creation_date` datetime NOT NULL,
  `last_updation_date` datetime NOT NULL,
  `rating` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`cat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='table containing data for categories';

CREATE TABLE `t_catg_ques` (
  `ques_id` int(11) NOT NULL,
  `linked_cat_id` int(11) NOT NULL,
  `ques` longtext,
  `creation_date` datetime NOT NULL,
  `last_updation_date` datetime NOT NULL,
  `rating` int(11) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='table containing data for questions';

CREATE TABLE `t_catg_ques_ans` (
  `ans_id` int(11) NOT NULL,
  `linked_ques_id` int(11) NOT NULL,
  `linked_cat_id` int(11) NOT NULL,
  `answer` longtext,
  `creation_date` datetime NOT NULL,
  `last_updation_date` datetime NOT NULL,
  `rating` int(11) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='table containing data for answers';