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

INSERT INTO mealplan (mealplan_name, mealplan_description, created_by_user_id) VALUES 
('Healthy Meal Plan', 'A meal plan focusing on balanced nutrition.', 3),
('Quick and Easy Meals', 'Simple and fast meals for busy days.', 3),
('Family Favorites', 'Meals that the whole family will love.', 3);


INSERT INTO user_tracked_mealplan (mealplan_id, start_date, user_id) VALUES 
((SELECT mealplan_id FROM mealplan WHERE mealplan_name = 'Healthy Meal Plan'), '2024-08-14', 3);

INSERT INTO mealplan_recipe (mealplan_id, mealplan_day_count, breakfast_recipe_id, lunch_recipe_id, dinner_recipe_id) VALUES 
((SELECT mealplan_id FROM mealplan WHERE mealplan_name = 'Healthy Meal Plan'), 1, (SELECT recipe_id FROM recipe WHERE recipe_name = 'Caesar Salad'), (SELECT recipe_id FROM recipe WHERE recipe_name = 'Spaghetti Carbonara'), (SELECT recipe_id FROM recipe WHERE recipe_name = 'Grilled Chicken')),
((SELECT mealplan_id FROM mealplan WHERE mealplan_name = 'Healthy Meal Plan'), 2, (SELECT recipe_id FROM recipe WHERE recipe_name = 'Caesar Salad'), (SELECT recipe_id FROM recipe WHERE recipe_name = 'Spaghetti Carbonara'), (SELECT recipe_id FROM recipe WHERE recipe_name = 'Grilled Chicken')),
((SELECT mealplan_id FROM mealplan WHERE mealplan_name = 'Quick and Easy Meals'), 1, (SELECT recipe_id FROM recipe WHERE recipe_name = 'Pancakes'), (SELECT recipe_id FROM recipe WHERE recipe_name = 'Tacos'), NULL),
((SELECT mealplan_id FROM mealplan WHERE mealplan_name = 'Quick and Easy Meals'), 2, (SELECT recipe_id FROM recipe WHERE recipe_name = 'Pancakes'), (SELECT recipe_id FROM recipe WHERE recipe_name = 'Tacos'), NULL),

((SELECT mealplan_id FROM mealplan WHERE mealplan_name = 'Family Favorites'), 1, (SELECT recipe_id FROM recipe WHERE recipe_name = 'Chocolate Chip Cookies'), (SELECT recipe_id FROM recipe WHERE recipe_name = 'Caesar Salad'), (SELECT recipe_id FROM recipe WHERE recipe_name = 'Spaghetti Carbonara')),
((SELECT mealplan_id FROM mealplan WHERE mealplan_name = 'Family Favorites'), 2, (SELECT recipe_id FROM recipe WHERE recipe_name = 'Chocolate Chip Cookies'), (SELECT recipe_id FROM recipe WHERE recipe_name = 'Caesar Salad'), (SELECT recipe_id FROM recipe WHERE recipe_name = 'Spaghetti Carbonara'));





COMMIT;
-- ROLLBACK;

