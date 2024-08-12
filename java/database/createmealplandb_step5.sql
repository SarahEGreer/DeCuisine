START TRANSACTION;
DROP TABLE IF EXISTS mealplan, user_tracked_mealplan, mealplan_recipe CASCADE;

DROP SEQUENCE IF EXISTS sec_mealplan_id, sec_event_id;
CREATE SEQUENCE sec_mealplan_id
	INCREMENT BY 1
	START WITH 100
	NO MAXVALUE;
	
CREATE SEQUENCE  sec_event_id
	INCREMENT BY 1
	START WITH 100
	NO MAXVALUE;
		
CREATE TABLE mealplan (
	mealplan_id int PRIMARY KEY DEFAULT nextval('sec_mealplan_id'),
	mealplan_name VARCHAR(50) UNIQUE NOT NULL,
	mealplan_description VARCHAR(200),
	created_by_user_id INT NOT NULL,
	CONSTRAINT fk_created_by_user_id FOREIGN KEY (created_by_user_id) REFERENCES users(user_id)
);

CREATE TABLE user_tracked_mealplan (
	event_id int PRIMARY KEY DEFAULT nextval('sec_event_id'),
	mealplan_id INT NOT NULL,
	start_date DATE NOT NULL,
	user_id INT NOT NULL,
	CONSTRAINT fk_mealplan_id FOREIGN KEY (mealplan_id) REFERENCES mealplan(mealplan_id),
	CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE mealplan_recipe (
	mealplan_id INT NOT NULL,
	mealplan_day_count INT NOT NULL,
	breakfast_recipe_id INT,
	lunch_recipe_id INT,
	dinner_recipe_id INT,
	CONSTRAINT fk_mealplan_id FOREIGN KEY (mealplan_id) REFERENCES mealplan(mealplan_id),
	CONSTRAINT fk_breakfast_recipe_id FOREIGN KEY (breakfast_recipe_id) REFERENCES recipe(recipe_id),
	CONSTRAINT fk_lunch_recipe_id FOREIGN KEY (lunch_recipe_id) REFERENCES recipe(recipe_id),
	CONSTRAINT fk_dinner_recipe_id FOREIGN KEY (dinner_recipe_id) REFERENCES recipe(recipe_id)
);

INSERT INTO mealplan (mealplan_name, mealplan_description, created_by_user_id) VALUES ('testmealplan','this is a test',1);

INSERT INTO user_tracked_mealplan (mealplan_id, start_date, user_id) VALUES (100,'2024-08-08',1);

INSERT INTO mealplan_recipe (mealplan_id, mealplan_day_count, breakfast_recipe_id, lunch_recipe_id, dinner_recipe_id) VALUES (100, 1, 1, 2, NULL);

COMMIT;
-- ROLLBACK;