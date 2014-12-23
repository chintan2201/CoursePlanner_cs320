use cs320stu55;

drop table if exists courseplans;
create table  courseplans
(
	id integer auto_increment primary key,
	course_code varchar(255),
	course_title varchar(255),
	course_prerec varchar(500),
	quarter varchar(255),
	time_stamp varchar(255),
	user_name_plan varchar(255) references userdetails(user_name)
);


