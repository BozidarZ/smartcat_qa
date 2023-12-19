
CREATE TABLE shift_breaks
(
	id INT not null,
	shift_id INT not null,
	timesheet_id INT not null,
	date DATE,
	start DATETIME,
	finish DATETIME,
	length INT,
	paid BOOLEAN,
	updated_at DATETIME,
	loaded_at DATETIME
);


CREATE TABLE shift_allowances
(
	id INT not null,
	shift_id INT not null,
	timesheet_id INT not null,
	date DATE,
	name VARCHAR(100),
	value INT,
	updated_at DATETIME,
	cost FLOAT,
	loaded_at DATETIME
);



CREATE TABLE shift_award_interpretation
(
	shift_id INT not null,
	timesheet_id INT not null,
	units INT,
	date DATE,
	export_name VARCHAR(50),
	secondary_export_name VARCHAR(50),	
	ordinary_hours BOOLEAN,
	cost FLOAT,
	`from` DATETIME,
	`to` DATETIME,
	loaded_at DATETIME
);


CREATE TABLE shifts
(
	id INT not null,
	timesheet_id INT not null,
	user_id INT not null,
	department_id INT,
	date DATE,
	start DATETIME,
	finish DATETIME,
	break_start DATETIME,
	break_finish DATETIME,
	break_length INT,
	sub_cost_centre FLOAT,
	tag VARCHAR(50),
	tag_id INT,
	status VARCHAR(50),
	metadata VARCHAR(100),
	leave_request_id VARCHAR(50),
	shift_feedback_id VARCHAR(50),
	approved_by VARCHAR(50),
	approved_at DATETIME,
	cost FLOAT,
	cost_breakdown VARCHAR(100),
	updated_at DATETIME,
	record_id INT,
	last_costed_at DATETIME,
	loaded_at DATETIME
);