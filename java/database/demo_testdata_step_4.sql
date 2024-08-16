START TRANSACTION;

DROP TABLE IF EXISTS user_tracked_recipes, recipe_tags, recipes_ingredients, ingredients, recipe, tag_id, user_grocery_list CASCADE;
DROP SEQUENCE IF EXISTS sec_ingredient_id, sec_recipe_id, sec_tag_id;

CREATE SEQUENCE sec_recipe_id
 INCREMENT BY 1
 START WITH 2001
 NO MAXVALUE;

CREATE SEQUENCE sec_ingredient_id
  INCREMENT BY 1
  START WITH 50
  NO MAXVALUE;
  
CREATE SEQUENCE sec_tag_id
  INCREMENT BY 1
  START WITH 50
  NO MAXVALUE;
  

CREATE TABLE recipe
(
    recipe_id int PRIMARY KEY DEFAULT nextval('sec_recipe_id'),
    created_by_user_id INT NOT NULL,
    recipe_name VARCHAR(200) NOT NULL,
    description VARCHAR(1000),
    instructions VARCHAR(6500) NOT NULL,
    prep_time NUMERIC,
    cook_time NUMERIC,
    servings NUMERIC,
	photo_url VARCHAR(500),
    CONSTRAINT fk_recipe_users FOREIGN KEY (created_by_user_id) REFERENCES users(user_id)
);

CREATE TABLE ingredients
(
    ingredient_id int PRIMARY KEY DEFAULT nextval('sec_ingredient_id'),
    ingredient_name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE recipes_ingredients
(
    recipe_id INT NOT NULL,
    ingredient_id INT NOT NULL,
    amount NUMERIC NOT NULL,
    unit_type VARCHAR(100) NOT NULL,
    system_of_measurement VARCHAR(100),
    CONSTRAINT fk_recipes_ingredients_recipe FOREIGN KEY (recipe_id) REFERENCES recipe(recipe_id),
    CONSTRAINT fk_recipes_ingredients_ingredient FOREIGN KEY (ingredient_id) REFERENCES ingredients(ingredient_id)
);

CREATE TABLE tag_id
(
    tag_id INT PRIMARY KEY DEFAULT nextval('sec_tag_id'),
    tag_name VARCHAR(50) NOT NULL
);

CREATE TABLE recipe_tags
(
    recipe_id INT NOT NULL,
    tag_id INT NOT NULL,
    CONSTRAINT pk_recipe_tags PRIMARY KEY (recipe_id, tag_id),
    CONSTRAINT fk_recipe_tags_recipe FOREIGN KEY (recipe_id) REFERENCES recipe(recipe_id),
    CONSTRAINT fk_recipe_tags_tag FOREIGN KEY (tag_id) REFERENCES tag_id(tag_id)
);

CREATE TABLE user_tracked_recipes
(
    recipes_id INT NOT NULL,
    user_id INT NOT NULL,
    CONSTRAINT pk_user_tracked_recipes PRIMARY KEY (recipes_id, user_id),
    CONSTRAINT fk_user_tracked_recipes_recipe FOREIGN KEY (recipes_id) REFERENCES recipe(recipe_id),
    CONSTRAINT fk_user_tracked_recipes_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);
CREATE TABLE user_grocery_list
(
	user_id int NOT NULL,
	item_name VARCHAR(100) NOT NULL,
	amount NUMERIC NOT NULL,
	unit_type VARCHAR(100) NOT NULL,
	system_of_measurement VARCHAR(100),
	CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
);


--test data
INSERT INTO recipe (created_by_user_id, recipe_name, description, instructions, prep_time, cook_time, servings, photo_url) VALUES
(3, 'Caesar Salad', 'A classic Caesar salad with a rich, creamy dressing.', 
'1. Prepare dressing.
2. Toss lettuce with dressing.
3. Add croutons and parmesan cheese.
4. Serve immediately.', 15, 0, 4, 'https://i.postimg.cc/HLJFKHq9/Caesar-Salad-Recipe-3.jpg'),

(3, 'Grilled Chicken', 'Juicy grilled chicken breasts.', 
'1. Marinate chicken.
2. Grill over medium heat until fully cooked.
3. Serve hot.', 30, 15, 4, 'https://i.postimg.cc/sXdHNW93/grilled-chicken-recipe-25.jpg'),

(3, 'Pancakes', 'Fluffy homemade pancakes.', 
'1. Mix ingredients.
2. Cook on a hot griddle.
3. Serve with syrup.', 10, 5, 4, 'https://i.postimg.cc/wM9f6dGP/wholesomeyum-Low-Carb-Keto-Pancakes-Recipe-21.jpg'),

(3, 'Chocolate Chip Cookies', 'Delicious homemade chocolate chip cookies.', 
'1. Preheat oven to 350°F (175°C).
2. Cream together butter and sugars.
3. Add eggs and vanilla extract.
4. Mix in dry ingredients and chocolate chips.
5. Scoop dough onto baking sheets and bake for 10-12 minutes.', 15, 12, 24, 'https://i.postimg.cc/JzF2jvsD/classic-chocolate-chip-cookies.jpg'),

(3, 'Spaghetti Carbonara', 'A classic Italian pasta dish made with eggs, cheese, pancetta, and pepper.', 
'1. Cook spaghetti according to package instructions.
2. In a bowl, whisk eggs and cheese together.
3. In a pan, cook pancetta until crispy.
4. Combine spaghetti, pancetta, and egg mixture.
5. Serve with pepper and extra cheese.', 10, 15, 4, 'https://i.postimg.cc/JzzfNjKg/11973-spaghetti-carbonara-ii-DDMFS-4x3-6edea51e421e4457ac0c3269f3be5157.jpg'),

(3, 'Tacos', 'Traditional Mexican tacos with beef, lettuce, and cheese.', 
'1. Cook beef with spices.
2. Warm tortillas.
3. Assemble tacos with beef, lettuce, and cheese.', 20, 10, 8, 'https://i.postimg.cc/m28qgtRS/chicken-tinga-tinga-de-pollo-4773239-Hero-01-1bd1d960c02a4fdb812323b8c60fd55b.jpg'),

(3, 'Tomato Basil Soup', 'A creamy and flavorful tomato soup with fresh basil.', 
'1. Heat olive oil in a pot and sauté onions until translucent.
2. Add garlic and cook for 1 minute.
3. Add tomatoes and broth, simmer for 20 minutes.
4. Blend the soup until smooth, then stir in cream and basil.
5. Serve hot with a drizzle of olive oil.', 10, 30, 4, 'https://i.postimg.cc/Gpkx95G1/tomato-basil-soup.jpg'),

(3, 'Chicken Alfredo', 'A rich and creamy pasta dish with tender chicken and parmesan.', 
'1. Cook pasta according to package instructions.
2. In a pan, cook chicken until browned and cooked through.
3. In another pan, melt butter and add garlic, cooking until fragrant.
4. Stir in cream and parmesan cheese until melted.
5. Combine pasta, chicken, and sauce, and serve hot.', 15, 25, 4, 'https://i.postimg.cc/x8dvxj70/Chicken-Alfredo-above-500x500.jpg'),

(3, 'Lemon Garlic Shrimp', 'Succulent shrimp sautéed in a tangy lemon garlic butter sauce.', 
'1. Heat butter in a pan over medium heat.
2. Add garlic and cook for 1 minute.
3. Add shrimp and cook until pink and opaque.
4. Stir in lemon juice and parsley, and serve immediately.', 10, 10, 4, 'https://i.postimg.cc/tgHh0Nfv/lemon-garlic-shrimp-pasta-2.jpg'),

(3, 'Vegetable Curry', 'A spicy and aromatic curry packed with fresh vegetables.', 
'1. Heat oil in a large pot, add onions, and sauté until golden.
2. Add garlic, ginger, and spices, cooking until fragrant.
3. Add vegetables and cook for 10 minutes.
4. Pour in coconut milk and simmer for 20 minutes.
5. Serve hot with rice or naan.', 20, 30, 4, 'https://i.postimg.cc/c4gYrzG5/Vegetable-Curry-Recipe.jpg'),

(3, 'Beef Tacos', 'Flavorful ground beef served in soft tortillas with your favorite toppings.', 
'1. Cook ground beef in a skillet over medium heat until browned.
2. Stir in taco seasoning and water, simmer until thickened.
3. Warm tortillas and assemble tacos with beef and desired toppings.', 15, 20, 8, 'https://i.postimg.cc/mkcc1PJF/beef-taco.jpg'),

(3, 'Cauliflower Fried Rice', 'A low-carb alternative to fried rice made with cauliflower.', 
'1. Pulse cauliflower in a food processor until it resembles rice.
2. Heat oil in a pan, add garlic and cook until fragrant.
3. Add vegetables and cook for 5 minutes.
4. Stir in cauliflower rice and soy sauce, and cook for 5 minutes.
5. Serve hot with green onions.', 15, 15, 4, 'https://i.postimg.cc/D0tSr23p/Cauliflower-Fried-Rice-Recipe.jpg'),

(3, 'BBQ Chicken Pizza', 'A homemade pizza topped with barbecue sauce, chicken, and cheese.', 
'1. Preheat oven to 475°F (245°C).
2. Roll out pizza dough and spread with barbecue sauce.
3. Top with cooked chicken, onions, and cheese.
4. Bake for 12-15 minutes until crust is golden and cheese is melted.
5. Serve hot, garnished with cilantro.', 20, 15, 4, 'https://i.postimg.cc/KYZRrqCb/bbq-chicken-pizza-feature.jpg'),

(3, 'Stuffed Bell Peppers', 'Bell peppers stuffed with a savory mixture of ground meat and rice.', 
'1. Preheat oven to 375°F (190°C).
2. Cook ground meat in a skillet, then stir in cooked rice, tomatoes, and spices.
3. Cut tops off bell peppers and remove seeds.
4. Stuff peppers with meat mixture and place in a baking dish.
5. Bake for 30-35 minutes, until peppers are tender.', 20, 35, 4, 'https://i.postimg.cc/cC8H4VtR/vegan-stuffed-peppers.jpg'),

(3, 'Margarita Pizza', 'A classic Italian pizza with tomatoes, mozzarella, and basil.', 
'1. Preheat oven to 475°F (245°C).
2. Roll out pizza dough and spread with tomato sauce.
3. Top with mozzarella and tomato slices.
4. Bake for 10-12 minutes until crust is golden and cheese is melted.
5. Garnish with fresh basil and serve.', 15, 12, 4, 'https://i.postimg.cc/8sQCFtLq/margherita-pizza.jpg'),

(3, 'Chicken Stir-Fry', 'A quick and easy stir-fry with chicken and vegetables.', 
'1. Heat oil in a wok or large pan over high heat.
2. Add chicken and stir-fry until browned.
3. Add vegetables and cook for 5-7 minutes.
4. Stir in soy sauce and cook for another 2 minutes.
5. Serve hot over rice or noodles.', 15, 10, 4, 'https://i.postimg.cc/44Kb9yz5/chicken-stir-fry-1.jpg'),

(3, 'Spaghetti Bolognese', 'A hearty Italian pasta dish with a rich meat sauce.', 
'1. Cook spaghetti according to package instructions.
2. In a pot, cook ground beef and onions until browned.
3. Add garlic, tomatoes, and herbs, and simmer for 30 minutes.
4. Serve the sauce over spaghetti, topped with parmesan.', 15, 30, 4, 'https://i.postimg.cc/638RnbG3/Spaghetti-Bolognese.jpg'),

(3, 'Grilled Cheese Sandwich', 'A classic grilled cheese sandwich with a crispy exterior and gooey interior.', 
'1. Butter one side of each bread slice.
2. Place cheese between the bread slices, butter side out.
3. Cook in a skillet over medium heat until golden and crispy on both sides.', 5, 10, 2, 'https://i.postimg.cc/k4LScNxy/grilled-cheese.jpg'),

(3, 'Chocolate Cake', 'A rich and moist chocolate cake with a creamy frosting.', 
'1. Preheat oven to 350°F (175°C).
2. Mix together flour, sugar, cocoa, baking powder, and salt.
3. Add eggs, milk, oil, and vanilla, and beat until smooth.
4. Pour batter into a greased pan and bake for 30-35 minutes.
5. Frost with chocolate frosting and serve.', 20, 35, 12, 'https://i.postimg.cc/g2GZN261/chocolate-cake.jpg'),

(3, 'Mushroom Risotto', 'A creamy risotto with sautéed mushrooms and parmesan.', 
'1. Heat broth in a pot and keep warm.
2. In a pan, sauté mushrooms until golden.
3. Add rice and cook for 2 minutes.
4. Gradually add broth, stirring constantly until absorbed.
5. Stir in parmesan and serve hot.', 20, 30, 4, 'https://i.postimg.cc/Gp2sngKQ/Creamy-Mushroom-Risotto.jpg'),

(3, 'Fish Tacos', 'Tacos with crispy fish, cabbage slaw, and a tangy sauce.', 
'1. Cook fish fillets in a skillet until golden and crispy.
2. Assemble tacos with fish, cabbage slaw, and sauce.
3. Serve with lime wedges.', 15, 10, 8, 'https://i.postimg.cc/yd3gkJw3/Fish-Tacos.jpg'),

(3, 'Chicken Caesar Salad', 'A classic Caesar salad topped with grilled chicken.', 
'1. Grill chicken until cooked through.
2. Toss lettuce with Caesar dressing.
3. Top with chicken, croutons, and parmesan.', 15, 15, 4, 'https://i.postimg.cc/vTR19Ysq/Grilled-Chicken-Caesar-Salad.jpg'),

(3, 'Mac and Cheese', 'A creamy and cheesy pasta dish that is pure comfort food.', 
'1. Cook pasta according to package instructions.
2. In a pot, melt butter and stir in flour.
3. Gradually add milk, stirring until thickened.
4. Stir in cheese until melted.
5. Combine with pasta and serve hot.', 10, 15, 4, 'https://i.postimg.cc/bN9GDfFg/Ultimate-Creamy-Baked-Mac-and-Cheese-feat.jpg'),

(3, 'Shrimp Scampi', 'Shrimp sautéed in garlic, butter, and white wine, served over pasta.', 
'1. Cook pasta according to package instructions.
2. In a pan, sauté garlic in butter until fragrant.
3. Add shrimp and cook until pink.
4. Stir in white wine and lemon juice, and simmer for 2 minutes.
5. Serve over pasta, garnished with parsley.', 10, 10, 4, 'https://i.postimg.cc/4x4Yp2Dg/Shrimp-Scampi-IMG-1006.jpg'),

(3, 'Pulled Pork Sandwich', 'Slow-cooked pulled pork served on a bun with barbecue sauce.', 
'1. Cook pork in a slow cooker with barbecue sauce until tender.
2. Shred the pork and serve on buns with extra sauce.', 20, 480, 8, 'https://i.postimg.cc/dVWL6mFT/BBQ-Pulled-Pork-1.jpg'),

(3, 'Greek Salad', 'A fresh salad with cucumbers, tomatoes, feta, and olives.', 
'1. Toss cucumbers, tomatoes, onions, and olives together.
2. Drizzle with olive oil and lemon juice.
3. Top with feta cheese and serve.', 10, 0, 4, 'https://i.postimg.cc/KYR4Hkcp/Greek-Salad-2-scaled.jpg'),

(3, 'French Toast', 'A breakfast favorite made with thick slices of bread soaked in a cinnamon-egg mixture.', 
'1. Whisk together eggs, milk, cinnamon, and vanilla.
2. Dip bread slices in the mixture, coating both sides.
3. Cook on a griddle until golden brown on both sides.
4. Serve with syrup and fresh berries.', 10, 10, 4, 'https://i.postimg.cc/jjmC0xLx/best-easy-french-toast-recipe-4.jpg');

-- Insert ingredients for the new recipes
INSERT INTO ingredients (ingredient_name) VALUES  
('croutons'), 
('parmesan cheese'), 
('chicken breast'), 
('flour'), 
('milk'), 
('eggs'), 
('butter'), 
('sugar'),
('chocolate chips'),
('spaghetti'),
('pancetta'),
('black pepper'),
('beef'),
('tortillas'),
('cheese'),
('olive oil'), 
('onions'), 
('garlic'), 
('tomatoes'),  
('pasta'), 
('shrimp'), 
('lemon'), 
('basil'), 
('mushrooms'), 
('cheese'),  
('cabbage'), 
('pork'), 
('bread'), 
('cinnamon'),
('love'),
('lettuce'),
('cauliflower'),
('rice'),
('cocoa'),
('tortillas'),
('fish'),
('cabbage'),
('cinnamon'),
('cucumbers'),
('feta cheese')
ON CONFLICT (ingredient_name) DO NOTHING;


-- Recipes_Ingredients Table
INSERT INTO recipes_ingredients (recipe_id, ingredient_id, amount, unit_type, system_of_measurement) VALUES
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Caesar Salad'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'lettuce'), 1, 'head', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Caesar Salad'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'croutons'), 0.5, 'cup', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Caesar Salad'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'parmesan cheese'), 0.25, 'cup', 'Imperial');


INSERT INTO recipes_ingredients (recipe_id, ingredient_id, amount, unit_type, system_of_measurement) VALUES
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Grilled Chicken'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'chicken breast'), 2, 'pieces', 'Imperial');

INSERT INTO recipes_ingredients (recipe_id, ingredient_id, amount, unit_type, system_of_measurement) VALUES
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Pancakes'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'flour'), 2, 'cups', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Pancakes'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'milk'), 1.5, 'cups', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Pancakes'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'eggs'), 2, 'pieces', 'Imperial');

INSERT INTO recipes_ingredients (recipe_id, ingredient_id, amount, unit_type, system_of_measurement) VALUES
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Chocolate Chip Cookies'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'butter'), 1, 'cup', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Chocolate Chip Cookies'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'sugar'), 0.5, 'cup', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Chocolate Chip Cookies'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'chocolate chips'), 2, 'cups', 'Imperial');

INSERT INTO recipes_ingredients (recipe_id, ingredient_id, amount, unit_type, system_of_measurement) VALUES
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Spaghetti Carbonara'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'spaghetti'), 400, 'grams', 'Metric'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Spaghetti Carbonara'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'pancetta'), 150, 'grams', 'Metric'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Spaghetti Carbonara'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'black pepper'), 5, 'grams', 'Metric');

INSERT INTO recipes_ingredients (recipe_id, ingredient_id, amount, unit_type, system_of_measurement) VALUES
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Tacos'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'beef'), 500, 'grams', 'Metric'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Tacos'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'tortillas'), 8, 'pieces', 'Metric'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Tacos'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'cheese'), 100, 'grams', 'Metric');

INSERT INTO recipes_ingredients (recipe_id, ingredient_id, amount, unit_type, system_of_measurement) VALUES
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Tomato Basil Soup'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'olive oil'), 2, 'tbsp', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Tomato Basil Soup'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'onions'), 1, 'cup', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Tomato Basil Soup'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'garlic'), 3, 'cloves', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Tomato Basil Soup'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'tomatoes'), 4, 'cups', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Tomato Basil Soup'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'basil'), 0.5, 'cup', 'Imperial');

INSERT INTO recipes_ingredients (recipe_id, ingredient_id, amount, unit_type, system_of_measurement) VALUES
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Chicken Alfredo'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'chicken breast'), 2, 'pieces', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Chicken Alfredo'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'pasta'), 8, 'oz', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Chicken Alfredo'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'parmesan cheese'), 1, 'cup', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Chicken Alfredo'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'garlic'), 2, 'cloves', 'Imperial');

INSERT INTO recipes_ingredients (recipe_id, ingredient_id, amount, unit_type, system_of_measurement) VALUES
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Lemon Garlic Shrimp'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'shrimp'), 1, 'lb', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Lemon Garlic Shrimp'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'garlic'), 4, 'cloves', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Lemon Garlic Shrimp'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'lemon'), 1, 'whole', 'Imperial');

INSERT INTO recipes_ingredients (recipe_id, ingredient_id, amount, unit_type, system_of_measurement) VALUES
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Vegetable Curry'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'onions'), 1, 'cup', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Vegetable Curry'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'garlic'), 3, 'cloves', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Vegetable Curry'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'tomatoes'), 2, 'cups', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Vegetable Curry'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'basil'), 0.5, 'cup', 'Imperial');

INSERT INTO recipes_ingredients (recipe_id, ingredient_id, amount, unit_type, system_of_measurement) VALUES
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Beef Tacos'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'beef'), 1, 'lb', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Beef Tacos'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'cheese'), 1, 'cup', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Beef Tacos'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'tortillas'), 8, 'pieces', 'Imperial');

INSERT INTO recipes_ingredients (recipe_id, ingredient_id, amount, unit_type, system_of_measurement) VALUES
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Cauliflower Fried Rice'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'cauliflower'), 1, 'head', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Cauliflower Fried Rice'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'garlic'), 3, 'cloves', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Cauliflower Fried Rice'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'onions'), 1, 'cup', 'Imperial');

INSERT INTO recipes_ingredients (recipe_id, ingredient_id, amount, unit_type, system_of_measurement) VALUES
((SELECT recipe_id FROM recipe WHERE recipe_name = 'BBQ Chicken Pizza'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'chicken breast'), 2, 'pieces', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'BBQ Chicken Pizza'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'cheese'), 1, 'cup', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'BBQ Chicken Pizza'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'onions'), 1, 'cup', 'Imperial');

INSERT INTO recipes_ingredients (recipe_id, ingredient_id, amount, unit_type, system_of_measurement) VALUES
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Stuffed Bell Peppers'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'beef'), 1, 'lb', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Stuffed Bell Peppers'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'rice'), 1, 'cup', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Stuffed Bell Peppers'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'cheese'), 1, 'cup', 'Imperial');

INSERT INTO recipes_ingredients (recipe_id, ingredient_id, amount, unit_type, system_of_measurement) VALUES
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Margarita Pizza'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'cheese'), 1, 'cup', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Margarita Pizza'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'tomatoes'), 1, 'cup', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Margarita Pizza'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'basil'), 0.5, 'cup', 'Imperial');

INSERT INTO recipes_ingredients (recipe_id, ingredient_id, amount, unit_type, system_of_measurement) VALUES
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Chicken Stir-Fry'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'chicken breast'), 2, 'pieces', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Chicken Stir-Fry'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'garlic'), 3, 'cloves', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Chicken Stir-Fry'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'onions'), 1, 'cup', 'Imperial');

INSERT INTO recipes_ingredients (recipe_id, ingredient_id, amount, unit_type, system_of_measurement) VALUES
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Spaghetti Bolognese'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'beef'), 1, 'lb', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Spaghetti Bolognese'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'tomatoes'), 2, 'cups', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Spaghetti Bolognese'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'onions'), 1, 'cup', 'Imperial');

INSERT INTO recipes_ingredients (recipe_id, ingredient_id, amount, unit_type, system_of_measurement) VALUES
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Grilled Cheese Sandwich'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'cheese'), 2, 'slices', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Grilled Cheese Sandwich'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'bread'), 2, 'slices', 'Imperial');

INSERT INTO recipes_ingredients (recipe_id, ingredient_id, amount, unit_type, system_of_measurement) VALUES
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Chocolate Cake'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'flour'), 2, 'cups', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Chocolate Cake'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'sugar'), 2, 'cups', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Chocolate Cake'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'cocoa'), 0.75, 'cup', 'Imperial');

INSERT INTO recipes_ingredients (recipe_id, ingredient_id, amount, unit_type, system_of_measurement) VALUES
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Mushroom Risotto'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'rice'), 1, 'cup', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Mushroom Risotto'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'mushrooms'), 1, 'cup', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Mushroom Risotto'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'parmesan cheese'), 0.5, 'cup', 'Imperial');

INSERT INTO recipes_ingredients (recipe_id, ingredient_id, amount, unit_type, system_of_measurement) VALUES
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Fish Tacos'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'fish'), 1, 'lb', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Fish Tacos'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'cabbage'), 1, 'cup', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Fish Tacos'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'tortillas'), 8, 'pieces', 'Imperial');

INSERT INTO recipes_ingredients (recipe_id, ingredient_id, amount, unit_type, system_of_measurement) VALUES
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Chicken Caesar Salad'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'chicken breast'), 2, 'pieces', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Chicken Caesar Salad'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'lettuce'), 1, 'head', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Chicken Caesar Salad'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'parmesan cheese'), 0.5, 'cup', 'Imperial');

INSERT INTO recipes_ingredients (recipe_id, ingredient_id, amount, unit_type, system_of_measurement) VALUES
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Mac and Cheese'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'pasta'), 8, 'oz', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Mac and Cheese'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'cheese'), 2, 'cups', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Mac and Cheese'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'milk'), 2, 'cups', 'Imperial');

INSERT INTO recipes_ingredients (recipe_id, ingredient_id, amount, unit_type, system_of_measurement) VALUES
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Shrimp Scampi'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'shrimp'), 1, 'lb', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Shrimp Scampi'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'garlic'), 3, 'cloves', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Shrimp Scampi'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'lemon'), 1, 'whole', 'Imperial');

INSERT INTO recipes_ingredients (recipe_id, ingredient_id, amount, unit_type, system_of_measurement) VALUES
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Pulled Pork Sandwich'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'pork'), 2, 'lb', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Pulled Pork Sandwich'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'bread'), 8, 'buns', 'Imperial');

INSERT INTO recipes_ingredients (recipe_id, ingredient_id, amount, unit_type, system_of_measurement) VALUES
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Greek Salad'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'tomatoes'), 2, 'cups', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Greek Salad'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'cucumbers'), 1, 'cup', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Greek Salad'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'feta cheese'), 0.5, 'cup', 'Imperial');

INSERT INTO recipes_ingredients (recipe_id, ingredient_id, amount, unit_type, system_of_measurement) VALUES
((SELECT recipe_id FROM recipe WHERE recipe_name = 'French Toast'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'bread'), 4, 'slices', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'French Toast'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'eggs'), 2, 'pieces', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'French Toast'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'milk'), 1, 'cup', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'French Toast'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'cinnamon'), 1, 'tsp', 'Imperial');



-- INSERT INTO user_grocery_list (user_id, item_name, amount, unit_type, system_of_measurement) VALUES
--need to change to just ingredient_name
-- (1, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Spaghetti'), 400, 'grams', 'Metric'),
-- (1, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Eggs'), 4, 'pieces', 'Metric'),
-- (1, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Parmesan Cheese'), 100, 'grams', 'Metric'),
-- (1, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Pancetta'), 150, 'grams', 'Metric'),
-- (1, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Black Pepper'), 5, 'grams', 'Metric'),
-- (2, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Butter'), 1, 'cup', 'Imperial'),
-- (2, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Sugar'), 0.5, 'cup', 'Imperial'),
-- (2, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Brown Sugar'), 0.5, 'cup', 'Imperial'),
-- (2, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Eggs'), 2, 'pieces', 'Imperial'),
-- (2, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Vanilla Extract'), 1, 'tsp', 'Imperial'),
-- (2, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Flour'), 2.5, 'cups', 'Imperial'),
-- (2, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Baking Soda'), 1, 'tsp', 'Imperial'),
-- (2, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Salt'), 0.5, 'tsp', 'Imperial'),
-- (2, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Chocolate Chips'), 2, 'cups', 'Imperial');

-- Recipe_Tags Table
-- INSERT INTO recipe_tags (recipe_id, tag_id) VALUES
-- (1, 3),
-- (1, 5),
-- (2, 4),
-- (2, 8);
-- User_Tracked_Recipes Table

-- INSERT INTO user_tracked_recipes (user_id, recipes_id) 
-- SELECT 3, recipe_id FROM recipe WHERE recipe_name IN ('Chocolate Chip Cookies', 'Spaghetti Carbonara', 'Tacos');

--insert into grocery list
INSERT INTO user_grocery_list (user_id, item_name, amount, unit_type, system_of_measurement) 
VALUES (3, 'lettuce', 1, 'head', 'Imperial');

INSERT INTO user_grocery_list (user_id, item_name, amount, unit_type, system_of_measurement) 
VALUES (3, 'chicken breast', 2, 'pieces', 'Imperial');

INSERT INTO user_grocery_list (user_id, item_name, amount, unit_type, system_of_measurement) 
VALUES (3, 'spaghetti', 400, 'grams', 'Metric');

INSERT INTO user_grocery_list (user_id, item_name, amount, unit_type, system_of_measurement) 
VALUES (3, 'beef', 500, 'grams', 'Metric');


COMMIT TRANSACTION;

-- rollback;

